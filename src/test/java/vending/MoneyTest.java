package vending;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class MoneyTest {
    @Test
    @DisplayName("투입 금액이 10원 단위가 아니면 예외가 발생한다.")
    void createMoney_WithInvalidUnit_ShouldThrowException() {
        // given
        int invalidAmount = 1005;

        // when & then
        assertThatThrownBy(() -> new Money(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 투입 금액은 10원 단위여야 합니다."); // 에러 메시지도 미리 정의
    }
    @Test
    @DisplayName("금액을 차감하면 잔액이 줄어든다.")
    void spendMoney() {
        Money money = new Money(1000);
        money.spend(400);
        assertThat(money.getAmount()).isEqualTo(600);
    }
}
