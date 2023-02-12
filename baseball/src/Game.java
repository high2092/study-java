import java.util.ArrayList;

public class Game {
    public static final ArrayList<Integer> hitNumbers = AnswerGenerator.generate();

    public static final int STRIKE_CRITERIA = 5;
    public static final int MISS = 0;
    public static final int STRIKE = 1;
    public static final int BALL = 2;

    private static Player player;
    private static Judgement judgement = new Judgement(hitNumbers);

    public Game() {

        boolean isGameOver = false;
        player = IO.register();
        while (isGameOver == false) {
            isGameOver = playTurn();
        }

    }

    private static boolean playTurn() {
        IO.printPlayTurnText();
        int[] inputNumbers = IO.inputNumbers(STRIKE_CRITERIA);
        ArrayList<Integer> guessResultCount = Judgement.judge(inputNumbers);

        return guessResultCount.get(STRIKE) == STRIKE_CRITERIA;
    }
}
