public class RBracketToken extends Token {
    private static int numberOfRBrackets = 0;

    public static int getNumberOfRBrackets () {
        return numberOfRBrackets;
    }

    public static void resetNumberOfRBrackets () {
        setNumberOfRBrackets(0);
    }

    public RBracketToken (int lineNumber)
    {
        super(lineNumber);
        setNumberOfRBrackets(getNumberOfRBrackets() + 1);
    }

    public String getName () {
        return ")";
    }

    public static void setNumberOfRBrackets(int numberOfRBrackets) {
        RBracketToken.numberOfRBrackets = numberOfRBrackets;
    }
}
