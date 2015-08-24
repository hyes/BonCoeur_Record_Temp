package com.example.hyes.myrecord_bc;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hyes on 2015. 3. 1..
 */
public class MainRecord extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    private static final float VISUALIZER_HEIGHT_DIP = 50f;
    private static final String TAG = "AudioFxDemo";

    final private static String RECORDED_FILEPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    //final private static String RECORDED_FILEPATH = "/storage/emulated/0/";
    MediaPlayer player;
    MediaRecorder recorder;
    private String fileName = null;
    private Uri outUri,realUri;
    private String filename, filePath;
    private SimpleDateFormat timestamp, timestampDate;
    private Camera camera = null;
    private ImageView pic_frame;
    private int idx, id;
    private LinearLayout visualizer;
    private String name;
    private String outUriStr;


    //audiofxview
    private VisualizerView mVisualizerView;
    private Visualizer mVisualizer;

    private Handler mHandler;
    private Runnable mRunnable;

    private static final int TYPE_WIFI = 1;
    private static final int TYPE_MOBILE = 2;
    private static final int TYPE_NOT_CONNECTED = 0;

    private RecordingDataPreference pref;

    private ProgressDialog progressDialog;

    private Spinner spinner;
    private String[] positions = {"Mitral valve", "Triscuspid valve", "Pulmonic valve", "Aortic valve"};
    private String selected_position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_main);

        Log.i("test", RECORDED_FILEPATH);

        Intent intent = getIntent();
        name =  intent.getStringExtra("name");
        id = intent.getIntExtra("id",0);
        idx = intent.getIntExtra("IDX",0);
        Log.i("test", "MainRecord~  name: " + name + "id: "+ id);


        ImageButton record_btn = (ImageButton) findViewById(R.id.record);
        ImageButton play_btn = (ImageButton) findViewById(R.id.play);
        ImageButton stop_btn = (ImageButton) findViewById(R.id.stop);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setPrompt("Choose the recording position");
        ArrayAdapter<String> list;
        list = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, positions);
        spinner.setAdapter(list);
        spinner.setOnItemSelectedListener(this);



        timestamp = new SimpleDateFormat("yyyyMMddHHmmss");
        timestampDate = new SimpleDateFormat("yyyyMMdd");

        visualizer = (LinearLayout)findViewById(R.id.visualizer_draw);

        final CameraSurfaceView cameraView = new CameraSurfaceView(getApplicationContext());
        FrameLayout previewFrame = (FrameLayout) findViewById(R.id.previewFrame);
        previewFrame.addView(cameraView);
        pic_frame = (ImageView) findViewById(R.id.pic);

        Button save_button = (Button) findViewById(R.id.save);
        Button cancel_button = (Button) findViewById(R.id.cancel_btn);
        final TextView step = (TextView) findViewById(R.id.step);
        step.setText( name + "'s  STEP " + idx );

        pref = new RecordingDataPreference(this);


        //record
        record_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                visualizer.removeView(mVisualizerView);

                RecordDialog recordDialog = new RecordDialog();

                recordDialog.show(getFragmentManager(), "recording");


                if (recorder == null) {
                    recorder = new MediaRecorder();
                }

                fileName = name+ "_" + timestamp.format(new Date()).toString() + "REC.mp4";

                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                recorder.setOutputFile(RECORDED_FILEPATH + fileName);
                Log.i("title test", RECORDED_FILEPATH + fileName);


                try {
                    recorder.prepare();
                    recorder.start();
                    Toast.makeText(getApplicationContext(), "녹음 시작~", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("RECORDER TEST", "EXCEPTION ", e);
                }


                mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        recorder.stop();
                        recorder.release();
                        Toast.makeText(getApplicationContext(), "녹음 중지~", Toast.LENGTH_SHORT).show();
                        recorder = null;

                        ContentValues values = new ContentValues(10);

                        values.put(MediaStore.MediaColumns.TITLE, RECORDED_FILEPATH + fileName);
                        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp4");
                        values.put(MediaStore.Audio.Media.DATA, RECORDED_FILEPATH + fileName);

                        Uri audioUri = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);

                        if (audioUri == null) {
                            Log.i("SampleAudioRecorder", "Audio insert failed");
                            return;
                        }
                    }
                };
                mHandler = new Handler();
                mHandler.postDelayed(mRunnable, 5000);

            }
        });


        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                mHandler.removeCallbacks(mRunnable);
//                Intent intent = new Intent(MainRecord.this, RecordingMainList.class);
//                intent.putExtra("name", name);
//                intent.putExtra("id", id);
//                startActivityForResult(intent, RESULT_OK);
//                finish();
                Intent intent = new Intent(MainRecord.this, RecordingDataList.class);
                intent.putExtra("name", name);
                intent.putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();


            }
        });

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    playAudio(RECORDED_FILEPATH + fileName);
                    Log.i("test", RECORDED_FILEPATH + fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


        stop_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (recorder == null) {
                    return;
                }

                recorder.stop();
                recorder.release();
                Toast.makeText(getApplicationContext(), "녹음이 중지되었습니다~", Toast.LENGTH_SHORT).show();
                recorder = null;

                ContentValues values = new ContentValues(10);

                values.put(MediaStore.MediaColumns.TITLE, RECORDED_FILEPATH + fileName);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp4");
                values.put(MediaStore.Audio.Media.DATA, RECORDED_FILEPATH + fileName);

                Uri audioUri = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);

                if (audioUri == null) {
                    Log.i("SampleAudioRecorder", "Audio insert failed");
                    return;
                }

            }
        });


        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fileName == null){
                    Toast.makeText(getApplicationContext(), "You need to record..", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(outUriStr == null){
                    Toast.makeText(getApplicationContext(), "You need to take a picture", Toast.LENGTH_SHORT).show();
                    return;

                }

                switch (idx) {
                    case 1:

                        Intent intent1 = new Intent(MainRecord.this, RecordingMainList.class);
                        intent1.putExtra("name", name);
                        intent1.putExtra("file1", RECORDED_FILEPATH + fileName);
                        intent1.putExtra("pic1", realUri.toString());
                        Dao dao1 = new Dao(getApplicationContext());
                        dao1.updateData1(RECORDED_FILEPATH + fileName, realUri.toString(), selected_position, name, id);
                        setResult(RESULT_OK, intent1);
                        finish();
                        break;

                    case 2:
                        Intent intent2 = new Intent(MainRecord.this, RecordingMainList.class);
                        intent2.putExtra("name", name);
                        intent2.putExtra("file2", RECORDED_FILEPATH + fileName);
                        intent2.putExtra("pic2", realUri.toString());
                        Dao dao2 = new Dao(getApplicationContext());
                        dao2.updateData2(RECORDED_FILEPATH + fileName, realUri.toString(), selected_position, name, id);
                        setResult(RESULT_OK, intent2);
                        finish();
                        break;

                    case 3:
                        Intent intent3 = new Intent(MainRecord.this, RecordingMainList.class);
                        intent3.putExtra("name", name);
                        intent3.putExtra("file3", RECORDED_FILEPATH + fileName);
                        intent3.putExtra("pic3", realUri.toString());
                        Dao dao3 = new Dao(getApplicationContext());
                        dao3.updateData3(RECORDED_FILEPATH + fileName, realUri.toString(), selected_position, name, id);
                        setResult(RESULT_OK, intent3);
                        finish();
                        break;

                    case 4:
                        Intent intent4 = new Intent(MainRecord.this, RecordingMainList.class);
                        intent4.putExtra("name", name);
                        intent4.putExtra("file4", RECORDED_FILEPATH + fileName);
                        intent4.putExtra("pic4", realUri.toString());
                        Dao dao4 = new Dao(getApplicationContext());
                        dao4.updateData4(RECORDED_FILEPATH + fileName, realUri.toString(), selected_position, name, id);
                        setResult(RESULT_OK, intent4);
                        finish();
                        break;

                    case 5:
                        Intent intent5 = new Intent(MainRecord.this, RecordingMainList.class);
                        intent5.putExtra("name", name);
                        intent5.putExtra("file5", RECORDED_FILEPATH + fileName);
                        intent5.putExtra("pic5", realUri.toString());
                        Dao dao5 = new Dao(getApplicationContext());
                        dao5.updateData5(RECORDED_FILEPATH + fileName, realUri.toString(), selected_position, name, id);
                        setResult(RESULT_OK, intent5);
                        finish();
                        break;

                    case 6:
                        Intent intent6 = new Intent(MainRecord.this, RecordingMainList.class);
                        intent6.putExtra("name", name);
                        intent6.putExtra("file6", RECORDED_FILEPATH + fileName);
                        intent6.putExtra("pic6", realUri.toString());
                        Dao dao6 = new Dao(getApplicationContext());
                        dao6.updateData6(RECORDED_FILEPATH + fileName, realUri.toString(), selected_position, name, id);
                        setResult(RESULT_OK, intent6);
                        finish();
                        break;
                }

            }
        });



        //camera

        previewFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.capture(new Camera.PictureCallback() {
                    public void onPictureTaken(byte[] data, Camera camera) {
                        try {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            outUriStr = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "CapturedImage", "Catured Image Using Camera");
                            if (outUriStr == null) {
                                Log.d("SampleCapture", "Image insert failed.");
                                return;
                            } else {

                                outUri = Uri.parse(outUriStr);
                                realUri = getRealPathUri(outUri);
                                filePath = realUri.toString();
                                filename = realUri.getLastPathSegment();
                                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, realUri));
                            }

                            camera.startPreview();
                            captureView(outUriStr);

                        } catch (Exception e) {
                            Log.e("SampleCapture", "Failed to insert image.", e);
                        }
                    }
                });
            }
        });

    }



    private void captureView(String src) {
//
//        Matrix m = new Matrix();
//        m.postRotate(90);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bt;
        Uri outUri = Uri.parse(src);
        try {
            bt = BitmapFactory.decodeStream(getContentResolver().openInputStream(outUri), null, options);

            pic_frame.setImageBitmap(bt);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    private class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceHolder mHolder;


        public CameraSurfaceView(Context context) {
            super(context);

            mHolder = getHolder();
            mHolder.addCallback(this);

        }

        public void surfaceCreated(SurfaceHolder holder) {
            camera = Camera.open();

            try {
                camera.setPreviewDisplay(mHolder);
            } catch (Exception e) {
                camera.release();
                camera = null;
                Log.e("CameraSurfaceView", "Failed to set camera preview.", e);
            }
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Camera.Parameters parameters = camera.getParameters();
//            List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();
            Camera.Size s = parameters.getSupportedPreviewSizes().get(0);
            parameters.setPreviewSize(s.width, s.height);


            camera.setParameters(parameters);
            camera.setDisplayOrientation(90);

            camera.startPreview();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }

        public boolean capture(Camera.PictureCallback handler) {
            if (camera != null) {
                camera.takePicture(null, null, handler);
                return true;
            } else {
                return false;
            }
        }

    }

    private Uri getRealPathUri(Uri uri) {
        Uri filePathUri = uri;
        if (uri.getScheme().toString().compareTo("content") == 0) {
            Cursor cursor = getApplicationContext().getContentResolver().query(uri, null, null, null, null);
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                filePathUri = Uri.parse(cursor.getString(column_index));
            }
        }
        return filePathUri;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    protected void onPause() {
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }

        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        recorder = new MediaRecorder();
    }

    private void playAudio(String url) throws Exception {
        killMediaPlayer();

        player = new MediaPlayer();
        player.setDataSource(url);
        Log.d(TAG, "MediaPlayer audio session ID: " + player.getAudioSessionId());

        mVisualizerView = new VisualizerView(getApplicationContext());
        mVisualizerView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                (int) (VISUALIZER_HEIGHT_DIP * getResources().getDisplayMetrics().density)));
        visualizer.addView(mVisualizerView);
//       String testest = String.valueOf((int) (VISUALIZER_HEIGHT_DIP * getResources().getDisplayMetrics().density));
//
//        Log.i(TAG, testest);

        // Create the Visualizer object and attach it to our media player.
        mVisualizer = new Visualizer(player.getAudioSessionId());
        mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        mVisualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() {
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes,
                                              int samplingRate) {
                mVisualizerView.updateVisualizer(bytes);
            }

            public void onFftDataCapture(Visualizer visualizer, byte[] bytes, int samplingRate) {
            }
        }, Visualizer.getMaxCaptureRate() / 2, true, false);


        mVisualizer.setEnabled(true);

        // When the stream ends, we don't need to collect any more data. We don't do this in
        // setupVisualizerFxAndUI because we likely want to have more, non-Visualizer related code
        // in this callback.
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                mVisualizer.setEnabled(false);
            }
        });


        player.prepare();
        player.start();

    }

    protected void onDestroy() {
        super.onDestroy();

        killMediaPlayer();
        try {
            mHandler.removeCallbacks(mRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void killMediaPlayer() {
        if (player != null) {
            try {
                player.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        selected_position = (String)spinner.getAdapter().getItem(spinner.getSelectedItemPosition());
        Log.i("test", selected_position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



