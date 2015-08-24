package com.example.hyes.myrecord_bc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by hyes on 2015. 4. 16..
 */
public class ListItemView extends LinearLayout {

    TextView nameTextView, dateTextView;
    TextView r1, r2, r3, r4, p1, p2, p3, p4;

    public ListItemView(Context context) {
        super(context);
        init(context);
    }

    public ListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context c) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item, this, true);
        nameTextView = (TextView) findViewById(R.id.name_tv);
        dateTextView = (TextView) findViewById(R.id.date_tv);
        r1 = (TextView) findViewById(R.id.rec1_tv);
        r2 = (TextView) findViewById(R.id.rec2_tv);
        r3 = (TextView) findViewById(R.id.rec3_tv);
        r4 = (TextView) findViewById(R.id.rec4_tv);
        p1 = (TextView) findViewById(R.id.pic1_tv);
        p2 = (TextView) findViewById(R.id.pic2_tv);
        p3 = (TextView) findViewById(R.id.pic3_tv);
        p4 = (TextView) findViewById(R.id.pic4_tv);
    }

    public void setName(String name) {
        nameTextView.setText(name);
    }

    public void setDate(String date) {
        dateTextView.setText(date);
    }

    public void setRecord1(String record1) {
        r1.setText(record1);
    }

    public void setRecord2(String record2) {
        r2.setText(record2);
    }

    public void setRecord3(String record3) {
        r3.setText(record3);
    }

    public void setRecord4(String record4) {
        r4.setText(record4);
    }

    public void setPicture1(String picture1) {
        p1.setText(picture1);
    }

    public void setPicture2(String picture2) {
        p1.setText(picture2);
    }

    public void setPicture3(String picture3) {
        p1.setText(picture3);
    }

    public void setPicture4(String picture4) {
        p1.setText(picture4);
    }


}
