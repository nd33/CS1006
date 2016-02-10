import java.util.ArrayList;

public class Lexer {

    private static boolean isOpChar (char c) {
        switch (c) {
            case '+' :
            case '-' :
            case '/' :
            case '*' :
            case '(' :
            case ')' :
            case '=' :
            case '<' :
            case '>' :
            case '!' :
            case '\n' :
                return true;
        }
        return false;
    }

    private static ArrayList<String> splitIntoWords (String input) {
        ArrayList<String> result = new ArrayList<String>();
        String currentWord = "";

        for (char currentChar : input.toCharArray()) {
            if (currentChar != ' ' && currentChar != '\t') {
                if (isOpChar(currentChar) && !currentWord.equals("")) {
                    result.add(currentWord);
                    currentWord = String.valueOf(currentChar);
                    result.add(currentWord);
                    currentWord = "";
                } else if (isOpChar(currentChar)) {
                    currentWord = String.valueOf(currentChar);
                    result.add(currentWord);
                    currentWord = "";
                } else {
                    currentWord += currentChar;
                }
            } else {
                if (!currentWord.isEmpty()) {
                    result.add(currentWord);
                    currentWord = "";
                }
            }
        }

        return result;
    }

    private static ArrayList<String> pairDoubleOperators (ArrayList<String> input) {
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < input.size(); i ++) {
            if (i < input.size() - 2 && (input.get(i).equals("=") || input.get(i).equals("<") || input.get(i).equals(">") || input.get(i).equals("!"))
                    && input.get(i + 1).equals("=")) {
                result.add(input.get(i) + input.get(i + 1));
                i ++;
            } else {
                result.add(input.get(i));
            }
        }
        return result;
    }

    public static ArrayList<Token> tokenise (String input) {
        ArrayList<String> words = pairDoubleOperators(splitIntoWords(input));
        ArrayList <Token> result = new ArrayList<Token>();
        boolean statment = false;
        boolean proc = false;
        int currentLine = 1;
        Token previousToken = new EOSToken(0);

        for (String currentWord : words) {
            if (currentWord.equals("FORWARD") || currentWord.equals("LEFT") || currentWord.equals("RIGHT")) {
                if (statment) {
                    result.add(new EOSToken(currentLine));
                } else {
                    statment = true;
                }
                result.add(new MoveToken(currentWord, currentLine));
            } else if (currentWord.equals("+") || currentWord.equals("-") || currentWord.equals("/") || currentWord.equals("*")
                    || currentWord.equals("==") || currentWord.equals("!=") || currentWord.equals("<=") || currentWord.equals(">=")
                    || currentWord.equals("<") || currentWord.equals(">")) {
                result.add(new OperatorToken(currentWord, currentLine));
            }else if (currentWord.equals("(")) {
                if (previousToken instanceof IdentifierToken && !proc) {
                    result.set(result.size() - 1, new MethodCallToken(currentLine, previousToken.getName()));
                    if (statment) {
                        result.add(result.size() - 1, new EOSToken(currentLine));
                    } else {
                        statment = true;
                    }
                } else {
                    proc = false;
                }
                result.add(new LBracketToken(currentLine));
            } else if (currentWord.equals(")")) {
                result.add(new RBracketToken(currentLine));
            } else if (currentWord.equals("IF")) {
                if (statment) {
                    result.add(new EOSToken(currentLine));
                } else {

                }
                result.add(new IfToken(currentLine));
            } else if (currentWord.equals("THEN")) {
                result.add(new ThenToken(currentLine));
            } else if (currentWord.equals("ELSE")) {
                if (statment) {
                    result.add(new EOSToken(currentLine));
                    statment = false;
                }
                result.add(new ElseToken(currentLine));
            } else if (currentWord.equals("ENDIF")) {
                if (statment) {
                    result.add(new EOSToken(currentLine));
                    statment = false;
                }
                result.add(new EndIfToken(currentLine));
            } else if (currentWord.equals("PROC")) {
                result.add(new ProcedureToken(currentLine));
                proc = true;
            } else if (currentWord.equals("0") || (currentWord.charAt(0) > 48 && currentWord.charAt(0) < 58)) {
                try {
                    result.add(new NumberToken(Integer.valueOf(currentWord), currentLine));
                } catch (NumberFormatException e) {
                    ErrorLog.logError(new Error(currentLine, currentWord + " is not a legal identifier or number"));
                    //Add zero for the purposes finding further errors
                    result.add(new NumberToken(0, currentLine));
                }
            } else if (currentWord.equals("\n")) {
                currentLine ++;
            } else {
                result.add(new IdentifierToken(currentWord, currentLine));
            }
            previousToken = result.get(result.size() - 1);
        }

        result.add(new EOIToken(currentLine));

        return result;
    }
}
