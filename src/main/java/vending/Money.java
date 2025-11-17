// src/main/java/vending/Money.java
package vending;

public class Money {
    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 투입 금액은 10원 단위여야 합니다.");
        }
    }
    
    // (참고) getter 같은 건 아직 필요 없으니 만들지 않습니다. (YAGNI 원칙)
}