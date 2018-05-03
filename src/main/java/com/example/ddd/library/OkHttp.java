package com.example.ddd.library;

import okhttp3.*;

import java.io.IOException;

public class OkHttp {

    private OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    /**
     * get通过url获取字符串
     * @param url
     * @return string
     * @throws IOException
     */
    public String getStringByUrl(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * post通过url获取字符串
     * @param url
     * @param json
     * @return string
     * @throws IOException
     */
    public String postStringByUrl(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
