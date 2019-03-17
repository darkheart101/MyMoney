package com.software.darkheart.mymoney;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.software.darkheart.DatabaseHelper;
import com.software.darkheart.Interfaces.Money;
import com.software.darkheart.model.Expense;
import com.software.darkheart.model.Period;


import java.util.List;

public class IncomeSpendsActivity extends AppCompatActivity {

    int periodID;
    private DatabaseHelper db;
    MoneyMove money_move;
    List<Money> money_conjuction;

    float sum_income = 0;
    float sum_expense = 0;
    float total_money = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_spends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);
        periodID = (int)getIntent().getExtras().get("periodID");

        List<Money> incomes = db.get_all_income_ForPeriod(periodID);
        List<Money> expenses = db.get_all_expenses_ForPeriod(periodID);

        money_move = new MoneyMove(incomes, expenses);
        money_conjuction = money_move.get_MoveyMove_List();

        ListAdapter la = new MoneyAdapter(this,money_conjuction );
        ListView lv = findViewById(R.id.lst_money);
        lv.setAdapter(la);

        // Calculate Total Income and Total Expenses
        for(Money income : incomes){ sum_income += income.get_Value(); }
        for(Money expense : expenses){ sum_expense += expense.get_Value(); }

        View footerView =  ( (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.sums_footer, null, false);

        total_money = sum_income - sum_expense;
        TextView txt_total_money = footerView.findViewById(R.id.txt_total_money);
        txt_total_money.setText(String.valueOf(total_money ));
        if(total_money < 0) txt_total_money.setTextColor(Color.RED);
        if(total_money >= 0) txt_total_money.setTextColor(Color.GREEN);

        lv.addFooterView(footerView);


        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Money selItem = (Money) parent.getAdapter().getItem(position);
                        int selected_id = selItem.getId();
                        boolean isRecordIncome = selItem.getIsIncome();

                        if( isRecordIncome == true){
                            Intent i = new Intent(getBaseContext(), IncomeActivity.class);
                            i.putExtra("IncomeID", selected_id);
                            i.putExtra("periodID", periodID);
                            startActivity(i);
                        }

                        if( isRecordIncome == false){
                            Intent i = new Intent(getBaseContext(), ExpenseActivity.class);
                            i.putExtra("ExpenseID", selected_id);
                            i.putExtra("periodID", periodID);
                            startActivity(i);
                        }
                    }
                }
        );

        FloatingActionButton fab_goBack = findViewById(R.id.fab_goBack);
        fab_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                i.putExtra("periodID", periodID);
                startActivity(i);
            }
        });


        FloatingActionButton fab_addIncome = findViewById(R.id.fab_addIncome);
        fab_addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), IncomeActivity.class);
                i.putExtra("periodID", periodID);
                i.putExtra("IncomeID", 0);
                startActivity(i);
            }
        });

        FloatingActionButton fab_addExpense = (FloatingActionButton) findViewById(R.id.fab_addExpense);
        fab_addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), ExpenseActivity.class);
                i.putExtra("ExpenseID", 0);
                i.putExtra("periodID", periodID);
                startActivity(i);
            }
        });
    }

}
