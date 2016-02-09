
public class Statment extends ABSElement {
    public static Statment parse () {
        Statment result = new Statment();
        if (Parser.getCurrentToken() instanceof MoveToken) {
            result = MoveStatment.parse();
            Parser.nextToken();
        } else if (Parser.getCurrentToken() instanceof IfToken) {
            result = IfStatment.parse();
        } else if (Parser.getCurrentToken() instanceof IdentifierToken) {
            result = ProcCallStatment.parse();
            Parser.nextToken();
        } else {
            //Add Error
        }
        return result;
    }

    public String codeString () {
        return "";
    }
}
