package homework7;

import java.util.*;

public class OnlineShopProduct {
    public static void main(String[] args) {
        Map<String, Map<String, Integer>> buyers = new TreeMap<>();

        addToBuyers(buyers, "Bat", "paper", 10);
        addToBuyers(buyers, "Purev", "pens", 5);
        addToBuyers(buyers, "Bat", "marker", 3);
        addToBuyers(buyers, "Bat", "paper", 7);
        addToBuyers(buyers, "Purev", "envelope", 20);
        addToBuyers(buyers, "Bat", "envelope", 5);

        for (Map.Entry<String, Map<String, Integer>> entry : buyers.entrySet()) {
            System.out.println(entry.getKey() + ":");
            Map<String, Integer> buyerItems = entry.getValue();
            for (Map.Entry<String, Integer> itemEntry : buyerItems.entrySet()) {
                System.out.println(itemEntry.getKey() + " " + itemEntry.getValue());
            }
        }
    }

    private static void addToBuyers(Map<String, Map<String, Integer>> buyers, String buyerName, String itemName, int quantity) {
        if (!buyers.containsKey(buyerName)) {
            buyers.put(buyerName, new TreeMap<>());
        }
        Map<String, Integer> buyerItems = buyers.get(buyerName);
        if (buyerItems.containsKey(itemName)) {
            quantity += buyerItems.get(itemName);
        }
        buyerItems.put(itemName, quantity);
    }
}

