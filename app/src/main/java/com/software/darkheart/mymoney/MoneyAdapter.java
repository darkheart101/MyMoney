package com.software.darkheart.mymoney;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.software.darkheart.Interfaces.Money;


import java.text.DecimalFormat;
import java.util.List;

public class MoneyAdapter extends ArrayAdapter<Money>{

    DecimalFormat f = new DecimalFormat("##.00");


    public MoneyAdapter(Context context, List<Money> money_conjuction) {
        super(context, R.layout.money_row, money_conjuction);
    }

    //@androidx.annotation.NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View moneyView = inflater.inflate(R.layout.money_row,parent,false);

        Money m = getItem(position);

        if (m != null) {


            String money_description = String.valueOf(m.get_Comment());
            String money_value = String.valueOf(f.format(m.get_Value()));
            String money_full_description = money_value + " - " + money_description;



            if(m.getIsIncome() == true){
                money_full_description = "+" + money_full_description;
                moneyView.setBackgroundColor(Color.GREEN);
            }

            if(m.getIsIncome() == false){
                money_full_description = "-" + money_full_description;
                moneyView.setBackgroundColor(Color.RED);
            }

            TextView txt_money_description = moneyView.findViewById(R.id.txt_money_description);
            txt_money_description.setText( " " + money_full_description );
            txt_money_description.setTextColor(Color.BLACK);
        }
;
        return moneyView;

    }
}
