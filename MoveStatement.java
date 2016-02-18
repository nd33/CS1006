

public class MoveStatement extends Statement {
    private String move;
    private Expression argument;

    public static MoveStatement parse() {
        MoveStatement result = new MoveStatement();
        result.setMove(Parser.getCurrentToken().getName());
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof NumberToken) && !(Parser.getCurrentToken() instanceof IdentifierToken) && !(Parser.getCurrentToken() instanceof LBracketToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting expression after " + result.getMove(), Parser.getCurrentToken()));
        }
        result.setArgument(Expression.parse());

        if (result.argument.numberOfBooleanOperators() != 0) {
            ErrorLog.logError(new Error(result.argument.getToken().getLineNumber(), "Argument cannot contain boolean operators", result.argument.getToken()));
        }

        return result;
    }

    public String codeString() {
        String result = "";
        if (argument != null) {
            result += argument.codeString();
        }
        result += " " + move + "\n";
        return result;
        //return getArgument().codeString() + " " + getMove() + "\n";
    }

    public String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
    }

    public Expression getArgument() {
        return argument;
    }

    public void setArgument(Expression argument) {
        this.argument = argument;
    }
}
