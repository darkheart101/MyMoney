package com.software.darkheart.mymoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.software.darkheart.model.Period;

import java.util.List;

public class PeriodAdapter extends ArrayAdapter<Period>{

    public PeriodAdapter(Context context, List<Period> periods) {
        super(context, R.layout.period_row , periods);
    }

    //@androidx.annotation.NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View periodView = inflater.inflate(R.layout.period_row,parent,false);

        Period period = getItem(position);


        if (period != null) {
            String period_full_description = Month.get_month_name(period.getMonth()) + " - " + Integer.toString( period.getYear() );

            TextView txt_period_description = periodView.findViewById(R.id.txt_period_description);
            txt_period_description.setText(period_full_description );


            TextView txt_period_id = periodView.findViewById(R.id.txt_period_id);
            txt_period_id.setText(String.valueOf(period.getId()));
        }



        return periodView;

    }
}
