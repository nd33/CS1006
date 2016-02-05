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

        for (String currentWord : words) {
            if (currentWord.equals("FORWARD") || currentWord.equals("LEFT") || currentWord.equals("RIGHT")) {
                result.add(new MoveToken(currentWord));
                statment = true;
            } else if (currentWord.equals("+") || currentWord.equals("-") || currentWord.equals("/") || currentWord.equals("*")
                    || currentWord.equals("==") || currentWord.equals("!=") || currentWord.equals("<=") || currentWord.equals(">=")
                    || currentWord.equals("<") || currentWord.equals(">")) {
                result.add(new OperatorToken(currentWord));
            }else if (currentWord.equals("(")) {
                result.add(new LBracketToken());
            } else if (currentWord.equals(")")) {
                result.add(new RBracketToken());
            } else if (currentWord.equals("IF")) {
                result.add(new IfToken());
            } else if (currentWord.equals("THEN")) {
                result.add(new ThenToken());
            } else if (currentWord.equals("ELSE")) {
                result.add(new ElseToken());
            } else if (currentWord.equals("ENDIF")) {
                result.add(new EndIfToken());
            } else if (currentWord.equals("PROC")) {
                result.add(new ProcedureToken());
            } else if (currentWord.equals("0") || (currentWord.charAt(0) > 48 && currentWord.charAt(0) < 58)) {
                result.add(new NumberToken(Integer.valueOf(currentWord)));
            } else if (currentWord.equals("\n")) {
                if (statment) {
                    result.add(new EOSToken());
                    statment = false;
                }
            } else {
                result.add(new IdentifierToken(currentWord));
            }
        }

        result.add(new EOIToken());

        return result;
    }
}
