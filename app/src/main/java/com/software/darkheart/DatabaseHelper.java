package com.software.darkheart;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import com.software.darkheart.Interfaces.Money;
import com.software.darkheart.model.Income;
import com.software.darkheart.model.Period;
import com.software.darkheart.model.Expense;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "mm_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create tables
        db.execSQL(Period.CREATE_TABLE);
        db.execSQL(Expense.CREATE_TABLE);
        db.execSQL(Income.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Period.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Expense.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Income.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    //
    // TABLE: Period
    //

    //
    // Insert Period Record
    //
    public long insert_Period_record(int month, int year) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put( Period.PERIOD_MONTH, month);
        values.put( Period.PERIOD_YEAR, year);

        long id = db.insert(Period.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    //
    // Get All Period Records
    //
    public List<Period> get_all_periods() {
        List<Period> periods = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Period.TABLE_NAME + " ORDER BY " +
                Period.PERIOD_MONTH +", " +Period.PERIOD_YEAR + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Period period = new Period();
                period.setId(cursor.getInt(cursor.getColumnIndex(Period.PERIOD_ID)));
                period.setYear(cursor.getInt(cursor.getColumnIndex(Period.PERIOD_YEAR)));
                period.setMonth(cursor.getInt(cursor.getColumnIndex(Period.PERIOD_MONTH)));

                periods.add(period);
            } while (cursor.moveToNext());
        }
        db.close();

        return periods;
    }

    //
    // TABLE: Income
    //

    //
    // Insert Income Record
    //
    public long insert_Income_record(int income_period_id, float income_value, String income_comment) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put( Income.INCOME_VALUE, income_value );
        values.put( Income.INCOME_COMMENT, income_comment);
        values.put( Income.INCOME_PERIOD_ID, income_period_id);

        long id = db.insert(Income.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    //
    // Get All Income For Selected Period
    //
    public List<Money> get_all_income_ForPeriod(int periodID) {
        List<Money> incomes = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Income.TABLE_NAME + " WHERE " +
                Income.INCOME_PERIOD_ID + " = " + String.valueOf(periodID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Income income = new Income();
                income.setId(cursor.getInt(cursor.getColumnIndex(Income.INCOME_ID)));
                income.setValue(cursor.getFloat(cursor.getColumnIndex(Income.INCOME_VALUE)));
                income.setComment(cursor.getString(cursor.getColumnIndex(Income.INCOME_COMMENT)));
                income.setIsIncome(true);
                incomes.add(income);
            } while (cursor.moveToNext());
        }
        db.close();

        return incomes;
    }


    //
    // Get All Income Record
    //
    public Money get_Income_record(long incomeID){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Income.TABLE_NAME,
                new String[]{
                    Income.INCOME_ID,
                    Income.INCOME_PERIOD_ID,
                    Income.INCOME_VALUE,
                    Income.INCOME_COMMENT
                },
                Income.INCOME_ID + " = ?",
                new String[]{String.valueOf(incomeID)}, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();
        Money income = new Income();


        income.setId(cursor.getInt(cursor.getColumnIndex(Income.INCOME_ID)));
        income.setPeriodID(cursor.getInt(cursor.getColumnIndex(Income.INCOME_PERIOD_ID)));
        income.setComment(cursor.getString(cursor.getColumnIndex(Income.INCOME_COMMENT)));
        income.setValue(cursor.getFloat(cursor.getColumnIndex(Income.INCOME_VALUE)));

        cursor.close();

        return income;
    }

    //
    // Delete Income Record
    //
    public void delete_Income_record(int incomeID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Income.TABLE_NAME, Income.INCOME_ID + " = ?",
                new String[]{String.valueOf(incomeID)});
        db.close();
    }


    //
    // TABLE: Expense
    //

    //
    // Insert Expense Record
    //
    public long insert_Expense_record(int expense_period_id, float expense_value, String expense_comment) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put( Expense.EXPENSE_VALUE, expense_value );
        values.put( Expense.EXPENSE_COMMENT, expense_comment);
        values.put( Expense.EXPENSE_PERIOD_ID, expense_period_id);

        long id = db.insert(Expense.TABLE_NAME, null, values);
        db.close();

        return id;
    }
    //
    // Get All Expenses For Selected Period
    //
    public List<Money> get_all_expenses_ForPeriod(int periodID) {
        List<Money> expenses = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Expense.TABLE_NAME + " WHERE " +
                Expense.EXPENSE_PERIOD_ID + " = " + String.valueOf(periodID);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Expense expense = new Expense();
                expense.setId(cursor.getInt(cursor.getColumnIndex(Expense.EXPENSE_ID)));
                expense.setValue(cursor.getFloat(cursor.getColumnIndex(Expense.EXPENSE_VALUE)));
                expense.setComment(cursor.getString(cursor.getColumnIndex(Expense.EXPENSE_COMMENT)));
                expense.setIsIncome(false);
                expenses.add(expense);
            } while (cursor.moveToNext());
        }
        db.close();

        return expenses;
    }
    //
    // Get Expense Record
    //
    public Money get_Expense_record(long expenseID){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Expense.TABLE_NAME,
                new String[]{
                        Expense.EXPENSE_ID,
                        Expense.EXPENSE_PERIOD_ID,
                        Expense.EXPENSE_VALUE,
                        Expense.EXPENSE_COMMENT
                },
                Expense.EXPENSE_ID + " = ?",
                new String[]{String.valueOf(expenseID)}, null, null, null, null);

        if (cursor != null) cursor.moveToFirst();
        Money expense = new Expense();


        expense.setId(cursor.getInt(cursor.getColumnIndex(Expense.EXPENSE_ID)));
        expense.setPeriodID(cursor.getInt(cursor.getColumnIndex(Expense.EXPENSE_PERIOD_ID)));
        expense.setComment(cursor.getString(cursor.getColumnIndex(Expense.EXPENSE_COMMENT)));
        expense.setValue(cursor.getFloat(cursor.getColumnIndex(Expense.EXPENSE_VALUE)));

        cursor.close();

        return expense;
    }

    //
    // Delete Expense Record
    //
    public void delete_Expense_record(int expenseID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Expense.TABLE_NAME, Expense.EXPENSE_ID + " = ?",
                new String[]{String.valueOf(expenseID)});
        db.close();
    }
}

