package vending;

public class VendingMachine {
    private final Inventory inventory;
    private Money money;

    public VendingMachine(Inventory inventory) {
        this.inventory = inventory;
        this.money = new Money(0);
    }

    public void insertMoney(int amount) {
        this.money = new Money(amount);
    }

    public void buy(String name) {
        // 1. 창고(inventory)에서 이름으로 상품(Item)을 찾는다.
        Item item = inventory.findByName(name);

        // 2. 돈(money) 객체에게 상품 가격만큼 사용(spend)하라고 시킨다.
        money.spend(item.getPrice());
    }

    public int getRemainAmount() {
        return money.getAmount();
    }
}
