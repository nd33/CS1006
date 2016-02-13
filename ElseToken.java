public class ElseToken extends Token {
    public ElseToken (int lineNumber) {
        super(lineNumber);
    }

    public String getName () {
        return "ELSE";
    }
}
