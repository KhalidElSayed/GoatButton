package com.alorma.floatingbutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DirectionalScrollListener;
import android.widget.GoatLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ListView listView = (ListView) findViewById(R.id.list);

        GoatLayout ly = (GoatLayout) findViewById(R.id.ly);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, getItems());

        listView.setAdapter(adapter);

        View view = LayoutInflater.from(this).inflate(R.layout.float_button, ly, false);
        ly.setFloatView(view);

        ly.setMarginBottom(30);
        ly.setMarginRight(20);
        ly.setDurationOut(200);
        ly.setDurationIn(200);
        ly.setTimeShowOnStop(300);
    }

    public List<String> getItems() {
        List<String> items = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            items.add("A " + i);
        }
        return items;
    }
}
