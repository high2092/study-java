public class Player {
    private Name name;
    private int age;

    public Player(String name, int age) {
        this.name = new Name(name);
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.age + ")";
    }
}

class Name {
    private String name;

    private static final int LENGTH = 3;

    public Name(String name) {
        this.name = name;
        validate();
    }

    private void validate() {
        if (this.name.length() != LENGTH) throw new RuntimeException();
    }

    @Override
    public String toString() {
        return this.name;
    }
}