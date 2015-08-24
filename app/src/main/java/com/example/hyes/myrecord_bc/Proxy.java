package com.example.hyes.myrecord_bc;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hyes on 2015. 4.
 */
public class Proxy {

    public String getJson(){

        try{
//            URL url = new URL("http://127.0.0.1:8899/loadData");
           //URL url = new URL("http://192.168.56.1:8899/loadData");
            URL url = new URL("Http://147.47.34.31:8000/submit");
//            URL url = new URL("http://10.73.39.201:8899/loadData");
           // URL url = new URL("http:// 172.30.52.215:8899/loadData");



//			URL url = new URL("http://54.64.250.239:5009/loadData");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setConnectTimeout(10*1000);
            conn.setReadTimeout(10*1000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Connection", "Kepp-Alive");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Accept", "application/json");

            conn.setDoInput(true);
            conn.connect();

            int status = conn.getResponseCode();
            Log.i("test", "ProxyResponseCode:" + status);

            switch(status){
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader( new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = br.readLine())!=null){
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();


            }

        } catch(Exception e){
            e.printStackTrace();
            Log.i("test", "NETWORK ERROR:" + e);
        }

        return null;
    }
}