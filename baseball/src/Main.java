import java.util.*;

public class Main {
    private static final int STRIKE_CRITERIA = 3;

    private static final List<Integer> DIGITS = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

    private static final int[] hitNumbers = generateHitNumbers();
    private static int[] generateHitNumbers() {
        int[] hitNumbers = new int[STRIKE_CRITERIA];
        return Arrays.stream(hitNumbers).map(hitNumber -> {
            int idx = (int)(Math.random() * DIGITS.size());
            return DIGITS.remove(idx);
        }).toArray();
    }

    private static int[] guessResultCount = new int[STRIKE_CRITERIA];
    private static final int MISS = 0;
    private static final int STRIKE = 1;
    private static final int BALL = 2;
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isGameOver = false;

    private static int check(int idx, int guess) {
        for (int i = 0; i < STRIKE_CRITERIA; i++) { // for문을 forEach로 대체하는 것의 장점이 떠오르지 않는 경우도 종종 맞닥뜨림
            if (hitNumbers[i] == guess) return idx == i ? STRIKE : BALL;
        }
        return MISS;
    }

    private static void printGuessResult(int[] guessResultCount) {
        System.out.println("스트라이크: " + guessResultCount[STRIKE]);
        System.out.println("볼: " + guessResultCount[BALL]);
    }

    private static void register() {
        System.out.println("당신의 이름은?");
        String username = scanner.nextLine();
        System.out.println("당신의 나이는?");
        int age = scanner.nextInt();
        System.out.println("등록 완료: " + username + " (" + age + ")");

        System.out.println("게임을 시작합니다.");
    }

    private static int[] playTurn() {
        System.out.println("3개의 숫자는 순서대로 무엇일까요? (0 이상 9 이하의 서로 다른 정수, 공백으로 구분)");
        guessResultCount = new int[STRIKE_CRITERIA];

        for (int i = 0; i < STRIKE_CRITERIA; i++) {
            int guess = scanner.nextInt(); // TODO: n개의 입력을 모두 받고 유효성 검증을 마친 후 연산 시작하기
            int result = check(i, guess);
            guessResultCount[result]++;
        }
        return guessResultCount;
    }

    public static void main(String[] args) {
        register();
        while (isGameOver == false) {
            int[] guessResult = playTurn();
            printGuessResult(guessResult);
            isGameOver = guessResultCount[STRIKE] == STRIKE_CRITERIA;
        }
    }
}