package com.alorma.floatingbutton;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GoatLayout;
import android.widget.ListView;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        setTitle(R.string.action_settings);

        ListView listView = (ListView) findViewById(R.id.list);

        GoatLayout ly = (GoatLayout) findViewById(R.id.ly);

        Button btn = new Button(this);
        btn.setText("Beeeeh");

        String[] items = new String[] {"A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items);

        listView.setAdapter(adapter);

        Button button = new Button(this);
        GoatLayout.LayoutParams params = new GoatLayout.LayoutParams(200, 200);
        button.setLayoutParams(params);
        ly.setButton(button);
    }
}
