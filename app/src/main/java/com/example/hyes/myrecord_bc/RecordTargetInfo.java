package com.example.hyes.myrecord_bc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RecordTargetInfo extends ActionBarActivity {
    private static final int TARGET_NAME = 1;
    String target_name = null, temp_age;
    int target_age;
    EditText name_edit_text, age_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_target_info);
        name_edit_text = (EditText) findViewById(R.id.target_name);
        age_edit_text = (EditText) findViewById(R.id.target_age);
        Button start_btn = (Button) findViewById(R.id.start_btn);


        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                target_name = name_edit_text.getText().toString();
                temp_age = age_edit_text.getText().toString();
                target_age = Integer.parseInt(temp_age);

                if (target_name.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                } else if(temp_age.matches("")){
                    Toast.makeText(getApplicationContext(), "Please enter age", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    Dao dao = new Dao(getApplicationContext());
                  ////이거부터 수정

                    dao.targetInsert(target_name, target_age);

                    //dao.targetInsert(target_name);

                  //  int id = dao.getRecentId();


                    Intent intent = new Intent(getApplicationContext(), RecordingMainList.class);
                    //intent.putExtra("id", id);
                    intent.putExtra("name", target_name);

                    startActivityForResult(intent, TARGET_NAME);
                }
            }
        });
    }
}
