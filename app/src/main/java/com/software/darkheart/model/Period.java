package com.software.darkheart.model;

public class Period {
    public static final String TABLE_NAME = "mmPeriods";

    public static final String PERIOD_ID = "PeriodID";
    public static final String PERIOD_MONTH = "PeriodMonth";
    public static final String PERIOD_YEAR = "PeriodYear";
    public static final String PERIOD_TIMESTAMP = "timestamp";

    private int period_id;
    private int period_month;
    private int period_year;
    private String timestamp;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + PERIOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + PERIOD_MONTH + " INTEGER,"
                    + PERIOD_YEAR + " INTEGER,"
                    + PERIOD_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public Period() {
    }

    public Period(int periodID, int periodMonth, int  periodYear, String timestamp) {
        this.period_id = periodID;
        this.period_month = periodMonth;
        this.period_year = periodYear;
        this.timestamp = timestamp;
    }

    public int getId() {
        return period_id;
    }

    public int getYear() {
        return period_year;
    }

    public int getMonth() {
        return period_month;
    }


    public void setYear(int year) {
        this.period_year = year;
    }

    public void setMonth(int month) {
        this.period_month = month;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.period_id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}