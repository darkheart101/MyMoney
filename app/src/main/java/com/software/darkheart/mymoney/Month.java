package com.software.darkheart.mymoney;

public class Month {

    public int id = 0;
    public String name = "";

    // A simple constructor for populating our member variables for this tutorial.
    public Month( int _id )
    {
        id = _id;
        name = get_month_name(_id);
    }

    public String toString()
    {
        return( name );
    }

    public static String get_month_name(int id){
        String month_name = "";

        switch(id) {
            case 1:
                month_name = "JANUARY";
                break;
            case 2:
                month_name = "FEBRUARY";
                break;
            case 3:
                month_name = "MARCH";
                break;
            case 4:
                month_name = "APRIL";
                break;
            case 5:
                month_name = "MAY";
                break;
            case 6:
                month_name = "JUNE";
                break;
            case 7:
                month_name = "JULY";
                break;
            case 8:
                month_name = "AUGUST";
                break;
            case 9:
                month_name = "SEPTEMBER";
                break;
            case 10:
                month_name = "OCTOBER";
                break;
            case 11:
                month_name = "NOVEMBER";
                break;
            case 12:
                month_name = "DECEMBER";
                break;
        }

        return month_name;
    }
}
