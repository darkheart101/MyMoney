package com.software.darkheart.mymoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.software.darkheart.DatabaseHelper;
import com.software.darkheart.Interfaces.Money;
import com.software.darkheart.model.Income;

public class IncomeActivity extends AppCompatActivity {

    int periodID;
    int incomeID = 0;
    private DatabaseHelper db;
    String income_comment = "";
    float income_value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //db = new DatabaseHelper(this);
        db = DatabaseHelper.getDBInstance(this);
        periodID = (int)getIntent().getExtras().get("periodID");
        incomeID = (int)getIntent().getExtras().get("IncomeID");

        if( incomeID > 0 ){

            Money m = db.get_Income_record(incomeID);

            TextView txt_income_value = findViewById(R.id.txt_income_value);
            float income_value = m.get_Value();
            txt_income_value.setText(String.valueOf(income_value));

            TextView txt_income_comment = findViewById(R.id.txt_income_comment);
            String income_comment = m.get_Comment();
            txt_income_comment.setText(income_comment);
        }


        // Cancel Button
        Button btn_cancel_income = findViewById(R.id.btn_cancel_income);
        btn_cancel_income.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), IncomeSpendsActivity.class);
                i.putExtra("periodID", periodID);
                startActivity(i);
            }
        });

        // Delete Button
        Button btn_delete_income = findViewById(R.id.btn_delete_income);
        btn_delete_income.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Delete record
                if( incomeID > 0){ db.delete_Income_record(incomeID); }

                Intent i = new Intent(getBaseContext(), IncomeSpendsActivity.class);
                i.putExtra("periodID", periodID);
                startActivity(i);
            }
        });

        // Save Button
        Button btn_save_income = findViewById(R.id.btn_save_income);
        btn_save_income.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TextView txt_income_value = findViewById(R.id.txt_income_value);

                // Handle empty value
                if(
                    txt_income_value.getText().toString() == null
                    ||
                    txt_income_value.getText().toString().length() == 0
                )txt_income_value.setText("0");


                income_value = Float.valueOf( txt_income_value.getText().toString() );


                TextView txt_income_comment = findViewById(R.id.txt_income_comment);
                income_comment = txt_income_comment.getText().toString();
                if(incomeID > 0){
                    Income inc = new Income();
                    inc.setId(incomeID);
                    inc.setPeriodID(periodID);
                    inc.setValue(income_value);
                    inc.setComment(income_comment);
                    db.update_Income_record(inc);
                }
                else {
                    db.insert_Income_record(periodID, income_value, income_comment);
                }
                Intent i = new Intent(getBaseContext(), IncomeSpendsActivity.class);
                i.putExtra("periodID", periodID);
                startActivity(i);
            }
        });
    }

}
