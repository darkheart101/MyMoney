package com.software.darkheart.mymoney;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.software.darkheart.DatabaseHelper;
import com.software.darkheart.model.Period;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //db = new DatabaseHelper(this);
        db = DatabaseHelper.getDBInstance(this);

        List<Period> periods = db.get_all_periods();

        ListAdapter la = new PeriodAdapter(this, periods );
        ListView lv = findViewById(R.id.lst_period);
        lv.setAdapter(la);
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Period selItem = (Period) parent.getAdapter().getItem(position);
                        int selected_period_id = selItem.getId();

                        Intent i = new Intent(getBaseContext(), IncomeSpendsActivity.class);
                        i.putExtra("periodID", selected_period_id);
                        startActivity(i);

                    }
                }
        );

        // Create New Period
        FloatingActionButton fab_new_period = findViewById(R.id.fab_new_period);
        fab_new_period.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getBaseContext(), PeriodFormActivity.class);
                        i.putExtra("editRecID", 0);
                        startActivity(i);
                    }
                }
        );
    }
}
