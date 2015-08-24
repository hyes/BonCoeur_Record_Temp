package com.example.hyes.myrecord_bc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.Date;

/**
 * Created by hyes on 2015. 3. 1..
 */
public class ConfirmEmail extends ActionBarActivity{

    private EditText register_email1, register_email2;
    private ProgressDialog progressDialog;
    private RecordItem record, data;

    private int id;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_email);



        Intent intent = getIntent();

        id = intent.getIntExtra("id", 0);
        Log.i("test", id+"");

        name = intent.getStringExtra("name");


        Button button2 = (Button) findViewById(R.id.submit_button);

        register_email1 = (EditText) findViewById(R.id.register_email1);
        register_email2 = (EditText)findViewById(R.id.register_email2);

        button2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   String email1, email2;

                   email1 = register_email1.getText().toString();
                   email2 = register_email2.getText().toString();


                   if(email1.equals(email2)){

                       Dao dao = new Dao(getApplicationContext());
                       dao.updateEmail(email1, name, id);
                       submit();

                   } else {
                       Toast.makeText(getApplicationContext(), "please check email address", Toast.LENGTH_SHORT).show();
                   }

               }
           }
        );

    }

    private void submit(){

        try {
            progressDialog = ProgressDialog.show(ConfirmEmail.this, "", "업로드 중입니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dao dao = new Dao(getApplicationContext());
        record = dao.getRecordingDataByName(id, name);

        data = new RecordItem(name, new Date().toString(), record.getAge(), record.getEmail(), record.getRecordFile1(), record.getCaptureFile1(), record.getPos1(), record.getRecordFile2(), record.getCaptureFile2(), record.getPos2(), record.getRecordFile3(), record.getCaptureFile3(), record.getPos3(), record.getRecordFile4(), record.getCaptureFile4(), record.getPos4(), record.getRecordFile5(), record.getCaptureFile5(), record.getPos5(), record.getRecordFile6(), record.getCaptureFile6(), record.getPos6());
       // Log.i("test", data.getName() + ":::" + data.getRecordFile1() + data.getCaptureFile1() + data.getRecordFile2() + data.getCaptureFile2() + data.getRecordFile3() + data.getCaptureFile3() + data.getRecordFile4() + data.getCaptureFile4() + data.getDate());
        Log.i("test", data.getName() + ":::" + data.getPos1()+";;"+data.getPos2());
        String[] filePaths = {record.getRecordFile1(), record.getCaptureFile1(), record.getRecordFile2(), record.getCaptureFile2(), record.getRecordFile3(), record.getCaptureFile3(), record.getRecordFile4(), record.getCaptureFile4(), record.getRecordFile5(), record.getCaptureFile5(), record.getRecordFile6(), record.getCaptureFile6()};

        ProxyUp.uploadArticle(data, filePaths,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          byte[] responseBody, Throwable error) {
                        Log.e("test", "up onFailure:" + statusCode);
                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(), "onFailure", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers,
                                          byte[] responseBody) {
                        Log.i("test", "up onSuccess:" + statusCode);
                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(), "onSuccess", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

        Intent intent = new Intent(ConfirmEmail.this, Intro.class);
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(progressDialog != null){
            progressDialog.dismiss();
        }

    }

}
