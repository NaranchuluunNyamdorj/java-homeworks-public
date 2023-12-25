package homework7;

import java.util.*;

public class OnlineShopProgram {
    public static void main(String[] args) {

        TreeSet<Purchase> purchases = new TreeSet<>(new PurchaseComparator());

        purchases.add(new Purchase("Bat", "aaa", 1));
        purchases.add(new Purchase("Purev", "aaa", 2));
        purchases.add(new Purchase("Suren", "aaa", 3));
        purchases.add(new Purchase("Bat", "aaa", 6));
        purchases.add(new Purchase("Purev", "aaa", 7));
        purchases.add(new Purchase("Suren", "aaa", 8));
        purchases.add(new Purchase("Bat", "bbb", 3));
        purchases.add(new Purchase("Purev", "bbb", 7));
        purchases.add(new Purchase("Suren", "aaa", 345));
        purchases.add(new Purchase("Bat", "ccc", 45));
        purchases.add(new Purchase("Purev", "ddd", 34));
        purchases.add(new Purchase("Zana", "eee", 234));
        purchases.add(new Purchase("Bat", "aaa", 45));

        for (Purchase purchase : purchases) {
            System.out.println(purchase.getBuyer() + " " + purchase.getItem() + " " + purchase.getQuantity());
        }
    }
}

class Purchase {
    private String buyer;
    private String item;
    private int quantity;

    public Purchase(String buyer, String item, int quantity) {
        this.buyer = buyer;
        this.item = item;
        this.quantity = quantity;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}

class PurchaseComparator implements Comparator<Purchase> {
    @Override
    public int compare(Purchase p1, Purchase p2) {
        int itemComparison = p1.getItem().compareTo(p2.getItem());
        if (itemComparison == 0) {
            return p1.getQuantity() - p2.getQuantity();
        }
        return itemComparison;
    }
}

