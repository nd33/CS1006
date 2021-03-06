
public class ProcCallStatement extends Statement {
    private String procedure;
    private Expression argument;

    public ProcCallStatement(Token token) {
        super(token);
    }

    public static ProcCallStatement parse() {
        ProcCallStatement result = new ProcCallStatement(Parser.getCurrentToken());
        result.setProcedure(Parser.getCurrentToken().getName());
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof LBracketToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(),
                    "A '(' must follow the name of the procedure when you call it"));
        }
        result.setArgument(Expression.parse());
        Parser.previousToken();
        if (!(Parser.getCurrentToken() instanceof RBracketToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(),
                    "A ')' must follow the argument of the procedure when you call it"));
        }

        if (result.argument.numberOfBooleanOperators() != 0) {
            ErrorLog.logError(new Error(result.argument.getToken().getLineNumber(), "Argument cannot contain boolean operators", result.argument.getToken()));
        }

        Parser.nextToken();
        return result;
    }

    public String codeString() {
        if (!Parser.getProcedures().contains(procedure)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Undefined identifier '" + procedure + "'", getToken()));
        }
        String result = "Arg ";
        if (argument != null) {
            result += argument.codeString();
        }
        result += " /Arg exch def " + getProcedure() + " /Arg exch def " + "\n";
        return result;
        //return "Arg " + getArgument().codeString() + " /Arg exch def " + getProcedure() + " /Arg exch def " + "\n";
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public Expression getArgument() {
        return argument;
    }

    public void setArgument(Expression argument) {
        this.argument = argument;
    }
}
