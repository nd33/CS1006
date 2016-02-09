

public class MoveStatment extends Statment {
    String move;
    Expression argument;

    public static MoveStatment parse () {
        MoveStatment result = new MoveStatment();
        result.move = Parser.currentToken.getName();
        Parser.nextToken();
        if (!(Parser.currentToken instanceof NumberToken) && !(Parser.currentToken instanceof IdentifierToken) && !(Parser.currentToken instanceof LBracketToken)) {
            //Add Error
            ErrorLog.logError(new Error(Parser.currentToken.getLineNumber(), "Expecting expression after " + result.move));
        }
        result.argument = Expression.parse();
        return result;
    }


    public String codeString () {
        return argument.codeString() + "/Arg exch def\n" + move + "\n";
    }
}
