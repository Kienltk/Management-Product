package com.example.demo.utils.formater.money;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyFormater {
    public String formatCurrencyUSD(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormatter.format(amount);
    }
}