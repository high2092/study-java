import java.util.Scanner;

public class IO {
    static Scanner scanner = new Scanner(System.in);
    public static Player register() {
        System.out.println("당신의 이름은?");
        String username = scanner.nextLine();
        System.out.println("당신의 나이는?");
        int age = scanner.nextInt();

        Player player = new Player(username, age);
        System.out.println("등록 완료: " + username + " (" + age + ")");

        System.out.println("게임을 시작합니다.");

        return player;
    }

    public static int[] inputNumbers(int count) {
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = scanner.nextInt(); // TODO: n개의 입력을 모두 받고 유효성 검증을 마친 후 연산 시작하기
        }
        return result;
    }
    public static void printGuessResult(int[] guessResultCount) {
        System.out.println("스트라이크: " + guessResultCount[Main.STRIKE]);
        System.out.println("볼: " + guessResultCount[Main.BALL]);
    }

    public static void printPlayTurnText() {
        System.out.println("3개의 숫자는 순서대로 무엇일까요? (0 이상 9 이하의 서로 다른 정수, 공백으로 구분)");
    }
}