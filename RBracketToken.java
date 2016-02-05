public class RBracketToken extends Token {
    private static int numberOfRBrackets = 0;

    public static int getNumberOfRBrackets () {
        return numberOfRBrackets;
    }

    public static void resetNumberOfRBrackets () {
        numberOfRBrackets = 0;
    }

    public RBracketToken (int lineNumber)
    {
        super(lineNumber);
        numberOfRBrackets ++;
    }
}
