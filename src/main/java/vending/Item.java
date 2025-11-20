package vending;
public class Item {
    private final String name;
    private final int price;
    private int quantity;

    public Item(String name, int price, int quantity) {
        validate(name, price, quantity);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    private void validate(String name, int price, int quantity) {
        if (price < 100) {
            throw new IllegalArgumentException("[ERROR] 상품 가격은 100원 이상이여야 합니다.");
        }
        if (price % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 상품 가격은 10원 단위여야 합니다.");
        }
    }
    public String getName() {
        return name;
    }
    
    public int getPrice() {
        return price;
    }

    public void sell() {
        if (quantity <= 0) {
            throw new IllegalArgumentException("[ERROR] 상품의 재고가 부족합니다.");
        }
        quantity--;
    }
}