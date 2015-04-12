//package com.example.hyes.myrecord_bc;
//
//
//import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.RequestParams;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//
///**
// * Created by hyes on 2015. 4. 12..
// */
//public class ProxyUp {
//
//    private String lineEnd = "\r\n";
//    private String twoHyphens = "--";
//    private String boundary = "*****";
//
//    private static AsyncHttpClient client = new AsyncHttpClient();
//
//    public static void uploadArticle(Article article, String filePath,
//                                     AsyncHttpResponseHandler responseHandler) {
//        RequestParams params = new RequestParams();
//        params.put("title", article.getTitle());
//        params.put("writer", article.getWriter());
//        params.put("id", article.getId());
//        params.put("content", article.getContent());
//        params.put("writeDate", article.getWriteDate());
//        params.put("imgName", article.getImgName());
//
//        try {
//            params.put("uploadedfile", new File(filePath));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        client.post("http://192.168.56.1:5009/upload", params, responseHandler);
//    }
//}