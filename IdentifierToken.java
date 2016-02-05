public class IdentifierToken extends Token {
    private String name;

    public IdentifierToken (String name) {
        this.name = name;
    }

    @Override public String getName () {
        return name;
    }
}
