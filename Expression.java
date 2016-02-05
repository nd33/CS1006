/**
 * Created by jm360 on 05/02/16.
 */
public class Expression extends ABSElement {
    public static Expression parse () {
        Token tempToken = Parser.currentToken;
        Parser.nextToken();
        if (Parser.currentToken instanceof OperatorToken) {
            Parser.previousToken();
            return BinaryExpression.parse();
        } else {
            Parser.previousToken();
            return PrimaryExpression.parse();
        }
        //Add Error checking for invalid expression end
    }

    public String codeString () {
        return "";
    }
}
