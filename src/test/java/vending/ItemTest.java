package vending;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ItemTest {

    @Test
    @DisplayName("상품 가격이 100원 미만이면 예외가 발생한다.")
    void createItem_WithLowPrice_ShouldThrowException() {
        // given
        String name = "콜라";
        int price = 90; // 100원 미만
        int quantity = 10;

        // when & then
        assertThatThrownBy(() -> new Item(name, price, quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 상품 가격은");
    }

    @Test
    @DisplayName("상품 가격이 10원 단위가 아니면 예외가 발생한다.")
    void createItem_WithInvalidUnit_ShouldThrowException() {
        // given
        String name = "사이다";
        int price = 105; // 10원 단위 아님
        int quantity = 10;

        // when & then
        assertThatThrownBy(() -> new Item(name, price, quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 상품 가격은");
    }
}