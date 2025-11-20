package vending;

import vending.view.InputView;
import vending.view.OutputView;

public class App {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        try {
            // 1. 상품 목록 생성 및 파싱
            Inventory inventory = new Inventory();
            String itemInfos = inputView.readItemInfos();
            // 입력값 파싱 (예: "[콜라,1000,10];[사이다,1200,20]")
            String[] items = itemInfos.split(";");
            for (String itemStr : items) {
                // 대괄호 여부 체크
                if (!itemStr.startsWith("[") || !itemStr.endsWith("]")) {
                    throw new IllegalArgumentException("[ERROR] 상품 정보는 대괄호([])로 감싸여야 합니다.");
                }
                // 대괄호 제거 후 쉼표로 분리: "콜라,1000,10" -> ["콜라", "1000", "10"]
                String content = itemStr.substring(1, itemStr.length() - 1);
                String[] parts = content.split(",");
                // 형식이 올바르지 않으면 에러를 던진다
                if (parts.length < 3) {
                    throw new IllegalArgumentException("[ERROR] 상품 정보 형식이 올바르지 않습니다. (예: [콜라,1000,10])");
                }
                String name = parts[0];
                int price = Integer.parseInt(parts[1]);
                int quantity = Integer.parseInt(parts[2]);

                inventory.add(new Item(name, price, quantity));
            }

            // 2. 자판기 생성 및 돈 투입
            VendingMachine vm = new VendingMachine(inventory);
            int inputAmount = inputView.readInputAmount();
            vm.insertMoney(inputAmount);

            // 3. 상품 구매 반복 (단순화를 위해 1회 구매 후 잔돈 반환으로 구현)
            // (심화 과정: while 문으로 잔액이 부족할 때까지 반복하게 바꿀 수 있습니다)
            while (true) {
                outputView.printInputAmount(vm.getRemainAmount());

                // 잔액으로 살 수 있는게 없거나 사용자가 종료를 원하면 break 해야 하지만,
                // 일단은 한 번 구매 시도 후 예외가 나면 종료되는 흐름으로 갑니다.
                try {
                    String productName = inputView.readProductName();
                    vm.buy(productName);
                } catch (IllegalArgumentException e) {
                    outputView.printError(e.getMessage());
                    break; // 예외 발생 시(잔액 부족 등) 반복 종료
                }
            }

            // 4. 잔돈 반환 및 출력
            outputView.printInputAmount(vm.getRemainAmount());
            outputView.printChanges(vm.getChanges());

        } catch (Exception e) {
            outputView.printError(e.getMessage());
        }
    }
}