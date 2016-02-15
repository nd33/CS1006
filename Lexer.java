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
        ArrayList <Token> line = new ArrayList<Token>();
        boolean statment = false;
        boolean proc = false;
        int currentLine = 1;
        Token previousToken = new EOSToken(0);

        for (String currentWord : words) {
            Token t;
            if (currentWord.equals("FORWARD") || currentWord.equals("LEFT") || currentWord.equals("RIGHT")) {
                if (statment) {
                    t = new EOSToken(currentLine);
                    t.setLine(line);
                    line.add(t);
                    result.add(t);
                } else {
                    statment = true;
                }
                t = new MoveToken(currentWord, currentLine);
                t.setLine(line);
                result.add(t);
            } else if (currentWord.equals("+") || currentWord.equals("-") || currentWord.equals("/") || currentWord.equals("*")
                    || currentWord.equals("==") || currentWord.equals("!=") || currentWord.equals("<=") || currentWord.equals(">=")
                    || currentWord.equals("<") || currentWord.equals(">")) {
                t = new OperatorToken(currentWord, currentLine);
                t.setLine(line);
                line.add(t);
                result.add(t);
            } else if (currentWord.equals("(")) {
                if (previousToken instanceof IdentifierToken && !proc) {
                    t = new MethodCallToken(currentLine, previousToken.getName());
                    t.setLine(previousToken.getLine());
                    line.add(t);
                    result.remove(previousToken);
                    previousToken.getLine().remove(previousToken);
                    result.add(t);
                    if (statment) {
                        t = new EOSToken(currentLine);
                        t.setLine(line);
                        line.add(line.size() - 1, t);
                        result.add(result.size() - 1, t);
                    } else {
                        statment = true;
                    }
                } else {
                    proc = false;
                }
                t = new LBracketToken(currentLine);
                t.setLine(line);
                line.add(t);
                result.add(t);
            } else if (currentWord.equals(")")) {
                t = new RBracketToken(currentLine);
                t.setLine(line);
                line.add(t);
                result.add(t);
            } else if (currentWord.equals("IF")) {
                if (statment) {
                    t = new EOSToken(currentLine);
                    t.setLine(line);
                    result.add(t);
                } else {

                }
                t = new IfToken(currentLine);
                t.setLine(line);
                line.add(t);
                result.add(t);
            } else if (currentWord.equals("THEN")) {
                t = new ThenToken(currentLine);
                t.setLine(line);
                line.add(t);
                result.add(t);
            } else if (currentWord.equals("ELSE")) {
                if (statment) {
                    t = new EOSToken(currentLine);
                    t.setLine(line);
                    result.add(t);
                    statment = false;
                }
                t = new ElseToken(currentLine);
                t.setLine(line);
                line.add(t);
                result.add(t);
            } else if (currentWord.equals("ENDIF")) {
                if (statment) {
                    t = new EOSToken(currentLine);
                    t.setLine(line);
                    result.add(t);
                    statment = false;
                }
                t = new EndIfToken(currentLine);
                t.setLine(line);
                line.add(t);
                result.add(t);
            } else if (currentWord.equals("PROC")) {
                t = new ProcedureToken(currentLine);
                t.setLine(line);
                line.add(t);
                result.add(t);
                proc = true;
            } else if (currentWord.equals("0") || (currentWord.charAt(0) > 48 && currentWord.charAt(0) < 58)) {
                try {
                    t = new NumberToken(Integer.valueOf(currentWord), currentLine);
                    t.setLine(line);
                    line.add(t);
                    result.add(t);;
                } catch (NumberFormatException e) {
                    ErrorLog.logError(new Error(currentLine, currentWord + " is not a legal identifier or number"));
                    //Add zero for the purposes finding further errors
                    t = new NumberToken(0, currentLine);
                    t.setLine(line);
                    line.add(t);
                    result.add(t);
                }
            } else if (currentWord.equals("\n")) {
                line = new ArrayList<Token>();
                currentLine ++;
            } else {
                t = new IdentifierToken(currentWord, currentLine);
                t.setLine(line);
                line.add(t);
                result.add(t);
            }
            previousToken = result.get(result.size() - 1);
        }

        result.add(new EOIToken(currentLine));

        return result;
    }
}
