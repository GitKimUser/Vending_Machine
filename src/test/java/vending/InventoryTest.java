package vending;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class InventoryTest {

    @Test
    @DisplayName("상품을 추가하고 이름으로 찾을 수 있다.")
    void addAndFindItem() {
        // given (준비)
        Inventory inventory = new Inventory();
        Item cola = new Item("콜라", 1000, 10);
        
        // when (실행)
        inventory.add(cola);
        Item foundItem = inventory.findByName("콜라");

        // then (검증)
        assertThat(foundItem).isEqualTo(cola);
    }
}