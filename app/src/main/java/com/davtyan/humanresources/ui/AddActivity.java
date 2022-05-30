package com.davtyan.humanresources.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.davtyan.humanresources.provider.DBHelper;
import com.davtyan.humanresources.provider.Employee;
import com.davtyan.humanresources.R;

public class AddActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etDivision;
    private EditText etSalary;
    private Button btnAdd;

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
                Employee employee = new Employee();
                employee.setName(etName.getText().toString().trim());
                employee.setDivision(etDivision.getText().toString().trim());
                employee.setSalary(etSalary.getText().toString().trim());
                dbHelper.addEmployee(employee);
            }
        });
    }
}