public class NumberToken extends Token {
    private int value;

    public NumberToken (int value, int lineNumber)
    {
        super(lineNumber);
        this.setValue(value);
    }

    @Override public int getValue () {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
