package com.software.darkheart.model;

import com.software.darkheart.Interfaces.Money;

public class Expense implements Money {
    public static final String TABLE_NAME = "mmExpenses";

    public static final String EXPENSE_ID = "ExpenseID";
    public static final String EXPENSE_PERIOD_ID = "ExpensePeriodID";
    public static final String EXPENSE_COMMENT = "ExpenseComment";
    public static final String EXPENSE_VALUE = "ExpenseValue";
    public static final String EXPENSE_TIMESTAMP = "timestamp";

    private int expense_id;
    private int expense_period_id;
    private String expense_comment;
    private float expense_value;
    private String timestamp;

    private boolean IsThisAnIncome;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + EXPENSE_PERIOD_ID + " INTEGER,"
                    + EXPENSE_COMMENT + " TEXT,"
                    + EXPENSE_VALUE + " REAL,"
                    + EXPENSE_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public Expense() {
    }

    public Expense(int expenseID, int expensePeriodID, String  expenseComment, float expenseValue, String timestamp) {
        this.expense_id = expenseID;
        this.expense_period_id = expensePeriodID;
        this.expense_comment = expenseComment;
        this.expense_value = expenseValue;
        this.timestamp = timestamp;
    }

    public int getId() {
        return expense_id;
    }

    public int getPeriodID() {
        return expense_period_id;
    }

    public float get_Value() {
        return expense_value;
    }

    public String get_Comment() {
        return expense_comment;
    }

    public void setPeriodID(int periodid) {
        this.expense_period_id = periodid;
    }

    public void setComment(String comment) {
        this.expense_comment = comment;
    }

    public void setValue(float value) {
        this.expense_value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.expense_id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public void setIsIncome(boolean is_income_or_expense) {
        IsThisAnIncome = is_income_or_expense;
    }

    @Override
    public boolean getIsIncome() {
        return IsThisAnIncome;
    }
}
