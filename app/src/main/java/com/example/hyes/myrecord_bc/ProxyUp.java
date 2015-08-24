package com.example.hyes.myrecord_bc;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;

/**
* Created by hyes on 2015. 4. 12..
*/

public class ProxyUp {

    private String lineEnd = "\r\n";
    private String twoHyphens = "--";
    private String boundary = "*****";

    private static AsyncHttpClient client = new AsyncHttpClient();

//    public static void uploadArticle(RecordItem article, String[] filePaths,
//                                     AsyncHttpResponseHandler responseHandler) {
//        RequestParams params = new RequestParams();
//
//            params.put("name", article.getName());
//            params.put("date", article.getDate());
//            params.put("age", article.getAge());
//            params.put("email", article.getEmail());
//            params.put("audio1", article.getRecordFile1());
//            params.put("capture1", article.getCaptureFile1());
//            params.put("pos1", article.getPos1());
//            params.put("audio2", article.getRecordFile2());
//            params.put("capture2", article.getCaptureFile2());
//            params.put("pos2", article.getPos2());
//            params.put("audio3", article.getRecordFile3());
//            params.put("capture3", article.getCaptureFile3());
//            params.put("pos3", article.getPos3());
//            params.put("audio4", article.getRecordFile4());
//            params.put("capture4", article.getCaptureFile4());
//            params.put("pos4", article.getPos4());
//            params.put("audio5", article.getRecordFile5());
//            params.put("capture5", article.getCaptureFile5());
//            params.put("pos5", article.getPos5());
//            params.put("audio6", article.getRecordFile6());
//            params.put("capture6", article.getCaptureFile6());
//            params.put("pos6", article.getPos6());
//
//        for(String name : filePaths) {
//
//            try {
//                if(name != null) {
//                    Log.i("test", "name: " + name);
//                    params.put("file[]", new File(name));
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        //client.post("http://192.168.43.194:8000/upload", params, responseHandler);
//        //client.post("http://192.168.43.195:8000/upload", params, responseHandler);
//        //client.post("http://10.73.39.201:8000/upload", params, responseHandler);
//        //client.post("http://172.30.52.215:8899/upload", params, responseHandler);
//
//       // client.post("http://192.168.56.1:8899/upload", params, responseHandler);
//
//        //와이파이 연결상태에 따라..
//        client.post("http://192.168.2.72:8899/upload", params, responseHandler);
//
//    }
//ddd서버 터미널에서 실행시켜보세요  java -jar BonCouerServer.jar
    // 맥에서 클래스패스 설정하는게 다른가봐요 라이브러리를 못찾는 에러가 뜨네요


////선규
    public static void uploadArticle(RecordItem article, String[] filePaths,
                                     AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();

        params.put("subject_name", article.getName());
        params.put("time1", article.getDate());
        params.put("position1", article.getPos1());
        params.put("position2", article.getPos2());
        params.put("position3", article.getPos3());
        params.put("position4", article.getPos4());



        try {
            params.put("recording1", new File(article.getRecordFile1()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            params.put("picture1", new File(article.getCaptureFile1()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            params.put("recording2", new File(article.getRecordFile2()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            params.put("picture2", new File(article.getCaptureFile2()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            params.put("recording3", new File(article.getRecordFile3()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            params.put("picture3", new File(article.getCaptureFile3()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            params.put("recording4", new File(article.getRecordFile4()));
           // Log.i("test", "recording file" + new File(article.getRecordFile4()).getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            params.put("picture4", new File(article.getCaptureFile4()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            params.put("recording5", new File(article.getRecordFile5()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            params.put("picture5", new File(article.getCaptureFile5()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            params.put("recording6", new File(article.getRecordFile6()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            params.put("picture6", new File(article.getCaptureFile6()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


       // client.post("http://172.30.52.77:8000/submit", params, responseHandler);
       // client.post("http://192.168.2.72:8000/submit", params, responseHandler);
       // client.post("http://192.168.56.1:8000/submit", params, responseHandler);
        client.post("http://147.47.34.31:8000/submit", params, responseHandler);



    }
}