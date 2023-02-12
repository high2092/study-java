import java.util.ArrayList;

public class GameView {
    public static final ArrayList<Integer> hitNumbers = AnswerGenerator.generate();

    public static final int STRIKE_CRITERIA = 5;
    public static final int MISS = 0;
    public static final int STRIKE = 1;
    public static final int BALL = 2;

    private static Player player;
    private static GameService gameService = new GameService(hitNumbers);

    public GameView() {
        boolean isGameOver = false;
        player = IO.register();
        int count = 0;
        while (isGameOver == false) {
            IO.printPlayTurnText();
            isGameOver = gameService.playTurn(IO.inputNumbers(STRIKE_CRITERIA));
            count++;
        }
        IO.printPlayCount(count);
    }


}
