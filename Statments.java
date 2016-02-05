import java.util.ArrayList;


public class Statments {
    ArrayList<Statment> statments;

    public static Statments parse () {
        Statments result = new Statments();
        while (Parser.currentToken instanceof MoveToken || Parser.currentToken instanceof IfToken || Parser.currentToken instanceof IdentifierToken) {
            result.statments.add(Statment.parse());
        }
        return result;
    }
}
