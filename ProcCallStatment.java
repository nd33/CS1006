
public class ProcCallStatment extends Statment {
    private String procedure;
    private Expression argument;

    public static ProcCallStatment parse () {
        ProcCallStatment result = new ProcCallStatment();
        result.setProcedure(Parser.getCurrentToken().getName());
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof LBracketToken)) {
            //Add Error
        }
        //Parser.nextToken();
        result.setArgument(Expression.parse());
        Parser.previousToken();
        if (!(Parser.getCurrentToken() instanceof RBracketToken)) {
            //Add Error
        }
        Parser.nextToken();
        //Parser.nextToken();
        return result;
    }

    public String codeString () {
        return getArgument().codeString() + "/Arg exch def\n" + getProcedure() + "\n";
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
