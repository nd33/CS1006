public class ProcedureToken extends Token {

    public ProcedureToken(int lineNumber) {
        super(lineNumber);
    }

    public String getName() {
        return "PROC";
    }
}
