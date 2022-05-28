package com.davtyan.humanresources;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvPeople;
    FloatingActionButton btnAdd;
    DBHelper dbHelper;
    ArrayList<Employee> employees;
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
        employees = new ArrayList<Employee>();

        fetchData();

        rvPeople.setLayoutManager(new LinearLayoutManager(this));
        customAdapter = new CustomAdapter(MainActivity.this, this, employees);
        rvPeople.setAdapter(customAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }

    void fetchData() {
        Cursor cursor = dbHelper.fetchData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                Employee employee = new Employee();
                employee.setId(cursor.getString(0));
                employee.setName(cursor.getString(1));
                employee.setDivision(cursor.getString(2));
                employee.setSalary(cursor.getString(3));
                employees.add(employee);
            }
        }
    }
}