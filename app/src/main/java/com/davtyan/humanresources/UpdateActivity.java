package com.davtyan.humanresources;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    Employee employee;
    EditText etName;
    EditText etDivision;
    EditText etSalary;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etName = findViewById(R.id.etNameUpdate);
        etDivision = findViewById(R.id.etDivisionUpdate);
        etSalary = findViewById(R.id.etSalaryUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        updateIntentData();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(UpdateActivity.this);
                Employee e = new Employee();
                e.setId(employee.getId());
                e.setName(etName.getText().toString().trim());
                e.setDivision(etDivision.getText().toString().trim());
                e.setSalary(etSalary.getText().toString().trim());
                dbHelper.updateEmployeeData(e);
            }
        });
    }

    void updateIntentData() {
        Intent intent = getIntent();
        if(intent.hasExtra("id") &&
           intent.hasExtra("name") &&
           intent.hasExtra("division") &&
           intent.hasExtra("salary")
        ) {
            employee = new Employee();
            employee.setId(intent.getStringExtra("id"));
            employee.setName(intent.getStringExtra("name"));
            employee.setDivision(intent.getStringExtra("division"));
            employee.setSalary(intent.getStringExtra("salary"));

            etName.setText(employee.getName());
            etDivision.setText(employee.getDivision());
            etSalary.setText(employee.getSalary());
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}