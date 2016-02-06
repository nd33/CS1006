import java.util.ArrayList;
import java.util.Collections;

public class BinaryExpression extends Expression {
    Expression left;
    Expression right;
    String operator;

    private static Expression generateTree (ArrayList<Token> expression) {
        Expression tree;
        if (expression.size() == 1 && !(expression.get(0) instanceof IdentifierToken || expression.get(0) instanceof NumberToken)) {
            //Add Error
            tree = new Expression();
        } else if (expression.get(0) instanceof NumberToken) {
            tree = new PrimaryExpression();
            ((PrimaryExpression) tree).isNumber = true;
            ((PrimaryExpression) tree).value = expression.get(0).getValue();
            expression.remove(0);
        } else if (expression.get(0) instanceof IdentifierToken) {
            tree = new PrimaryExpression();
            ((PrimaryExpression) tree).isNumber = false;
            ((PrimaryExpression) tree).name = expression.get(0).getName();
            expression.remove(0);
        } else if (expression.get(0) instanceof OperatorToken) {
            tree = new BinaryExpression();
            ((BinaryExpression) tree).operator = expression.get(0).getName();
            expression.remove(0);
            ((BinaryExpression) tree).right = generateTree(expression);
            ((BinaryExpression) tree).left = generateTree(expression);
            tree.codeString();
        } else {
            tree = new Expression();
        }
        return tree;
    }

    public static BinaryExpression parse (ArrayList<Token> RPNExpression) {
        BinaryExpression result = new BinaryExpression();
        Collections.reverse(RPNExpression);
        result = (BinaryExpression)generateTree(RPNExpression);
        return result;
    }

    static String opToPostScriptCode (String operator) {
        if (operator.equals("+")) {
            return "add";
        } else if (operator.equals("-")) {
            return "sub";
        } else if (operator.equals("*")) {
            return "mul";
        } else if (operator.equals("/")) {
            return "div";
        } else if (operator.equals("==")) {
            return "eq";
        } else if (operator.equals("!=")) {
            return "ne";
        } else if (operator.equals(">=")) {
            return "ge";
        } else if (operator.equals(">")) {
            return "gt";
        } else if (operator.equals("<=")) {
            return "le";
        } else if (operator.equals("<")) {
            return "lt";
        }
        return "";
    }

    public String codeString () {
        String result = "";
        result += left.codeString();
        result += right.codeString();
        result += opToPostScriptCode(operator) + "\n";
        return result;
    }

}
