
public class OperatorToken extends Token {
    private String operator;

    public OperatorToken(String operator, int lineNumber) {
        super(lineNumber);
        this.setOperator(operator);
    }

    @Override
    public String getName() {
        return getOperator();
    }

    @Override
    public int getPrecedence() {
        if (getOperator().equals("+") || getOperator().equals("-")) {
            return 3;
        } else if (getOperator().equals("*") || getOperator().equals("/")) {
            return 4;
        } else if (getOperator().equals("<") || getOperator().equals(">") || getOperator().equals("<=") || getOperator().equals(">=")) {
            return 2;
        } else if (getOperator().equals("==") || getOperator().equals("!=")) {
            return 1;
        }
        return 0;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
