/**
 * Created by jm360 on 05/02/16.
 */
public class IfStatment extends Statment {
    Statments then;
    Statments or;
    Expression condition;

    public static IfStatment parse () {
        IfStatment result = new IfStatment();
        Parser.nextToken();
        result.condition = Expression.parse();
        if (!(Parser.currentToken instanceof ThenToken)) {
            //Add Error
            ErrorLog.logError(new Error(Parser.currentToken.getLineNumber(), "Expecting 'THEN'", "Inserting 'THEN'"));
            Parser.moveToNext(new ThenToken(0));
        }
        Parser.nextToken();
        result.then = Statments.parse();
        if (!(Parser.currentToken instanceof ElseToken)) {
            //Add Error
            ErrorLog.logError(new Error(Parser.currentToken.getLineNumber(), "Expecting 'ELSE'", "Inserting 'ELSE'"));
        }
        Parser.nextToken();
        result.or = Statments.parse();
        if (!(Parser.currentToken instanceof  EndIfToken)) {
            //Add Error
            ErrorLog.logError(new Error(Parser.currentToken.getLineNumber(), "Expecting 'ENDIF'", "Inserting 'ENDIF'"));
        }
        Parser.nextToken();
        return result;
    }

    public String codeString () {
        return condition.codeString() + "{\n" + then.codeString() + "} {\n" + or.codeString() + "} ifelse \n";
    }
}
