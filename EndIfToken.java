public class EndIfToken extends Token {
    public EndIfToken(int lineNumber) {
        super(lineNumber);
    }

    public String getName() {
        return "ENDIF";
    }
}
