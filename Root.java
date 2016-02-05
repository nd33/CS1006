import java.util.ArrayList;

public class Root extends ABSElement {
    ArrayList<Procedure> procs;

    public Root () {
        procs = new ArrayList<Procedure>();
    }

    public static Root parse () {
        Root result = new Root();
        if (Parser.currentToken == null) {
            return result;
        }

        while (Parser.currentToken instanceof ProcedureToken) {
            result.procs.add(Procedure.parse());
        }

        return result;
    }

    public String codeString () {
        String result = "";
        for (Procedure p : procs) {
            result += p.codeString();
        }
        return result;
    }
}
