package com.example.hyes.myrecord_bc;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hyes on 2015. 3. 1..
 */
public class Record extends ActionBarActivity {

    final private static String RECORDED_FILEPATH = "/storage/emulated/0/";
    MediaPlayer player;
    MediaRecorder recorder;
    private String fileName = null;
    SimpleDateFormat timestamp;
    private Camera camera = null;
    ImageView pic_frame;
    private int idx=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);

        ImageButton record_btn = (ImageButton) findViewById(R.id.record);
        ImageButton play_btn = (ImageButton) findViewById(R.id.play);
        ImageButton stop_btn = (ImageButton) findViewById(R.id.stop);
        timestamp = new SimpleDateFormat("yyyyMMddHHmmss");

        final ImageButton cam_capture = (ImageButton) findViewById(R.id.capture);
        final CameraSurfaceView cameraView = new CameraSurfaceView(getApplicationContext());

        FrameLayout previewFrame = (FrameLayout) findViewById(R.id.previewFrame);
        previewFrame.addView(cameraView);
        Log.i("test", "asasasasasasas");

        pic_frame = (ImageView) findViewById(R.id.pic);
        Button next_button = (Button) findViewById(R.id.next_button);
        final TextView step = (TextView) findViewById(R.id.step);


        //record
        record_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recorder == null) {
                    recorder = new MediaRecorder();
                }

                fileName = timestamp.format(new Date()).toString() + "REC.mp4";
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
            }
        });

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    playAudio(RECORDED_FILEPATH + fileName);
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

                Log.i("title test", "aaaaaaaa");
                // Log.i("title test", RECORDED_FILEPATH + fileName);
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
        });


        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (idx == 0) {
                    Toast.makeText(getApplicationContext(), "다음 단계로 이동합니다", Toast.LENGTH_SHORT).show();
                    step.setText("STEP 2");
                    pic_frame.setImageBitmap(null);
                    //                    pic_frame.setImageDrawable(res.getDrawable(R.drawable.shutter));
                    idx += 1;
                } else if (idx == 1) {

                    step.setText("STEP 3");
                    pic_frame.setImageBitmap(null);
                    idx += 1;
                } else if (idx == 2) {

                    step.setText("STEP 4");
                    pic_frame.setImageBitmap(null);
                    idx += 1;
                } else if (idx == 3) {

                    AlertDialog.Builder alert_confirm = new AlertDialog.Builder(Record.this);


                    alert_confirm.setMessage("Are you sure you want to send your data to BonCoeur?").setCancelable(false).setPositiveButton("CONFIRM",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 'YES'
                                    Intent intent = new Intent(getApplicationContext(), Ending.class);
                                    startActivity(intent);
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

                    AlertDialog alert = alert_confirm.create();
                    alert.show();

//                    TextView tv = (TextView) findViewById(android.R.id.message);
//                    tv.setTextSize(24);
//                    tv.setTextColor(Color.parseColor("#0D415E"));

                }

            }
        });
        //camera

        cam_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.capture(new Camera.PictureCallback() {
                    public void onPictureTaken(byte[] data, Camera camera) {
                        try {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            String outUriStr = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "CapturedImage", "Catured Image Using Camera");
                            if (outUriStr == null) {
                                Log.d("SampleCapture", "Image insert failed.");
                                return;
                            } else {
                                Uri outUri = Uri.parse(outUriStr);

                                Log.i("title test", outUriStr);
                                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, outUri));

                                //changeUri(outUriStr);
                            }

//                            Toast.makeText(getApplicationContext(), "카메라로 찍은 사진을 앨범에 저장했습니다.", Toast.LENGTH_LONG).show();

                            // restart
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

        Matrix m = new Matrix();
        m.postRotate(90);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
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

    public String changeUri(String uri) {
        Cursor c =
                getContentResolver().query(Uri.parse(uri), null, null, null, null);
        c.moveToNext();
        String path = c.getString(c.getColumnIndex(MediaStore.MediaColumns.DATA));

        Log.i("path확인", path);
        return path;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
        player.prepare();
        player.start();

    }

    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
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

}



