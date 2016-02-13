import java.util.ArrayList;
import java.util.Stack;

public class Expression extends ABSElement {
    public Expression(Token token){
        super(token);
    }

    public Expression(){
    }


    public static ArrayList isolateExpression () {
        ArrayList<Token> result = new ArrayList<Token>();

        while (Parser.getCurrentToken() instanceof NumberToken || Parser.getCurrentToken() instanceof IdentifierToken ||
                Parser.getCurrentToken() instanceof OperatorToken || Parser.getCurrentToken() instanceof LBracketToken ||
                Parser.getCurrentToken() instanceof RBracketToken) {

            result.add(Parser.getCurrentToken());
            Parser.nextToken();
        }

        return result;
    }

    public static boolean isLegalExpression (ArrayList<Token> expression) {

        boolean result = true;
        int bracketCounter = 0;

        for (int i = 0; i < expression.size(); i ++) {
            if (expression.get(i) instanceof NumberToken || expression.get(i) instanceof IdentifierToken) {
                if (i == expression.size() - 1) {

                } else if (!(expression.get(i + 1) instanceof RBracketToken) && !(expression.get(i + 1) instanceof OperatorToken))  {
                    ErrorLog.logError(new Error(expression.get(i + 1).getLineNumber(), "Expected an operator or ')' after '" + expression.get(i).getName() + "'"));
                    result = false;
                }
            } else if (expression.get(i) instanceof OperatorToken) {
                if (i == expression.size() - 1) {
                    ErrorLog.logError(new Error(expression.get(i).getLineNumber(), "Expecting a number or identifier after '" + expression.get(i).getName() + "'"));
                    result = false;
                } else if (expression.get(i + 1) instanceof OperatorToken || expression.get(i + 1) instanceof RBracketToken) {
                    ErrorLog.logError(new Error(expression.get(i).getLineNumber(), "Expecting a number or identifier after '" + expression.get(i).getName() + "'"));
                    result = false;
                }
            } else if (expression.get(i) instanceof LBracketToken) {
                if (i == expression.size() - 1) {
                    ErrorLog.logError(new Error(expression.get(i).getLineNumber(), "Expecting a number or identifier after '" + expression.get(i).getName() + "'"));
                    result = false;
                }

                bracketCounter ++;
            } else if (expression.get(i) instanceof RBracketToken) {
                bracketCounter --;
            }

            if (bracketCounter < 0) {
                ErrorLog.logError(new Error(expression.get(i).getLineNumber(), "Expecting '(' before ')'"));
            }
        }

        if (bracketCounter != 0) {
            ErrorLog.logError(new Error(expression.get(expression.size() - 1).getLineNumber(), "Expecting ')' after '('"));
            result = false;
        }

        return result;
    }

    //Dijkstra's 'Shunting Yard' algorithm
    public static ArrayList<Token> generateRPNExpression (ArrayList<Token> expression) {
        ArrayList<Token> result = new ArrayList<Token>();
        Stack<Token> opStack = new Stack<Token>();

        for (Token currentToken : expression) {
            if (currentToken instanceof  NumberToken || currentToken instanceof IdentifierToken) {

                result.add(currentToken);

            } else if (currentToken instanceof OperatorToken) {

                if (opStack.isEmpty()) {
                    opStack.push(currentToken);
                } else if (currentToken.getPrecedence() <= opStack.peek().getPrecedence() && opStack.peek() instanceof OperatorToken) {
                    while (!opStack.empty() && opStack.peek() instanceof OperatorToken && currentToken.getPrecedence() <= opStack.peek().getPrecedence()) {
                        result.add(opStack.pop());
                    }
                    opStack.push(currentToken);
                    result.get(0);
                } else {
                    opStack.push(currentToken);
                }

            } else if (currentToken instanceof LBracketToken) {

                opStack.push(currentToken);

            } else if (currentToken instanceof RBracketToken) {

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
        }

        while (!opStack.empty()) {
            result.add(opStack.pop());
        }

        return result;
    }

    public static Expression parse () {
        ArrayList<Token> expression = isolateExpression();

        if (!isLegalExpression(expression)) {
            Expression e = new Expression();
            e.setEmpty(true);
            return e;
        }

        ArrayList<Token> RPNExpression = generateRPNExpression(expression);
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

    public int numberOfBooleanOperators () {
        return 0;
    }

    public String codeString () {
        return "";
    }
}
