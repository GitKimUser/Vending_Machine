package vending;

import java.util.*;

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

    Map<Coin, Integer> getChanges() {
        Map<Coin, Integer> changes = new EnumMap<>(Coin.class);

        int remainAmount = money.getAmount(); // 현재 남은 돈 (예: 550원)

        // 1. 큰 동전부터 순서대로 꺼냅니다 (500 -> 100 -> 50 -> 10)
        for (Coin coin : Coin.getCoinsDesc()) {
            int coinAmount = coin.getAmount();

            // 2. 이 동전으로 몇 개나 거슬러 줄 수 있는지 계산
            int count = remainAmount / coinAmount;
            
            // 3. 줄 수 있는 게 있다면 기록하고, 남은 돈 갱신
            if(count > 0) {
                changes.put(coin, count);   // 500원 1개 기록
                remainAmount %= coinAmount; // 550 % 500 = 50원 남음
            }
        }
        return changes;
    }
}
