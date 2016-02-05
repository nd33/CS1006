import java.util.ArrayList;

public class Parser {
    public static Token currentToken;
    static ArrayList<Token> input;

    public static void nextToken () {
        if (currentToken == null) {
            return;
        } else if (input == null) {
            return;
        } else if (input.lastIndexOf(currentToken) != input.size() - 1) {
            currentToken = input.get(input.indexOf(currentToken) + 1);
        }
    }

    public static void previousToken () {
        if (currentToken == null) {
            return;
        } else if (input == null) {
            return;
        } else if (input.lastIndexOf(currentToken) != 0) {
            currentToken = input.get(input.indexOf(currentToken) - 1);
        }
    }

    public static Root parse (ArrayList<Token> input) {
        Root program = new Root();
        Parser.input = input;
        if (input.size() > 0) {
            currentToken = input.get(0);
        }
        return program;
    }
}
