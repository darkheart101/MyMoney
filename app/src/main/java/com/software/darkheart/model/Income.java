package com.software.darkheart.model;

import com.software.darkheart.Interfaces.Money;

public class Income implements Money {
    public static final String TABLE_NAME = "mmIncome";

    public static final String INCOME_ID = "IncomeID";
    public static final String INCOME_PERIOD_ID = "IncomePeriodID";
    public static final String INCOME_COMMENT = "IncomeComment";
    public static final String INCOME_VALUE = "IncomeValue";
    public static final String INCOME_TIMESTAMP = "timestamp";

    private int income_id;
    private int income_period_id;
    private String income_comment;
    private float income_value;
    private String timestamp;

    private boolean IsThisAnIncome;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
                    + INCOME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + INCOME_PERIOD_ID + " INTEGER,"
                    + INCOME_COMMENT + " TEXT,"
                    + INCOME_VALUE + " REAL,"
                    + INCOME_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public Income() {
    }

    public Income(int incomeID, int incomePeriodID, String  incomeComment, float incomeValue, String timestamp) {
        this.income_id = incomeID;
        this.income_period_id = incomePeriodID;
        this.income_comment = incomeComment;
        this.income_value = incomeValue;
        this.timestamp = timestamp;
    }

    public int getId() {
        return income_id;
    }

    public int getPeriodID() {
        return income_period_id;
    }

    public float get_Value() {
        return income_value;
    }

    public String get_Comment() {
        return income_comment;
    }

    public void setPeriodID(int periodid) {
        this.income_period_id = periodid;
    }

    public void setComment(String comment) {
        this.income_comment = comment;
    }

    public void setValue(float value) {
        this.income_value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.income_id = id;
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
