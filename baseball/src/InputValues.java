import java.util.ArrayList;
public class InputValues {
    ArrayList<Integer> inputValues;

    public InputValues(ArrayList<Integer> inputValues) {
        this.inputValues = inputValues;
        validate();
    }

    public void validate() {
        int MIN = 0, MAX = GameView.STRIKE_CRITERIA - 1;
        if (inputValues.size() != GameView.STRIKE_CRITERIA) {
            throw new RuntimeException();
        }
        if (inputValues.stream().anyMatch(inputValue -> inputValue < MIN || inputValue > MAX)) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        inputValues.stream()
                .map(inputValue -> inputValue + " ")
                .forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    public ArrayList<Integer> toList() {
        return new ArrayList<>(inputValues);
    }
}
