package com.davtyan.humanresources;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText etName;
    EditText etDivision;
    EditText etSalary;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = findViewById(R.id.etName);
        etDivision = findViewById(R.id.etDivision);
        etSalary = findViewById(R.id.etSalary);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(AddActivity.this);
                dbHelper.addEmployee(etName.getText().toString().trim(),
                        etDivision.getText().toString().trim(),
                        Integer.valueOf(etSalary.getText().toString().trim()));
            }
        });
    }
}