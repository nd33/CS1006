public class IdentifierToken extends Token {
    private String name;

    public IdentifierToken(String name, int lineNumber) {
        super(lineNumber);
        this.setName(name);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
