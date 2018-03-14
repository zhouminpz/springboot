package com.zm.utils;


import com.google.gson.Gson;
import javafx.util.Pair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {
    private static final String CHARSET = "UTF-8";

    private static final int retryTimes = 3;//重试次数


    private static HttpClient httpClient = null;

    static {
        synchronized (HttpUtils.class) {
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    .setSocketTimeout(60000)
                    .setConnectTimeout(60000)
                    .setConnectionRequestTimeout(60000)
                    .build();
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(defaultRequestConfig)
                    .setMaxConnTotal(20000)
                    .setMaxConnPerRoute(20000)
                    .setRetryHandler((exception, executionCount, context) -> {
                        if (executionCount >= retryTimes) {
                            return false;
                        }
                        return true;
                    })
                    .build();
        }
    }

    public static void main(String[] args) {
        int i = 1;
        long l1 = System.currentTimeMillis();
        try {
            Pair<Integer, String> resultPair = get("http://192.168.1.230:8011", null, null);
        } catch (Exception e) {
            System.out.println("第" + i + "次重试");
            i++;
        }

        long l2 = System.currentTimeMillis();
        System.out.println((l2 - l1) / 1000 + "秒");
    }


    public static Pair<Integer, String> get(String url,
                                            Map<String, Object> params,
                                            Map<String, String> headers) {
        HttpResponse response = null;
        try {
            if (params != null && params.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
                if (url.contains("?")) {
                    url = url + sb.toString();
                } else {
                    url = "?" + url + sb.toString();
                }
            }
            HttpGet httpGet = new HttpGet(url);
            headers = headers == null ? new HashMap<String, String>() : headers;

            headers.put("Content-Type", "application/json");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }


            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, CHARSET);
            return new Pair<>(response.getStatusLine().getStatusCode(), result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (response instanceof Closeable) {
                try {
                    ((Closeable) response).close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static Pair<Integer, String> post(String url,
                                             Object params,
                                             Map<String, String> headers) {
        HttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            if (params != null) {
                httpPost.setEntity(new StringEntity(new Gson().toJson(params),CHARSET));
            }
            headers = headers == null ? new HashMap<String, String>() : headers;

            headers.put("Content-Type", "application/json");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, CHARSET);
            return new Pair<>(response.getStatusLine().getStatusCode(), result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (response instanceof Closeable) {
                try {
                    ((Closeable) response).close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static Pair<Integer, String> put(String url,
                                            Object params,
                                            Map<String, String> headers) {
        HttpResponse response = null;
        try {
            HttpPut httpPut = new HttpPut(url);
            if (params != null) {
                httpPut.setEntity(new StringEntity(new Gson().toJson(params),CHARSET));
            }

            headers = headers == null ? new HashMap<String, String>() : headers;
            headers.put("Content-Type", "application/json");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPut.setHeader(entry.getKey(), entry.getValue());
            }
            response = httpClient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, CHARSET);
            return new Pair<>(response.getStatusLine().getStatusCode(), result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (response instanceof Closeable) {
                try {
                    ((Closeable) response).close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static Pair<Integer, String> delete(String url,
                                               Object params,
                                               Map<String, String> headers) {
        HttpResponse response = null;
        try {
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url);
            if (params != null) {
                httpDelete.setEntity(new StringEntity(new Gson().toJson(params),CHARSET));
            }
            headers = headers == null ? new HashMap<String, String>() : headers;
            headers.put("Content-Type", "application/json");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpDelete.setHeader(entry.getKey(), entry.getValue());
            }

            response = httpClient.execute(httpDelete);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, CHARSET);
            return new Pair<>(response.getStatusLine().getStatusCode(), result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (response instanceof Closeable) {
                try {
                    ((Closeable) response).close();
                } catch (IOException e) {
                }
            }
        }
    }


    @NotThreadSafe
    static class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
        public static final String METHOD_NAME = "DELETE";
        @Override
        public String getMethod() {
            return METHOD_NAME;
        }

        public HttpDeleteWithBody(final String uri) {
            super();
            setURI(URI.create(uri));
        }

        public HttpDeleteWithBody(final URI uri) {
            super();
            setURI(uri);
        }

        public HttpDeleteWithBody() {
            super();
        }
    }

}
