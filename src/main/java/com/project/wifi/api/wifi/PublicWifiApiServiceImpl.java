package com.project.wifi.api.wifi;

import com.google.gson.Gson;
import com.project.wifi.api.wifi.data.WifiDetail;
import com.project.wifi.api.wifi.data.WifiResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class PublicWifiApiServiceImpl {

    public WifiResult getWifiResult() throws IOException {

        String wifiUrl = getPublicWifiURLBuilder(1, 1);

        Response response = requestExecute(wifiUrl);

        WifiResult tb = null;

        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            if (body != null) {

                String temp = body.string();

                System.out.println(temp);

                Gson gson = new Gson();
                tb =  gson.fromJson(temp, WifiResult.class);

                System.out.println(tb.toString());
                System.out.println("===============================================");
                System.out.println(tb.getTbPublicWifiInfo().getRow().get(0).toString());

            }
        } else {
            System.out.println("error");
        }

        return tb;
    }

    public int getWifiTotalCount() throws IOException {
        String wifiURL = getPublicWifiURLBuilder(1, 1);

        Response response = requestExecute(wifiURL);

        WifiResult tb = null;

        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            if (body != null) {

                String temp = body.string();
                Gson gson = new Gson();
                tb =  gson.fromJson(temp, WifiResult.class);
            }
        } else {
            System.out.println("error");
        }


        if (tb == null) {
            return 0;
        }

        return tb.getTbPublicWifiInfo().getListTotalCount();
    }


    public List<WifiDetail> getWifiDetailPage(int startNum, int endNum) throws IOException {
        String wifiUrl = getPublicWifiURLBuilder(startNum, endNum);

        Response response = requestExecute(wifiUrl);

        WifiResult tb = null;

        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            if (body != null) {

                String temp = body.string();
                Gson gson = new Gson();
                tb =  gson.fromJson(temp, WifiResult.class);
            }
        } else {
            System.out.println("error");
        }

        return tb.getTbPublicWifiInfo().getRow();

    }

    private Response requestExecute(String wifiUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request.Builder requestBuilder = new Request.Builder().url(wifiUrl).get();
        requestBuilder.addHeader("Content-type", "application/json");
        Request request = requestBuilder.build();

        return client.newCall(request).execute();
    }

    private String getPublicWifiURLBuilder(int startNumber, int endNumber) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder("http://openapi.seoul.go.kr:8088");
        sb.append("/" + URLEncoder.encode("447765796f7363693833474a435970", "UTF-8"));
        sb.append("/" + URLEncoder.encode("json", "UTF-8"));
        sb.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
        sb.append("/" + URLEncoder.encode(String.valueOf(startNumber), "UTF-8"));
        sb.append("/" + URLEncoder.encode(String.valueOf(endNumber), "UTF-8"));
        return sb.toString();
    }
}
