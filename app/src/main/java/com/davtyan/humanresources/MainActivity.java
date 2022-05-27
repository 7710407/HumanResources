package com.davtyan.humanresources;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvPeople;
    FloatingActionButton btnAdd;
    DBHelper dbHelper;
    ArrayList<String> ids;
    ArrayList<String> names;
    ArrayList<String> divisions;
    ArrayList<String> salaries;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPeople = findViewById(R.id.rvPeople);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new DBHelper(MainActivity.this);
        ids = new ArrayList<String>();
        names = new ArrayList<String>();
        divisions = new ArrayList<String>();
        salaries = new ArrayList<String>();

        fetchData();

        customAdapter = new CustomAdapter(MainActivity.this, ids, names, divisions, salaries);
        rvPeople.setAdapter(customAdapter);
        rvPeople.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void fetchData() {
        Cursor cursor = dbHelper.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                ids.add(cursor.getString(0));
                names.add(cursor.getString(1));
                divisions.add(cursor.getString(2));
                salaries.add(cursor.getString(3));
            }
        }
    }
}