import sun.tools.jstat.Operator;

public class OperatorToken extends Token {
    private String operator;

    public OperatorToken (String operator, int lineNumber)
    {
        super(lineNumber);
        this.operator = operator;
    }

    @Override public String getName () {
        return operator;
    }

    @Override public int getPrecedence () {
        if (operator.equals("+") || operator.equals("-")) {
            return 3;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 4;
        } else if (operator.equals("<") || operator.equals(">") || operator.equals("<=") || operator.equals(">=")) {
            return 2;
        } else if (operator.equals("==") || operator.equals("!=")) {
            return 1;
        }
        return 0;
    }

}
