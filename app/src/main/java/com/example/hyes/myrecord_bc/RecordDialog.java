package com.example.hyes.myrecord_bc;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hyes on 2015. 4. 10..
 */
public class RecordDialog extends DialogFragment {

    public RecordDialog() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "Recording now...", " recording will automatically end after 30 seconds", true);



        long delayInMillis = 5000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, delayInMillis);

        return dialog;

    }

}
