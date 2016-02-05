import java.util.ArrayList;


public class Statments extends ABSElement {
    ArrayList<Statment> statments;

    public Statments () {
        statments = new ArrayList<Statment>();
    }

    public static Statments parse () {
        Statments result = new Statments();
        while (Parser.currentToken instanceof MoveToken || Parser.currentToken instanceof IfToken || Parser.currentToken instanceof IdentifierToken) {
            result.statments.add(Statment.parse());
        }
        return result;
    }

    public String codeString () {
        String result = "";
        for (Statment s : statments) {
            result += s.codeString();
        }
        return result;
    }
}
