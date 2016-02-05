/**
 * Created by jm360 on 05/02/16.
 */
public class Procedure extends ABSElement {
    String name;
    String argument;
    Statments body;

    public static Procedure parse () {
        Procedure result = new Procedure();
        Parser.nextToken();
        if (!(Parser.currentToken instanceof IdentifierToken)) {
            //Add Error
        }
        result.name = Parser.currentToken.getName();
        Parser.nextToken();
        if (!(Parser.currentToken instanceof LBracketToken)) {
            //Add Error
        }
        Parser.nextToken();
        if (!(Parser.currentToken instanceof IdentifierToken)) {
            //Add Error
        }
        result.argument = Parser.currentToken.getName();
        Parser.nextToken();
        if (!(Parser.currentToken instanceof RBracketToken)) {
            //Add Error
        }
        Parser.nextToken();
        result.body = Statments.parse();
        return result;
    }

    public String codeString () {
        return "/" + name + "{\n" + body.codeString() + "} def\n";
    }
}
