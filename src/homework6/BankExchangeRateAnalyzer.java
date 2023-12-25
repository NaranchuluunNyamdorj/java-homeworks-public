package homework6;

import java.util.HashMap;
import java.util.Map;
class ExchangeRate<T> {
    private T initialRate;
    private T finalRate;

    public ExchangeRate(T initialRate, T finalRate) {
        this.initialRate = initialRate;
        this.finalRate = finalRate;
    }

    public T getInitialRate() {
        return initialRate;
    }

    public T getFinalRate() {
        return finalRate;
    }

    public T calculateChange() {
        if (initialRate instanceof Double) {
            return (T) Double.valueOf(((Double) finalRate - (Double) initialRate));
        } else if (initialRate instanceof Float) {
            return (T) Float.valueOf(((Float) finalRate - (Float) initialRate));
        }
        else if (initialRate instanceof Integer) {
            return (T)  Integer.valueOf(((Integer) finalRate - (Integer) initialRate));
        }
        return null;
    }
}

public class BankExchangeRateAnalyzer {
    public static <T> void findLargestChange(Map<String, Map<String, ExchangeRate<T>>> data) {
        T largestChange = null;
        String bankName = null;
        String currencyName = null;

        for (Map.Entry<String, Map<String, ExchangeRate<T>>> bankEntry : data.entrySet()) {
            String bank = bankEntry.getKey();
            Map<String, ExchangeRate<T>> currencyRates = bankEntry.getValue();

            for (Map.Entry<String, ExchangeRate<T>> currencyEntry : currencyRates.entrySet()) {
                String currency = currencyEntry.getKey();
                ExchangeRate<T> rate = currencyEntry.getValue();

                T change = rate.calculateChange();

                if (largestChange == null || (Double.valueOf(change.toString()) > Double.valueOf(largestChange.toString()))) {
                    largestChange = change;
                    bankName = bank;
                    currencyName = currency;
                }
            }
        }

        System.out.println("Bank with largest exchange rate change: " + bankName);
        System.out.println("Currency with largest change: " + currencyName);
        System.out.println("Largest change amount: " + largestChange);
    }

    public static void main(String[] args) {
        // Create a map to store exchange rate data
        Map<String, Map<String, ExchangeRate<Double>>> exchangeData = new HashMap<>();

        Map<String, ExchangeRate<Double>> TDB = new HashMap<>();
        TDB.put("USD", new ExchangeRate<>(3447.00, 3447.00));
        TDB.put("EUR", new ExchangeRate<>(3626.00, 3626.00 ));
        TDB.put("GBP", new ExchangeRate<>(4209.00, 4209.00));
        TDB.put("RUB", new ExchangeRate<>(5000.0, 5000.0));
        TDB.put("JPY", new ExchangeRate<>(1100.0, 1120.0));
        TDB.put("CNY", new ExchangeRate<>(1100.0, 1120.0));
        TDB.put("KRW", new ExchangeRate<>(1100.0, 1120.0));

        Map<String, ExchangeRate<Double>> Golomt = new HashMap<>();
        Golomt.put("USD", new ExchangeRate<>(1.0, 1.1));
        Golomt.put("EUR", new ExchangeRate<>(0.9, 0.85));
        Golomt.put("GBP", new ExchangeRate<>(110.0, 112.0));
        Golomt.put("RUB", new ExchangeRate<>(110.0, 112.0));
        //Doorh unelgeeg gargah yostoi
        Golomt.put("JPY", new ExchangeRate<>(1100.0, 1500.0));
        Golomt.put("CNY", new ExchangeRate<>(110.0, 112.0));
        Golomt.put("KRW", new ExchangeRate<>(110.0, 112.0));


        Map<String, ExchangeRate<Double>> Khaan = new HashMap<>();
        Khaan.put("USD", new ExchangeRate<>(1000.0, 1000.1));
        Khaan.put("EUR", new ExchangeRate<>(1000.9, 1000.85));
        Khaan.put("GBP", new ExchangeRate<>(1100.0, 1120.0));
        Khaan.put("RUB", new ExchangeRate<>(1100.0, 1120.0));
        Khaan.put("JPY", new ExchangeRate<>(1100.0, 1120.0));
        Khaan.put("CNY", new ExchangeRate<>(1100.0, 1120.0));
        Khaan.put("KRW", new ExchangeRate<>(1100.0, 1120.0));

        exchangeData.put("Bank1", TDB);
        exchangeData.put("Bank2", Golomt);
        exchangeData.put("Bank3", Khaan);
        // Call the findLargestChange method to find the largest change
        findLargestChange(exchangeData);
    }
}
