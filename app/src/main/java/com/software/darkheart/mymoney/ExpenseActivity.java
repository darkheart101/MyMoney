package com.software.darkheart.mymoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.software.darkheart.DatabaseHelper;
import com.software.darkheart.Interfaces.Money;
import com.software.darkheart.model.Expense;

public class ExpenseActivity extends AppCompatActivity {

    private DatabaseHelper db;
    int periodID;
    int expenseID;
    float expense_value = 0;
    String expense_comment = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = DatabaseHelper.getDBInstance(this);
        periodID = (int)getIntent().getExtras().get("periodID");
        expenseID = (int)getIntent().getExtras().get("ExpenseID");

        if( expenseID > 0 ){

            Money m = db.get_Expense_record(expenseID);

            TextView txt_expense_value = findViewById(R.id.txt_expense_value);
            float expense_value = m.get_Value();
            txt_expense_value.setText(String.valueOf(expense_value));

            TextView txt_expense_comment = findViewById(R.id.txt_expense_comment);
            String expense_comment = m.get_Comment();
            txt_expense_comment.setText(expense_comment);
        }

        // Cancel Button
        Button btn_cancel_expense = findViewById(R.id.btn_cancel_expense);
        btn_cancel_expense.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), IncomeSpendsActivity.class);
                i.putExtra("periodID", periodID);
                startActivity(i);
            }
        });

        // Delete Button
        Button btn_delete_expense = findViewById(R.id.btn_delete_expense);
        btn_delete_expense.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Delete record
                if( expenseID > 0){ db.delete_Expense_record(expenseID); }

                Intent i = new Intent(getBaseContext(), IncomeSpendsActivity.class);
                i.putExtra("periodID", periodID);
                startActivity(i);
            }
        });

        // Save Button
        Button btn_save_expense = findViewById(R.id.btn_save_expense);
        btn_save_expense.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                TextView txt_expense_value = findViewById(R.id.txt_expense_value);

                // Handle empty value
                if(
                    txt_expense_value.getText().toString() == null
                    ||
                    txt_expense_value.getText().toString().length() == 0
                ) txt_expense_value.setText("0");
                expense_value = Float.valueOf( txt_expense_value.getText().toString() );


                TextView txt_expense_comment = findViewById(R.id.txt_expense_comment);
                expense_comment = txt_expense_comment.getText().toString();

                if( expenseID > 0) {
                    Expense exp = new Expense();
                    exp.setId(expenseID);
                    exp.setPeriodID(periodID);
                    exp.setValue(expense_value);
                    exp.setComment(expense_comment);
                    db.update_Expense_record(exp);
                }
                else
                    db.insert_Expense_record(periodID, expense_value,expense_comment);
                Intent i = new Intent(getBaseContext(), IncomeSpendsActivity.class);
                i.putExtra("periodID", periodID);
                startActivity(i);
            }
        });
    }

}
