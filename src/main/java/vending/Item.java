package vending;
public class Item {
    private final String name;
    private final int price;
    private final int quantity;

    public Item(String name, int price, int quantity) {
        validate(name, price, quantity);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    private void validate(String name, int price, int quantity) {
        if (price < 100) {
            throw new IllegalArgumentException("[ERROR] 투입 금액은 100원 이상이여야 합니다.");
        }
        if (price % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 투입 금액은 10원 단위여야 합니다.");
        }
    }
}