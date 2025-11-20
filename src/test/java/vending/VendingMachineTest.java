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

    @Test
    @DisplayName("남은 금액을 동전으로 반환한다.")
    void returnChange() {
        // given
        Inventory inventory = new Inventory();
        VendingMachine vm = new VendingMachine(inventory);
        
        // 우리가 검증하고 싶은 550원을 바로 넣습니다.
        vm.insertMoney(550); 

        // when (550원을 동전으로 교환)
        java.util.Map<Coin, Integer> changes = vm.getChanges();

        // then (500원 1개, 50원 1개여야 함)
        assertThat(changes.get(Coin.COIN_500)).isEqualTo(1);
        assertThat(changes.get(Coin.COIN_50)).isEqualTo(1);
        assertThat(changes.getOrDefault(Coin.COIN_100,0)).isEqualTo(0); // 100원은 없으므로 null (EnumMap 특성상 0이 아니라 null일 수 있음)
    }

    @Test
    @DisplayName("다양한 동전이 섞인 경우(1260원)도 최소 개수로 반환한다.")
    void returnChange_Complex() {
        // given
        Inventory inventory = new Inventory();
        VendingMachine vm = new VendingMachine(inventory);
        
        // 1260원을 넣습니다.
        // 기대 결과: 500원x2, 100원x2, 50원x1, 10원x1
        vm.insertMoney(1260); 

        // when
        java.util.Map<Coin, Integer> changes = vm.getChanges();

        // then (하나하나 꼼꼼하게 검증!)
        assertThat(changes.get(Coin.COIN_500)).isEqualTo(2); // 1000원
        assertThat(changes.get(Coin.COIN_100)).isEqualTo(2); // 200원
        assertThat(changes.get(Coin.COIN_50)).isEqualTo(1);  // 50원
        assertThat(changes.get(Coin.COIN_10)).isEqualTo(1);  // 10원
    }

    @Test
    @DisplayName("재고가 없는 상품을 구매하면 예외가 발생한다.")
    void buySoldOutItem() {
        // given
        Inventory inventory = new Inventory();
        // 콜라 1개만 넣음
        inventory.add(new Item("콜라", 1000, 1)); 
        
        VendingMachine vm = new VendingMachine(inventory);
        vm.insertMoney(2000);

        // when
        vm.buy("콜라"); // 1개 구매 (남은 재고 0개)

        // then (재고 0개인 상태에서 또 사려고 하면 예외 발생!)
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> vm.buy("콜라"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 상품의 재고가 부족");
    }
}