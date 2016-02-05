public class NumberToken extends Token {
    private int value;

    public NumberToken (int value) {
        this.value = value;
    }

    @Override public int getValue () {
        return value;
    }
}
