package com.davtyan.humanresources.provider;

import android.database.Cursor;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.davtyan.humanresources.provider.DBHelper;
import com.davtyan.humanresources.provider.Employee;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    //private BDQueryTask dbExecutor = new BDQueryTask();
    private DBHelper dbHelper;

    public MutableLiveData<ArrayList<Employee>> liveData = new MutableLiveData<>();

    public void fetchData(){
        new BDQueryTask().execute();
    }

    public void setDbHelper(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    class BDQueryTask extends AsyncTask<Void, Void, ArrayList<Employee>> {
        @Override
        protected ArrayList<Employee> doInBackground(Void... voids) {
            return fetchDataFromDb();
        }

        @Override
        protected void onPostExecute(ArrayList<Employee> employees) {
            super.onPostExecute(employees);
            liveData.setValue(employees);
        }
    }

    private ArrayList<Employee> fetchDataFromDb() {
        ArrayList<Employee> employees = new ArrayList<>();
        Cursor cursor = dbHelper.fetchData();
        if(cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                Employee employee = new Employee();
                employee.setId(cursor.getString(0));
                employee.setName(cursor.getString(1));
                employee.setDivision(cursor.getString(2));
                employee.setSalary(cursor.getString(3));
                employees.add(employee);
            }
        }
        return employees;
    }
}
