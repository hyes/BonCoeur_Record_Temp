package com.example.hyes.myrecord_bc;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NameList extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private static final int TARGET = 1;
    private ArrayList<NameItem> nameList;
    private ListView listView;
    private TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_list);


        listView = (ListView)findViewById(R.id.nameListView);
       // tv = (TextView)findViewById(R.id.textView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }


    private void listView(){
        Dao dao = new Dao(getApplicationContext());
        nameList = dao.getArticleList();

        if(nameList.isEmpty()){
            nameList.add(new NameItem(0, "EMPTY"));
        }
            NameAdapter nameAdapter = new NameAdapter(this, R.layout.custom_name_list, nameList);
            listView.setAdapter(nameAdapter);
            listView.setOnItemClickListener(this);
    }

    private final Handler handler = new Handler();

    private void refreshData(){
        new Thread(){
            public void run(){
               // Proxy proxy = new Proxy();
              //  String jsonData = proxy.getJson();

               // Dao dao = new Dao(getApplicationContext());

//                dao.insertJsonData(jsonData);
//                Log.i("test", jsonData);

                handler.post(new Runnable(){
                    public void run(){

                        Log.i("test", "handler ON!!!");
                        listView();

                    }
                });
            }
        }.start();
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

        Intent intent = new Intent(this, RecordingDataList.class);

        intent.putExtra("Name", nameList.get(position).getName());
        intent.putExtra("id", nameList.get(position).getId());


        Log.i("test", "intent " + nameList.get(position).getId());
        startActivity(intent);

    }


}
