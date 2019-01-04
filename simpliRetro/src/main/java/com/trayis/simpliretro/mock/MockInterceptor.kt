package com.trayis.simpliretro.mock

import android.content.Context
import android.text.TextUtils
import android.util.Log

import com.google.gson.Gson

import java.io.IOException
import java.io.InputStream

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * Created by mudesai on 9/18/16.
 */
class MockInterceptor private constructor(context: Context) : Interceptor, Runnable {

    private val context: Context = context.applicationContext

    private var domains: Array<Domain>? = null

    private var uriMatcher: MockUriMatcher? = null

    init {
        // TODO: Confirm if possible to avoid thread spawning
        Thread(this).start()
        // run();
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url()
        Log.v(TAG, url.toString())

        val json = uriMatcher!!.match(url)

        return if (!TextUtils.isEmpty(json)) {
            obtainResponse(request, json!!)
        } else chain.proceed(request)

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
        val gson = Gson()
        domains = gson.fromJson(array, Array<Domain>::class.java)
        uriMatcher = MockUriMatcher(MockUriMatcher.NO_MATCH)

        domains?.let { dom ->
            for ((nodes, url) in dom) {
                nodes?.let { nod ->
                    for ((url1, json) in nod) {
                        url?.let {
                            val baseUrl = HttpUrl.parse(it)?.host()
                            uriMatcher!!.addURI(baseUrl!!, url1, json)
                        }
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
            json = String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            Log.w(TAG, e.message, e)
        }

        return json
    }

    companion object {

        private val TAG = "MockInterceptor"

        private const val MOCK_JSON = "_mock.json"

        fun getInstance(context: Context): MockInterceptor {
            return MockInterceptor(context)
        }

        fun isConfigured(context: Context): Boolean {
            var fileExists = false
            var `is`: InputStream? = null
            try {
                `is` = context.assets.open(MOCK_JSON)
            } catch (e: IOException) {
                Log.w(TAG, e.message, e)
            } finally {
                if (`is` != null) {
                    fileExists = true
                    try {
                        `is`.close()
                    } catch (e: IOException) {
                        // Do nothing
                    }

                }
            }
            return fileExists
        }
    }
}
