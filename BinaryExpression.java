import java.util.ArrayList;
import java.util.Collections;

public class BinaryExpression extends Expression {
    private Expression left;
    private Expression right;
    private String operator;

    public BinaryExpression(Token token) {
        super(token);
    }

    private static Expression generateTree(ArrayList<Token> expression) {
        Expression tree;
        if (expression.size() == 1 && !(expression.get(0) instanceof IdentifierToken || expression.get(0) instanceof NumberToken)) {
            tree = new Expression(expression.get(0));
        } else if (expression.get(0) instanceof NumberToken) {
            tree = new PrimaryExpression(expression.get(0));
            ((PrimaryExpression) tree).setNumber(true);
            ((PrimaryExpression) tree).setValue(expression.get(0).getValue());
            expression.remove(0);
        } else if (expression.get(0) instanceof IdentifierToken) {
            tree = new PrimaryExpression(expression.get(0));
            ((PrimaryExpression) tree).setNumber(false);
            ((PrimaryExpression) tree).setName(expression.get(0).getName());
            expression.remove(0);
        } else if (expression.get(0) instanceof OperatorToken) {
            tree = new BinaryExpression(expression.get(0));
            ((BinaryExpression) tree).setOperator(expression.get(0).getName());
            expression.remove(0);
            ((BinaryExpression) tree).setRight(generateTree(expression));
            ((BinaryExpression) tree).setLeft(generateTree(expression));
        } else {
            tree = new Expression(expression.get(0));
        }
        return tree;
    }

    public static BinaryExpression parse(ArrayList<Token> RPNExpression) {
        BinaryExpression result;
        Collections.reverse(RPNExpression);
        result = (BinaryExpression) generateTree(RPNExpression);
        return result;
    }

    static String opToPostScriptCode(String operator) {
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

    public String codeString() {
        String result = "";
        result += getLeft().codeString();
        result += getRight().codeString();
        result += opToPostScriptCode(getOperator()) + " ";
        return result;
    }

    public boolean isOperatorBoolean() {
        if (this.operator.equals("==") || this.operator.equals("<") || this.operator.equals(">") || this.operator.equals("<=") || this.operator.equals("<=")
                || this.operator.equals("!=")) {
            return true;
        } else {
            return false;
        }
    }

    public int numberOfBooleanOperators() {
        int totalBooleanOpperators = 0;
        totalBooleanOpperators += right.numberOfBooleanOperators();
        totalBooleanOpperators += left.numberOfBooleanOperators();

        if (this.isOperatorBoolean()) {
            totalBooleanOpperators += 1;
        }

        return totalBooleanOpperators;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
