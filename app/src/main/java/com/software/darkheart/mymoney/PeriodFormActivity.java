package com.software.darkheart.mymoney;

import com.software.darkheart.Factories.MonthFactory;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.software.darkheart.DatabaseHelper;
import com.software.darkheart.Factories.MonthFactory;

public class PeriodFormActivity extends AppCompatActivity {

    public DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_form);
        db = DatabaseHelper.getDBInstance(this);

        // Month Spinner
        Spinner spn_month = findViewById(R.id.spn_month);
        ArrayAdapter<Month> spn_month_ArrayAdapter;

        Month January = MonthFactory.MonthInstance_("January");
        Month February = MonthFactory.MonthInstance_("February");
        Month March = MonthFactory.MonthInstance_("March");
        Month April = MonthFactory.MonthInstance_("April");
        Month May = MonthFactory.MonthInstance_("May");
        Month June = MonthFactory.MonthInstance_("June");
        Month July = MonthFactory.MonthInstance_("July");
        Month August = MonthFactory.MonthInstance_("August");
        Month September = MonthFactory.MonthInstance_("September");
        Month October = MonthFactory.MonthInstance_("October");
        Month November = MonthFactory.MonthInstance_("November");
        Month December = MonthFactory.MonthInstance_("December");

        spn_month_ArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, new Month[] {
                        January, February, March, April, May, June, July, August, September,
                        October, November, December
        });
        spn_month.setAdapter(spn_month_ArrayAdapter);

        // Year Spinner
        List<Integer> yearArray = new ArrayList<>();
        int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);
        for( int year = CurrentYear; year >= 2017; year-- )
            yearArray .add( year);

        Spinner spn_year = findViewById(R.id.spn_year);
        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_expandable_list_item_1, yearArray);
        spn_year.setAdapter(yearAdapter);



        final Button btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent activityChangeIntent = new Intent( getBaseContext(), MainActivity.class);

                PeriodFormActivity.this.startActivity(activityChangeIntent);
            }
        });


        final Button btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Spinner spn_month = findViewById(R.id.spn_month);
                Month m = (Month) spn_month.getSelectedItem();

                Spinner spn_year = findViewById(R.id.spn_year);
                int year= (int)spn_year.getSelectedItem();


                db.insert_Period_record(m.id, year);

                // Perform action on click
                Intent activityChangeIntent = new Intent( getBaseContext(), MainActivity.class);
                PeriodFormActivity.this.startActivity(activityChangeIntent);
            }
        });


    }


}
