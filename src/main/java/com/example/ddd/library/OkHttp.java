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
     * get通过url获取字符串
     * @param url
     * @return string
     * @throws IOException
     */
    public byte[] getStringByUrlTwo(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        byte[] responseBytes=response.body().bytes();
        return responseBytes;
    }

    /**
     * get通过url获取字符串
     * @param url
     * @return string
     * @throws IOException
     */
    public byte[] getStringByUrlThree(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .addHeader("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8")
                .addHeader("Connection","keep-alive")
                .addHeader("Referer","http://www.okooo.com/soccer/match/1011677/node/")
                .addHeader("Host","www.okooo.com")
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36")
                .build();
        Response response = client.newCall(request).execute();
        byte[] responseBytes=response.body().bytes();
        return responseBytes;
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
