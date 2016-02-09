import java.util.ArrayList;

public class Parser {
    public static Token currentToken;
    static ArrayList<Token> input;
    public static String currProcArg;

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

    public static boolean moveToNext (Token token) {
        for (int i = input.indexOf(currentToken) + 1; i < input.size(); i ++) {
            if (input.get(i).getClass() == token.getClass()) {
                currentToken = input.get(i);
                return true;
            }
        }
        //If no further such token is found return false
        return false;
    }

    public static boolean moveToNextWithinIf (Token token) {
        while (!(Parser.currentToken instanceof EndIfToken)) {
            if (Parser.currentToken.getClass() == token.getClass()) {
                return true;
            } else if (Parser.currentToken instanceof EOIToken) {
                return false;
            } else if (Parser.currentToken instanceof MoveToken) {
                MoveStatment.parse();
            } else if (Parser.currentToken instanceof IfToken) {
                IfStatment.parse();
            } else if (Parser.currentToken instanceof ProcedureToken) {
                return false;
            }
            Parser.nextToken();
        }

        return false;
    }

    public static Root parse (ArrayList<Token> input) {
        Root program = new Root();
        Parser.input = input;
        if (input.size() > 0) {
            currentToken = input.get(0);
        }
        while (Parser.currentToken instanceof ProcedureToken) {

            program.procs.add(Procedure.parse());
        }
        return program;
    }
}
