package vending.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    // 1. 상품 정보 입력 받기 (예: [콜라,1500,20];[사이다,1000,10])
    public String readItemInfos() {
        System.out.println("자판기가 보유하고 있는 상품을 입력해 주세요.");
        return scanner.nextLine();
    }

    // 2. 투입 금액 입력 받기
    public int readInputAmount() {
        System.out.println("\n투입 금액을 입력해 주세요.");
        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 금액은 숫자여야 합니다.");
        }
    }

    // 3. 구매할 상품명 입력 받기
    public String readProductName() {
        System.out.println("구매할 상품명을 입력해 주세요.");
        return scanner.nextLine();
    }
}