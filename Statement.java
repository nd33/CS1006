
public class Statement extends ABSElement {

    public Statement(Token token) {
        super(token);
    }

    public Statement() {

    }

    public static Statement parse() {
        Statement result = new Statement();
        if (Parser.getCurrentToken() instanceof MoveToken) {
            result = MoveStatement.parse();
            Parser.nextToken();
        } else if (Parser.getCurrentToken() instanceof IfToken) {
            result = IfStatement.parse();
        } else if (Parser.getCurrentToken() instanceof MethodCallToken) {
            result = ProcCallStatement.parse();
            Parser.nextToken();
        } else {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(),
                    "A statement must start with a move token, an if token or an identifier token", Parser.getCurrentToken()));
        }
        return result;
    }

    public String codeString() {
        return "";
    }
}
