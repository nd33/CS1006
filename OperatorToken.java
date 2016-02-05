public class OperatorToken extends Token {
    private String operator;

    public OperatorToken (String operator) {
        this.operator = operator;
    }

    @Override public int getPrecedence () {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 2;
        } else if (operator.equals("<") || operator.equals(">") || operator.equals("<=") || operator.equals(">=")) {
            return 3;
        } else if (operator.equals("==") || operator.equals("!=")) {
            return 4;
        }
        return 0;
    }

}
