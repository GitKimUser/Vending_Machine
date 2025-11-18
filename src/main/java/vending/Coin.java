package vending;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;
public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public static List<Coin> getCoinsDesc() {
        return Arrays.stream(values())
                .sorted((c1, c2) -> c2.amount - c1.amount)
                .collect(Collectors.toList());
    }
}
