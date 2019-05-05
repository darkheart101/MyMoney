package com.software.darkheart.Factories;

import com.software.darkheart.mymoney.Month;

public class MonthFactory {

    public static Month MonthInstance_(String month_name){
        int month_id = -1;
        month_id = MonthFactory.convert_month_name_to_month_id(month_name);

        if(month_id < 0) throw  new IllegalArgumentException("Month ID Error!");


        return new Month(month_id);

    }

    public static int convert_month_name_to_month_id(String month_name){

        int month_id = -1;
        month_name = month_name.toUpperCase();

        switch(month_name) {
            case "JANUARY":
                month_id = 1;
                break;
            case "FEBRUARY":
                month_id = 2;
                break;
            case "MARCH":
                month_id = 3;
                break;
            case "APRIL":
                month_id = 4;
                break;
            case "MAY":
                month_id = 5;
                break;
            case "JUNE":
                month_id = 6;
                break;
            case "JULY":
                month_id = 7;
                break;
            case "AUGUST":
                month_id = 8;
                break;
            case "SEPTEMBER":
                month_id = 9;
                break;
            case "OCTOBER":
                month_id = 10;
                break;
            case "NOVEMBER":
                month_id = 11;
                break;
            case "DECEMBER":
                month_id = 12;
                break;
        }

        return month_id;

    }
}
