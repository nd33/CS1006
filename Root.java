import java.util.ArrayList;

/**
 * Created by jm360 on 05/02/16.
 */
public class Root {
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
