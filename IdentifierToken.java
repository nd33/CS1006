public class IdentifierToken extends Token {
    private String name;

    public IdentifierToken (String name, int lineNumber) {
        super(lineNumber);
        this.name = name;
    }

    @Override public String getName () {
        return name;
    }
}
