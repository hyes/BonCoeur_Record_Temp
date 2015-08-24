package com.example.hyes.myrecord_bc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by hyes on 2015. 4. 24..
 */
public class CustomAdapter extends ArrayAdapter<ListItem> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<ListItem> recordData;

    public CustomAdapter(Context context, int resource, ArrayList<ListItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.recordData = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

        }

        TextView step = (TextView) row.findViewById(R.id.step_tv);
        ImageView capturedImage = (ImageView) row.findViewById(R.id.capture_iv);

        step.setText(recordData.get(position).getName());

        String img_path = context.getFilesDir().getPath() + "/" + recordData.get(position).getCaptureFile1();
        File img_load_path = new File(img_path);

        if (img_load_path.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(img_path);
            capturedImage.setImageBitmap(bitmap);
        }

        return row;
    }

}
