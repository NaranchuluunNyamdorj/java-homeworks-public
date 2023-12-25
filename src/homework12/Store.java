package homework12;

class Store {

    interface BuyingFunction {
        double buy(int quantity, double price);
    }

    static double suggestBuy(String itemName, int quantity, double price, BuyingFunction buyingFunction) {
        System.out.println("Buying " + quantity + " units of " + itemName + " at $" + price + " each.");
        return buyingFunction.buy(quantity, price);
    }

    public static void main(String[] args) {
        BuyingFunction buyingFunction = (quantity, price) -> quantity * price;
        double totalPrice = 0;

        totalPrice += suggestBuy("Product1", 2, 10.99, buyingFunction);
        totalPrice += suggestBuy("Product2", 3, 24.99, buyingFunction);
        totalPrice += suggestBuy("Product3", 1, 8.49, buyingFunction);
        totalPrice += suggestBuy("Product4", 5, 5.99, buyingFunction);
        totalPrice += suggestBuy("Product5", 2, 15.75, buyingFunction);

        System.out.println("Total Price: $" + totalPrice);
    }
}
