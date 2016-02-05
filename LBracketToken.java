public class LBracketToken extends Token {
    private static int numberOfLBrackets = 0;

    public static int getNumberOfRBrackets () {
        return numberOfLBrackets;
    }

    public static void resetNumberOfRBrackets () {
        numberOfLBrackets = 0;
    }

    public LBracketToken () {
        numberOfLBrackets ++;
    }
}
