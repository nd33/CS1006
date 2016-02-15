public class RBracketToken extends Token {

    public RBracketToken(int lineNumber) {
        super(lineNumber);
    }

    public String getName() {
        return ")";
    }
}
