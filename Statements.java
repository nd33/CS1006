import java.util.ArrayList;


public class Statements extends ABSElement {
    private ArrayList<Statement> statements;

    public Statements() {
        setStatements(new ArrayList<Statement>());
    }

    public static Statements parse() {
        Statements result = new Statements();


        while (Parser.getCurrentToken() instanceof MoveToken || Parser.getCurrentToken() instanceof IfToken || Parser.getCurrentToken() instanceof MethodCallToken) {
            result.getStatements().add(Statement.parse());
        }
        return result;
    }

    public String codeString() {
        String result = "";
        for (Statement s : getStatements()) {
            result += s.codeString();
        }
        return result;
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Statement> statements) {
        this.statements = statements;
    }
}
