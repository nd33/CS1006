
public class Procedure extends ABSElement {
    private String name;
    private String argument;
    private Statements body;

    public static Procedure parse () {
        Procedure result = new Procedure();
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof IdentifierToken)) {
            //Add Error
        }
        result.setName(Parser.getCurrentToken().getName());
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof LBracketToken)) {
            //Add Error
        }
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof IdentifierToken)) {
            //Add Error
        }
        result.setArgument(Parser.getCurrentToken().getName());
        Parser.nextToken();
        if (!(Parser.getCurrentToken() instanceof RBracketToken)) {
            //Add Error
        }
        Parser.nextToken();
        result.setBody(Statements.parse());
        return result;
    }

    public String codeString () {
        Parser.setCurrProcArg(this.getArgument());

        return "/" + getName() + " {\n" + getBody().codeString() + "} def\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public Statements getBody() {
        return body;
    }

    public void setBody(Statements body) {
        this.body = body;
    }
}
