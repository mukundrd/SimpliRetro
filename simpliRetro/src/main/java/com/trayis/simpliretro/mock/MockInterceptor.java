package com.trayis.simpliretro.mock;

import android.content.Context;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by mudesai on 9/18/16.
 */
public class MockInterceptor implements Interceptor, Runnable {

    private static final String TAG = "MockInterceptor";

    private static final String MOCK_JSON = "_mock.json";

    private final Context context;

    private MockUriMatcher uriMatcher;

    private MockInterceptor(Context context) {
        this.context = context.getApplicationContext();
        new Thread(this).start();
    }

    public static MockInterceptor getInstance(Context context) {
        return new MockInterceptor(context);
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl url = request.url();
        Log.v(TAG, url.toString());

        String json = uriMatcher.match(url);

        if (!TextUtils.isEmpty(json)) {
            return obtainResponse(request, json);
        }

        return chain.proceed(request);
    }

    private Response obtainResponse(Request request, String json) {
        String responseString = loadJsonFromFile(json);

        return new Response.Builder()
                .code(200)
                .message("Mock - Response")
                .request(request)
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();
    }

    @Override
    public void run() {
        String array = loadJsonFromFile(MOCK_JSON);
        Gson gson = new Gson();
        Domain[] domains = gson.fromJson(array, Domain[].class);
        uriMatcher = new MockUriMatcher(MockUriMatcher.NO_MATCH);

        for (Domain domain : domains) {
            MockMatchNode[] nodes = domain.nodes;
            for (MockMatchNode node : nodes) {
                HttpUrl parse = HttpUrl.parse(domain.url);
                if (parse != null) {
                    String baseUrl = parse.host();
                    uriMatcher.addURI(baseUrl, node.url, node.json);
                }
            }
        }

    }

    private String loadJsonFromFile(String jsonFile) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            //noinspection ResultOfMethodCallIgnored
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            Log.w(TAG, e.getMessage(), e);
        }
        return json;
    }

    public static boolean isConfigured(Context context) {
        boolean fileExists = false;
        InputStream is = null;
        try {
            is = context.getAssets().open(MOCK_JSON);
        } catch (IOException e) {
            Log.w(TAG, e.getMessage(), e);
        } finally {
            if (is != null) {
                fileExists = true;
                try {
                    is.close();
                } catch (IOException e) {
                    // Do nothing
                }
            }
        }
        return fileExists;
    }
}
