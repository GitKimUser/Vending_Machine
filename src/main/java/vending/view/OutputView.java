package vending.view;

import vending.Coin;
import java.util.Map;

public class OutputView {

    // 현재 투입 금액 출력
    public void printInputAmount(int amount) {
        System.out.printf("\n투입 금액: %d원%n", amount);
    }

    // 잔돈 반환 결과 출력
    public void printChanges(Map<Coin, Integer> changes) {
        System.out.println("잔돈");
        for (Map.Entry<Coin, Integer> entry : changes.entrySet()) {
            System.out.printf("%d원 - %d개%n", entry.getKey().getAmount(), entry.getValue());
        }
    }

    // 에러 메시지 출력
    public void printError(String message) {
        System.out.println(message);
    }
}