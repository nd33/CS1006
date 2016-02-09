

public class MoveStatment extends Statment {
    private String move;
    private Expression argument;

    public static MoveStatment parse () {
        MoveStatment result = new MoveStatment();
        result.setMove(Parser.getCurrentToken().getName());
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof NumberToken) && !(Parser.getCurrentToken() instanceof IdentifierToken) && !(Parser.getCurrentToken() instanceof LBracketToken)) {
            //Add Error
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting expression after " + result.getMove()));
        }
        result.setArgument(Expression.parse());
        return result;
    }


    public String codeString () {
        return getArgument().codeString() + "/Arg exch def\n" + getMove() + "\n";
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
