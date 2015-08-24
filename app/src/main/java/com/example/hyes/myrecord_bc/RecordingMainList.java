package com.example.hyes.myrecord_bc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class RecordingMainList extends ActionBarActivity {

    private String name;
    private int id;
    private TextView name_tv;
    private Button submit_btn;
    private RecordItem record, data;
    private static final int REVISECODE1 = 1;
    private static final int REVISECODE2 = 2;
    private static final int REVISECODE3 = 3;
    private static final int REVISECODE4 = 4;
    private static final int REVISECODE5 = 5;
    private static final int REVISECODE6 = 6;
    public ImageView pic1, pic2, pic3, pic4, pic5, pic6;
    public String[] filePaths;

    private static final int TYPE_WIFI = 1;
    private static final int TYPE_MOBILE = 2;
    private static final int TYPE_NOT_CONNECTED = 0;

    private ProgressDialog progressDialog;
    private AlertDialog alert;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_record_main_list);


        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        Dao dao = new Dao(getApplicationContext());
        id = dao.getRecentId();
        Log.i("test", name + "id: "+ id);



        name_tv = (TextView) findViewById(R.id.name);
        pic1 = (ImageView) findViewById(R.id.cap1);
        pic2 = (ImageView) findViewById(R.id.cap2);
        pic3 = (ImageView) findViewById(R.id.cap3);
        pic4 = (ImageView) findViewById(R.id.cap4);
        pic5 = (ImageView) findViewById(R.id.cap5);
        pic6 = (ImageView) findViewById(R.id.cap6);

        name_tv.setText(name);

        submit_btn = (Button) findViewById(R.id.send_btn);

          submit_btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {


            int status = NetworkState.getConnectivityStatus(getApplicationContext());

                    switch (status) {
                        case TYPE_WIFI:
                        case TYPE_MOBILE:

                            Toast.makeText(getApplicationContext(), "데이터 전송 가능 상태", Toast.LENGTH_SHORT).show();
                            alertDialog();
                            break;
                        case TYPE_NOT_CONNECTED:
                            Toast.makeText(getApplicationContext(), "데이터 전송 불가 상태", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Ending2.class);
                            startActivity(intent);
                            break;
                        default:
                            break;
                    }

//            Dao dao = new Dao(getApplicationContext());
//            record = dao.getRecordingDataByName(name);
//
//            data = new RecordItem(name, new Date().toString(), record.getRecordFile1(), record.getCaptureFile1(), record.getRecordFile2(), record.getCaptureFile2(), record.getRecordFile3(), record.getCaptureFile3(), record.getRecordFile4(), record.getCaptureFile4());
//            //Log.i("test", data.getName() + ":::" + data.getRecordFile1() + data.getCaptureFile1() + data.getRecordFile2() + data.getCaptureFile2() + data.getRecordFile3() + data.getCaptureFile3() + data.getRecordFile4() + data.getCaptureFile4() + data.getDate());
//
//
//            String[] filePaths = {record.getRecordFile1(), record.getCaptureFile1(), record.getRecordFile2(), record.getCaptureFile2(), record.getRecordFile3(), record.getCaptureFile3(), record.getRecordFile4(), record.getCaptureFile4()};
//
//            ProxyUp.uploadArticle(data, filePaths,
//                    new AsyncHttpResponseHandler() {
//                        @Override
//                        public void onFailure(int statusCode, Header[] headers,
//                                              byte[] responseBody, Throwable error) {
//                            Log.e("test", "up onFailure:" + statusCode + error);
//
//                            Toast.makeText(getApplicationContext(), "onFailure", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//
//                        @Override
//                        public void onSuccess(int statusCode, Header[] headers,
//                                              byte[] responseBody) {
//                            Log.i("test", "up onSuccess:" + statusCode);
//
//                            //  progressDialog.cancel();
//                            Toast.makeText(getApplicationContext(), "onSuccess", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//                    });
            }
        });

            Button step1 = (Button) findViewById(R.id.step1);
            step1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RecordingMainList.this, MainRecord.class);
                    intent.putExtra("IDX", 1);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);

                    startActivityForResult(intent, REVISECODE1);

                }
            });

            Button step2 = (Button) findViewById(R.id.step2);
            step2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RecordingMainList.this, MainRecord.class);
                    intent.putExtra("IDX", 2);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    startActivityForResult(intent, REVISECODE2);
                }
            });

            Button step3 = (Button) findViewById(R.id.step3);
            step3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RecordingMainList.this, MainRecord.class);
                    intent.putExtra("IDX", 3);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    startActivityForResult(intent, REVISECODE3);
                }
            });

            Button step4 = (Button) findViewById(R.id.step4);
            step4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RecordingMainList.this, MainRecord.class);
                    intent.putExtra("IDX", 4);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    startActivityForResult(intent, REVISECODE4);
                }
            });

            Button step5 = (Button) findViewById(R.id.step5);
            step5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RecordingMainList.this, MainRecord.class);
                    intent.putExtra("IDX", 5);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    startActivityForResult(intent, REVISECODE5);
                }
            });
            Button step6 = (Button) findViewById(R.id.step6);
            step6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(RecordingMainList.this, MainRecord.class);
                    intent.putExtra("IDX", 6);
                    intent.putExtra("id", id);
                    intent.putExtra("name", name);
                    startActivityForResult(intent, REVISECODE6);
                }
            });

        }



    public void Play1Clicked(View v) throws IOException {
        Log.i("test", record.getRecordFile1());
          playAudio(record.getRecordFile1());
    }
    public void Play2Clicked(View v) throws IOException {
        playAudio(record.getRecordFile2());
    }
    public void Play3Clicked(View v) throws IOException {
        playAudio(record.getRecordFile3());
    }
    public void Play4Clicked(View v) throws IOException {
        playAudio(record.getRecordFile4());
    }
    public void Play5Clicked(View v) throws IOException {
        playAudio(record.getRecordFile5());
    }
    public void Play6Clicked(View v) throws IOException {
        playAudio(record.getRecordFile6());
    }



    public void playAudio(String file) throws IOException {


        MediaPlayer player = new MediaPlayer();
        try {
            player.setDataSource(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        try {
            player.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }


    private Bitmap loadCaptureView(String uri) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither=false;
        options.inSampleSize = 8;
        Bitmap bitmap = BitmapFactory.decodeFile(uri, options);
        return bitmap;
    }

    private void alertDialog() {

        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(RecordingMainList.this);
        alert_confirm.setMessage("Are you sure you want to send your data to BonCoeur?").setCancelable(false).setPositiveButton("CONFIRM",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(RecordingMainList.this, ConfirmEmail.class);
                        intent.putExtra("id", id);
                        intent.putExtra("name", name);
                        startActivityForResult(intent, RESULT_OK);

                    }
                }).setNegativeButton("CANCEL",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 'No'
                        finish();
                        return;
                    }
                });

        alert = alert_confirm.create();
        alert.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REVISECODE1:
                if (resultCode == RESULT_OK) {
                    String name = data.getStringExtra("name");
                    String audio1 = data.getStringExtra("file1");
                    String pic11 = data.getStringExtra("pic1");

                    pic1.setImageBitmap(loadCaptureView(pic11));

                } else {
                    finish();
                }
                break;

            case REVISECODE2:
                if (resultCode == RESULT_OK) {
                    String name = data.getStringExtra("name");
                    String audio2 = data.getStringExtra("file2");
                    String pic22 = data.getStringExtra("pic2");
                    pic2.setImageBitmap(loadCaptureView(pic22));

                } else {
                    finish();
                }
                break;

            case REVISECODE3:
                if (resultCode == RESULT_OK) {
                    String name = data.getStringExtra("name");
                    String audio3 = data.getStringExtra("file3");
                    String pic33 = data.getStringExtra("pic3");
                    pic3.setImageBitmap(loadCaptureView(pic33));

                } else {
                    finish();
                }
                break;

            case REVISECODE4:
                if (resultCode == RESULT_OK) {
                    String name = data.getStringExtra("name");
                    String audio4 = data.getStringExtra("file4");
                    String pic44 = data.getStringExtra("pic4");
                    pic4.setImageBitmap(loadCaptureView(pic44));

                } else {
                    finish();
                }
                break;

            case REVISECODE5:
                if (resultCode == RESULT_OK) {
                    String name = data.getStringExtra("name");
                    String audio5 = data.getStringExtra("file5");
                    String pic55 = data.getStringExtra("pic5");
                    pic5.setImageBitmap(loadCaptureView(pic55));

                } else {
                    finish();
                }
                break;

            case REVISECODE6:
                if (resultCode == RESULT_OK) {
                    String name = data.getStringExtra("name");
                    String audio6 = data.getStringExtra("file6");
                    String pic66 = data.getStringExtra("pic6");
                    pic6.setImageBitmap(loadCaptureView(pic66));

                } else {
                    finish();
                }
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(alert != null){
            alert.dismiss();
        }

    }
}
