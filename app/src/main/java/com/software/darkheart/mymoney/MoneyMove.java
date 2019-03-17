package com.software.darkheart.mymoney;

import com.software.darkheart.Interfaces.Money;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MoneyMove {
    private List<Money> money_conjunction = new ArrayList<>();

    MoneyMove(List<Money> incomes, List<Money> expenses){

        Iterator<Money> iterator = incomes.iterator();

        while (iterator.hasNext())
            money_conjunction.add(iterator.next() );

        iterator = expenses.iterator();

        while (iterator.hasNext())
            money_conjunction.add(iterator.next());

    }

    List<Money> get_MoveyMove_List(){
        return money_conjunction;
    }

}
