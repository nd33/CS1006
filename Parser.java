import java.util.ArrayList;

public class Parser {
    private static Token currentToken;
    private static ArrayList<Token> input;
    private static String currProcArg;
    private static ArrayList<String> procedures = new ArrayList<String>();

    public static void nextToken() {
        if (getCurrentToken() == null) {
            return;
        } else if (getInput() == null) {
            return;
        } else if (getInput().lastIndexOf(getCurrentToken()) != getInput().size() - 1) {
            setCurrentToken(getInput().get(getInput().indexOf(getCurrentToken()) + 1));
        }
    }

    public static void previousToken() {
        if (getCurrentToken() == null) {
            return;
        } else if (getInput() == null) {
            return;
        } else if (getInput().lastIndexOf(getCurrentToken()) != 0) {
            setCurrentToken(getInput().get(getInput().indexOf(getCurrentToken()) - 1));
        }
    }

    public static void addProcedure(String name) {
        procedures.add(name);
    }

    public static boolean moveToNext(Token token) {
        while (!(Parser.getCurrentToken() instanceof EOIToken)) {
            if (Parser.getCurrentToken().getClass() == token.getClass()) {
                return true;
            } else if (Parser.getCurrentToken() instanceof MoveToken) {
                MoveStatement.parse();
            } else if (Parser.getCurrentToken() instanceof IfToken) {
                IfStatement.parse();
            } else if (Parser.getCurrentToken() instanceof MethodCallToken) {
                ProcCallStatement.parse();
            }
            Parser.nextToken();
        }
        //If no further such token is found return false
        return false;
    }

    public static void moveToNextStatementWithinProc() {
        while (!(Parser.getCurrentToken() instanceof ProcedureToken && !(Parser.getCurrentToken() instanceof EOIToken))) {
            if (Parser.getCurrentToken() instanceof MoveToken || Parser.getCurrentToken() instanceof IfToken || Parser.getCurrentToken() instanceof MethodCallToken) {
                return;
            }
            Parser.nextToken();
        }
    }


    public static boolean moveToNextWithinIf(Token token) {
        while (!(Parser.getCurrentToken() instanceof EndIfToken)) {
            if (Parser.getCurrentToken().getClass() == token.getClass()) {
                return true;
            } else if (Parser.getCurrentToken() instanceof EOIToken) {
                return false;
            } else if (Parser.getCurrentToken() instanceof MoveToken) {
                MoveStatement.parse();
            } else if (Parser.getCurrentToken() instanceof IfToken) {
                IfStatement.parse();
            } else if (Parser.getCurrentToken() instanceof ProcedureToken) {
                return false;
            }
            Parser.nextToken();
        }

        return false;
    }

    public static Root parse(ArrayList<Token> input) {
        Root program = new Root();
        Parser.setInput(input);
        if (input.size() > 0) {
            setCurrentToken(input.get(0));
        }
        program = Root.parse();
        return program;
    }

    public static Token getCurrentToken() {
        return currentToken;
    }

    public static ArrayList<String> getProcedures() {
        return procedures;
    }

    public static void setCurrentToken(Token currentToken) {
        Parser.currentToken = currentToken;
    }

    public static ArrayList<Token> getInput() {
        return input;
    }

    public static void setInput(ArrayList<Token> input) {
        Parser.input = input;
    }

    public static String getCurrProcArg() {
        return currProcArg;
    }

    public static void setCurrProcArg(String currProcArg) {
        Parser.currProcArg = currProcArg;
    }
}
