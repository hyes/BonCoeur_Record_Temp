package com.example.hyes.myrecord_bc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.hyes.myrecord_bc.R.id;
import static com.example.hyes.myrecord_bc.R.layout;

/**
 * Created by hyes on 2015. 4. 24..
 */
public class RecordMain extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ArrayList<ListItem> recordList = new ArrayList<>();
    private ListView listView;
    private static final int TARGET_NAME = 1;
    private String name, fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.record_main_list);

       // Button submit_btn = (Button) findViewById(R.id.submit);
        Dao dao = new Dao(getApplicationContext());
       // recordList = dao.getArticleList();

        Intent intent = getIntent();
        name =  intent.getStringExtra("name");
        fileName = intent.getStringExtra("file1");
      //  Toast.makeText(getApplicationContext(), "file~" + fileName, Toast.LENGTH_SHORT).show();


        TextView name_tv = (TextView) findViewById(id.record_mainlist_name);
        name_tv.setText(name);


        listView = (ListView) findViewById(id.listView);

        CustomAdapter adapter = new CustomAdapter(this, layout.custom_recordlist, recordList);
        adapter.add(new ListItem(fileName, "","", "asdasd.mp4", "", "", "", "", "", ""));
        adapter.add(new ListItem("Step2","","", "asdasd.mp4", "", "", "", "", "", ""));
        adapter.add(new ListItem("Step3","","", "asdasd.mp4", "", "", "", "", "", ""));
        adapter.add(new ListItem("Step4","","", "asdasd.mp4", "", "", "", "", "", ""));
        adapter.add(new ListItem("Step5","","", "asdasd.mp4", "", "", "", "", "", ""));
        adapter.add(new ListItem("Step6","","", "asdasd.mp4", "", "", "", "", "", ""));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

//        submit_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RecordMain.this, Ending.class);
//                startActivity(intent);
//
//            }
//        });

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, MainRecord.class);

        intent.putExtra("ArticleNumber", recordList.get(position).getName() + "");
        intent.putExtra("name", name);
        intent.putExtra("idx", position);
        startActivityForResult(intent, TARGET_NAME);
       // startActivity(intent);
    }
}
