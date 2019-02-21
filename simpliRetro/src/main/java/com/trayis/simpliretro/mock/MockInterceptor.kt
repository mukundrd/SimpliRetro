package com.trayis.simpliretro.mock

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

/**
 * Created by mudesai on 9/18/16.
 */
class MockInterceptor private constructor(val context: Context) : Interceptor, Runnable {

    private lateinit var uriMatcher: MockUriMatcher

    init {
        Thread(this).start()
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url()
        Log.v(TAG, url.toString() + request.method())

        val json = uriMatcher.match(url, request.method())

        return json?.let {
            obtainResponse(request, it)
        } ?: chain.proceed(request)

    }

    private fun obtainResponse(request: Request, json: String): Response {
        val responseString = loadJsonFromFile(json)

        return Response.Builder()
                .code(200)
                .message("Mock - Response")
                .request(request)
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString!!.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
    }

    override fun run() {
        val array = loadJsonFromFile(MOCK_JSON)
        val domains = Gson().fromJson(array, Array<Domain>::class.java)
        uriMatcher = MockUriMatcher(MockUriMatcher.NO_MATCH)

        for ((nodes, url) in domains) {
            nodes?.let {
                for ((url1, json, method) in it) {
                    HttpUrl.parse(url!!)?.let {
                        val baseUrl = it.host()
                        uriMatcher.addURI(baseUrl, url1, json!!, method)
                    }
                }
            }
        }

    }

    private fun loadJsonFromFile(jsonFile: String): String? {
        var json: String? = null
        try {
            val `is` = context.assets.open(jsonFile)
            val size = `is`.available()
            val buffer = ByteArray(size)

            `is`.read(buffer)
            `is`.close()
            json = String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            Log.w(TAG, e.message, e)
        }

        return json
    }

    companion object {

        private const val TAG = "MockInterceptor"

        private const val MOCK_JSON = "_mock.json"

        fun getInstance(context: Context): MockInterceptor {
            return MockInterceptor(context.applicationContext)
        }

        fun isConfigured(context: Context): Boolean {
            var fileExists = false
            var `is`: InputStream? = null
            try {
                `is` = context.assets.open(MOCK_JSON)
            } catch (e: IOException) {
                Log.w(TAG, e.message, e)
            } finally {
                `is`?.let {
                    fileExists = true
                    try {
                        it.close()
                    } catch (e: IOException) {
                        // Do nothing
                    }

                }
            }
            return fileExists
        }
    }
}
