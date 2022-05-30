package com.davtyan.humanresources.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.davtyan.humanresources.provider.DBHelper;
import com.davtyan.humanresources.provider.Employee;
import com.davtyan.humanresources.R;

import static com.davtyan.humanresources.ConstValues.*;

public class UpdateActivity extends AppCompatActivity {
    private Employee employee;
    private EditText etName;
    private EditText etDivision;
    private EditText etSalary;
    private Button btnUpdate;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etName = findViewById(R.id.etNameUpdate);
        etDivision = findViewById(R.id.etDivisionUpdate);
        etSalary = findViewById(R.id.etSalaryUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
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
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle(employee.getName());
        }
    }

    void updateIntentData() {
        Intent intent = getIntent();
        if(intent.hasExtra(ID) &&
           intent.hasExtra(NAME) &&
           intent.hasExtra(DIVISION) &&
           intent.hasExtra(SALARY)
        ) {
            employee = new Employee();
            employee.setId(intent.getStringExtra(ID));
            employee.setName(intent.getStringExtra(NAME));
            employee.setDivision(intent.getStringExtra(DIVISION));
            employee.setSalary(intent.getStringExtra(SALARY));

            etName.setText(employee.getName());
            etDivision.setText(employee.getDivision());
            etSalary.setText(employee.getSalary());
        } else {
            Toast.makeText(this, R.string.no_data, Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.delete_) + employee.getName() + "?");
        builder.setMessage(getString(R.string.are_you_sure) + employee.getName() + "?");
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper dbHelper = new DBHelper(UpdateActivity.this);
                dbHelper.deleteEmployeeData(employee.getId());
                finish();
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        builder.create().show();
    }
}