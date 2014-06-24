package com.alorma.floatingbutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GoatMenuLayout;
import android.widget.ListView;
import android.widget.MenuItemView;
import android.widget.MenuLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity implements MenuItemView.OnMenuItemClickListener {

    private MenuItemView itemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        ListView listView = (ListView) findViewById(R.id.list);

        GoatMenuLayout ly = (GoatMenuLayout) findViewById(R.id.ly);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, getItems());

        listView.setAdapter(adapter);

        ly.setMarginBottom(30);
        ly.setMarginRight(20);
        ly.setDurationOut(200);
        ly.setDurationIn(200);
        ly.setTimeShowOnStop(300);

        ly.inflate(R.menu.my1, this);

        itemView = (MenuItemView) findViewById(R.id.icon);
        itemView.setOnMenuItemClickListener(this);

        MenuLayout menuLayout2 = (MenuLayout) findViewById(R.id.menu2);
        menuLayout2.inflate(R.menu.my2, this);
    }

    public List<String> getItems() {
        List<String> items = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            items.add("A " + i);
        }
        return items;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Hurray: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        itemView.setMenuItem(item);
        return true;
    }
}
