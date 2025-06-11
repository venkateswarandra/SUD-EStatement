package com.sud.life.estatement.reports.DTO;

import lombok.*;

@Setter
@Getter
@Builder
public class NumberToWordsConverter {
    private static final String[] units = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public static String convert(long number) {
        if (number == 0) return "Zero";
        return convertLessThanOneCrore(number).trim() + " Only";
    }

    private static String convertLessThanOneCrore(long number) {
        if (number < 20)
            return units[(int) number];
        if (number < 100)
            return tens[(int) (number / 10)] + " " + units[(int) (number % 10)];
        if (number < 1000)
            return units[(int) (number / 100)] + " Hundred " + convertLessThanOneCrore(number % 100);
        if (number < 100000)
            return convertLessThanOneCrore(number / 1000) + " Thousand " + convertLessThanOneCrore(number % 1000);
        if (number < 10000000)
            return convertLessThanOneCrore(number / 100000) + " Lakh " + convertLessThanOneCrore(number % 100000);
        return convertLessThanOneCrore(number / 10000000) + " Crore " + convertLessThanOneCrore(number % 10000000);
    }
}

