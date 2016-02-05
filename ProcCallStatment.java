/**
 * Created by jm360 on 05/02/16.
 */
public class ProcCallStatment extends Statment {
    String procedure;
    Expression argument;

    public static ProcCallStatment parse () {
        ProcCallStatment result = new ProcCallStatment();
        result.procedure = Parser.currentToken.getName();
        Parser.nextToken();
        if (!(Parser.currentToken instanceof LBracketToken)) {
            //Add Error
        }
        Parser.nextToken();
        result.argument = Expression.parse();
        if (!(Parser.currentToken instanceof RBracketToken)) {
            //Add Error
        }
        Parser.nextToken();
        return result;
    }

    public String codeString () {
        return argument.toString() + "/Arg exch def\n" + procedure + "\n";
    }
}
