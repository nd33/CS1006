/**
 * Created by jm360 on 05/02/16.
 */
public class MoveStatment extends Statment {
    String move;
    Expression argument;

    public static MoveStatment parse () {
        MoveStatment result = new MoveStatment();
        result.move = Parser.currentToken.getName();
        Parser.nextToken();
        if (!(Parser.currentToken instanceof NumberToken) && !(Parser.currentToken instanceof IdentifierToken) && !(Parser.currentToken instanceof LBracketToken)) {
            //Add Error
        }
        result.argument = Expression.parse();
        return result;
    }

    public String codeString () {
        return argument.toString() + "\n" + move + "\n";
    }
}
