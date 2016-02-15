public class LBracketToken extends Token {

    public LBracketToken(int lineNumber) {
        super(lineNumber);
    }

    public String getName() {
        return "(";
    }
}
