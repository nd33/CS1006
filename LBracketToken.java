public class LBracketToken extends Token {
    private static int numberOfLBrackets = 0;

    public static int getNumberOfRBrackets () {
        return getNumberOfLBrackets();
    }

    public static void resetNumberOfRBrackets () {
        setNumberOfLBrackets(0);
    }

    public LBracketToken (int lineNumber) {
        super(lineNumber);
        setNumberOfLBrackets(getNumberOfLBrackets() + 1);
    }

    public String getName () {
        return "(";
    }

    public static int getNumberOfLBrackets() {
        return numberOfLBrackets;
    }

    public static void setNumberOfLBrackets(int numberOfLBrackets) {
        LBracketToken.numberOfLBrackets = numberOfLBrackets;
    }
}
