
public class IfStatement extends Statement {
    private Statements then;
    private Statements or;
    private Expression condition;

    public static IfStatement parse () {
        IfStatement result = new IfStatement();
        Parser.nextToken();
        result.setCondition(Expression.parse());
        if (!(Parser.getCurrentToken() instanceof ThenToken)) {
            //Add Error
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting 'THEN'", "Inserting 'THEN'"));
            Parser.moveToNext(new ThenToken(0));
        }
        Parser.nextToken();
        result.setThen(Statements.parse());
        if (!(Parser.getCurrentToken() instanceof ElseToken)) {
            //Add Error
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting 'ELSE'", "Inserting 'ELSE'"));
            Parser.moveToNext(new ElseToken(0));
        }
        Parser.nextToken();
        result.setOr(Statements.parse());
        if (!(Parser.getCurrentToken() instanceof  EndIfToken)) {
            //Add Error
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting 'ENDIF'", "Inserting 'ENDIF'"));
            Parser.moveToNext(new ElseToken(0));
        }
        Parser.nextToken();
        return result;
    }

    public String codeString () {
        return getCondition().codeString() + "{\n" + getThen().codeString() + "} {\n" + getOr().codeString() + "} ifelse \n";
    }

    public Statements getThen() {
        return then;
    }

    public void setThen(Statements then) {
        this.then = then;
    }

    public Statements getOr() {
        return or;
    }

    public void setOr(Statements or) {
        this.or = or;
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }
}
