import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Expression extends ABSElement {

    //Dijkstra's 'Shunting Yard' algorithm
    public static ArrayList<Token> generateRPNExpression () {
        ArrayList<Token> result = new ArrayList<Token>();
        Stack<Token> opStack = new Stack<Token>();

        while (Parser.currentToken instanceof NumberToken || Parser.currentToken instanceof IdentifierToken ||
                Parser.currentToken instanceof OperatorToken || Parser.currentToken instanceof LBracketToken ||
                Parser.currentToken instanceof RBracketToken) {

            if (Parser.currentToken instanceof  NumberToken || Parser.currentToken instanceof IdentifierToken) {

                result.add(Parser.currentToken);

            } else if (Parser.currentToken instanceof OperatorToken) {

                if (opStack.isEmpty()) {
                    opStack.push(Parser.currentToken);
                } else if (Parser.currentToken.getPrecedence() <= opStack.peek().getPrecedence() && opStack.peek() instanceof OperatorToken) {
                    while (!opStack.empty() && opStack.peek() instanceof OperatorToken && Parser.currentToken.getPrecedence() <= opStack.peek().getPrecedence()) {
                        result.add(opStack.pop());
                    }
                    opStack.push(Parser.currentToken);
                    result.get(0);
                } else {
                    opStack.push(Parser.currentToken);
                }

            } else if (Parser.currentToken instanceof LBracketToken) {

                opStack.push(Parser.currentToken);

            } else if (Parser.currentToken instanceof RBracketToken) {

                while (!(opStack.peek() instanceof LBracketToken)) {
                    result.add(opStack.pop());
                    if (opStack.empty()) {
                        //Mismatched Parentheses Add Error
                        break;
                    }
                }

                if (!opStack.empty()) {
                    opStack.pop();
                }

            }

            Parser.nextToken();
        }

        while (!opStack.empty()) {
            result.add(opStack.pop());
        }

        return result;
    }

    public static Expression parse () {
        ArrayList<Token> RPNExpression = generateRPNExpression();
        if (RPNExpression.size() == 1) {
            return PrimaryExpression.parse(RPNExpression);
        } else if (!RPNExpression.isEmpty()) {
            return BinaryExpression.parse(RPNExpression);
        } else {
            //Add Error
            return new Expression();
        }
        //Add Error checking for invalid expression end
    }

    public String codeString () {
        return "";
    }
}
