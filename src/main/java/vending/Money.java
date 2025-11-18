// src/main/java/vending/Money.java
package vending;

public class Money {
    private int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 투입 금액은 10원 단위여야 합니다.");
        }
    }
    
    // 돈을 사용(차감)하는 메서드
    public void spend(int price) {
        if(this.amount < price) {
            throw new IllegalArgumentException("[ERROR] 잔액이 부족합니다.");
        }
        this.amount -= price;
    }
    
    // 현재 잔액을 알려주는 메서드
    public int getAmount() {
        return this.amount;
    }
}