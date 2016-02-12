
public class Procedure extends ABSElement {
    private String name;
    private String argument;
    private Statements body;

    public static Procedure parse () {
        Procedure result = new Procedure();
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof IdentifierToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting identifier after 'PROC'"));
        }
        result.setName(Parser.getCurrentToken().getName());
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof LBracketToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "A '(' must follow the name of the procedure"));
        }
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof IdentifierToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "An identifier must be the argument of the procedure"));
        }
        result.setArgument(Parser.getCurrentToken().getName());
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof RBracketToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "A ')' must follow the argument of the procedure"));
        }
        Parser.nextToken();
        result.setBody(Statements.parse());
        return result;
    }

    public String codeString () {
        Parser.setCurrProcArg(this.getArgument());

        return "/" + getName() + " {\n" + getBody().codeString() + "} def\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public Statements getBody() {
        return body;
    }

    public void setBody(Statements body) {
        this.body = body;
    }
}
