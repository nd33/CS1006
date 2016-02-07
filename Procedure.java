
public class Procedure extends ABSElement {
    String name;
     static String argument;
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
        result.setArgument(Parser.currentToken.getName());
        Parser.nextToken();
        if (!(Parser.currentToken instanceof RBracketToken)) {
            //Add Error
        }
        Parser.nextToken();
        result.body = Statments.parse();
        return result;
    }

    public static  String getArgument() {
        return argument;
    }

    public static void setArgument(String argument) {
        Procedure.argument = argument;
    }

    public String codeString () {
        return "/" + name + "{\n" + body.codeString() + "} def\n";
    }
}
