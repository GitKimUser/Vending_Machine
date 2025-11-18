package vending;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class VendingMachineTest {

    @Test
    @DisplayName("상품을 구매하면 투입 금액이 줄어든다.")
    void buyItem() {
        // given (준비: 콜라가 들어있는 자판기에 1500원을 넣음)
        Inventory inventory = new Inventory();
        inventory.add(new Item("콜라", 1000, 10));
        
        VendingMachine vm = new VendingMachine(inventory);
        vm.insertMoney(1500);

        // when (실행: 콜라 구매)
        vm.buy("콜라");

        // then (검증: 1500원 - 1000원 = 500원이 남아야 함)
        assertThat(vm.getRemainAmount()).isEqualTo(500);
    }
}