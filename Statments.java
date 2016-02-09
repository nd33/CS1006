import java.util.ArrayList;


public class Statments extends ABSElement {
    private ArrayList<Statment> statments;

    public Statments () {
        setStatments(new ArrayList<Statment>());
    }

    public static Statments parse () {
        Statments result = new Statments();
        while (Parser.getCurrentToken() instanceof MoveToken || Parser.getCurrentToken() instanceof IfToken || Parser.getCurrentToken() instanceof IdentifierToken) {
            result.getStatments().add(Statment.parse());
        }
        return result;
    }

    public String codeString () {
        String result = "";
        for (Statment s : getStatments()) {
            result += s.codeString();
        }
        return result;
    }

    public ArrayList<Statment> getStatments() {
        return statments;
    }

    public void setStatments(ArrayList<Statment> statments) {
        this.statments = statments;
    }
}
