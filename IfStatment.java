
public class IfStatment extends Statment {
    private Statments then;
    private Statments or;
    private Expression condition;

    public static IfStatment parse () {
        IfStatment result = new IfStatment();
        Parser.nextToken();
        result.setCondition(Expression.parse());
        if (!(Parser.getCurrentToken() instanceof ThenToken)) {
            //Add Error
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting 'THEN'", "Inserting 'THEN'"));
            Parser.moveToNext(new ThenToken(0));
        }
        Parser.nextToken();
        result.setThen(Statments.parse());
        if (!(Parser.getCurrentToken() instanceof ElseToken)) {
            //Add Error
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting 'ELSE'", "Inserting 'ELSE'"));
        }
        Parser.nextToken();
        result.setOr(Statments.parse());
        if (!(Parser.getCurrentToken() instanceof  EndIfToken)) {
            //Add Error
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Expecting 'ENDIF'", "Inserting 'ENDIF'"));
        }
        Parser.nextToken();
        return result;
    }

    public String codeString () {
        return getCondition().codeString() + "{\n" + getThen().codeString() + "} {\n" + getOr().codeString() + "} ifelse \n";
    }

    public Statments getThen() {
        return then;
    }

    public void setThen(Statments then) {
        this.then = then;
    }

    public Statments getOr() {
        return or;
    }

    public void setOr(Statments or) {
        this.or = or;
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }
}
