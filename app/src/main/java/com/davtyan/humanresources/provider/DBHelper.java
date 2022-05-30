package com.davtyan.humanresources.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import static com.davtyan.humanresources.ConstValues.*;

import com.davtyan.humanresources.R;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "HumanResourses.db";
    private static final int DB_VERSION = 3;
    private static final String TABLE_NAME = "employees";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                DIVISION + " TEXT, " +
                SALARY + " INTEGER);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, employee.getName());
        cv.put(DIVISION, employee.getDivision());
        cv.put(SALARY, employee.getSalary());
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1) {
            Toast.makeText(context, R.string.add_fail, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.add_success, Toast.LENGTH_SHORT).show();
        }
    }

    Cursor fetchData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateEmployeeData(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, employee.getName());
        cv.put(DIVISION, employee.getDivision());
        cv.put(SALARY, employee.getSalary());

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{employee.getId()});
        if(result == -1) {
            Toast.makeText(context, R.string.update_fail, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.update_success, Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteEmployeeData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{id});
        if(result == -1) {
            Toast.makeText(context, R.string.delete_fail, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.delete_success, Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllEmployeesData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
