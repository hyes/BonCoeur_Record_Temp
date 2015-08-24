package com.example.hyes.myrecord_bc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hyes on 2015. 4. 16..
 */

public class List extends ActionBarActivity {

    private ArrayList<ListItem> temp = new ArrayList<>();
    ListView view;
    private String name;

//    String[] check_nums = {"1", "0", "1", "0", "0"};
//    String[] titles = {"20150307", "20150301", "20150218", "라라라", "마마마"};
//    String[] contents ={"abc@google.com", "def@google.com", "ghi@nanan.com", "라내용내용", "마내용내용"};

    private RecordingDataPreference pref;

    String callValue1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recorded_list);

        Intent intent = getIntent();
        name =  intent.getStringExtra("name");
        view = (ListView)findViewById(R.id.listView);


        ItemAdapter adapter = new ItemAdapter();

        adapter.add(new ListItem("1","1","1","1","1","1","1","1","1","1"));
        adapter.add(new ListItem("2","2","1","1","1","1","1","1","1","1"));
        adapter.add(new ListItem("3","3","1","1","1","1","1","1","1","1"));

        try {
            callValue1 = pref.getValue(name + "record1", "");

        }catch(NullPointerException e){
            e.printStackTrace();
        }
//        final String callValue2 = pref.getValue(name + "record2", "");
//        final String callValue3 = pref.getValue(name + "record3", "");
//        final String callValue4 = pref.getValue(name + "record4", "");
        try {
            Log.i("test1", callValue1);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
//        Log.i("test1", callValue2);
//        Log.i("test1", callValue3);
//        Log.i("test1", callValue4);
//        final String call1 = pref.getValue(name + "capture1", "");
//        final String call2 = pref.getValue(name + "capture2", "");
//        final String call3 = pref.getValue(name + "capture3", "");
//        final String call4 = pref.getValue(name + "capture4", "");
//        Log.i("test1", call1);
//        Log.i("test1", call2);
//        Log.i("test1", call3);
//        Log.i("test1", call4);

        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // Intent intent = new Intent(getApplicationContext(), EachView.class);
               // startActivity(intent);

                Toast.makeText(getApplicationContext(), "클릭 위치는: " + position, Toast.LENGTH_SHORT).show();
               //Log.i("TEST", temp.get(position).getTitle() + ", " + temp.get(position).getContent());
            }
        });

    }





    class ItemAdapter extends BaseAdapter {
//        ArrayList<Item> temp = new ArrayList<Item>();

        @Override
        public int getCount() {
            return temp.size();
        }

        @Override
        public Object getItem(int position) {
            return temp.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void add(ListItem item){
            temp.add(item);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ListItemView view = null;
            if(convertView == null){
                view = new ListItemView(getApplicationContext());
            }else{
                view = (ListItemView)convertView;
            }

            ListItem current = (ListItem) temp.get(position);

            view.setName(current.getName());
            view.setDate(current.getDate());
//            view.setRecord1(current.getRecordFile1());
//            view.setRecord2(current.getRecordFile2());
//            view.setRecord3(current.getRecordFile3());
//            view.setRecord4(current.getRecordFile4());
//            view.setPicture1(current.getCaptureFile1());
//            view.setPicture2(current.getCaptureFile2());
//            view.setPicture3(current.getCaptureFile3());
//            view.setPicture4(current.getCaptureFile4());

            return view;
        }
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
}
