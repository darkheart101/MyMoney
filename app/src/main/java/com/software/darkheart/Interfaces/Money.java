package com.software.darkheart.Interfaces;

public interface Money {


    int getId();

    int getPeriodID();

    float get_Value();

    String get_Comment();

    void setPeriodID(int periodid);

    void setComment(String comment);

    void setValue(float value);

    String getTimestamp();

    void setId(int id);

    void setTimestamp(String timestamp);

    void setIsIncome(boolean is_income_or_expense);

    boolean getIsIncome();
}
