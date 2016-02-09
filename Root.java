import java.util.ArrayList;

public class Root extends ABSElement {
    private ArrayList<Procedure> procs;

    public Root () {
        setProcs(new ArrayList<Procedure>());
    }

    public static Root parse () {
        Root result = new Root();
        if (Parser.getCurrentToken() == null) {
            result.setEmpty(true);
            return result;
        }

        if (!(Parser.getCurrentToken() instanceof ProcedureToken)) {
            ErrorLog.logError(new Error(Parser.getCurrentToken().getLineNumber(), "Program must start with a procedure"));
            if (!Parser.moveToNext(new ProcedureToken(0))) {
                result.setEmpty(true);
                return result;
            }
        }

        while (Parser.getCurrentToken() instanceof ProcedureToken) {
            result.getProcs().add(Procedure.parse());
        }

        return result;
    }

    public String codeString () {
        String result = "";
        for (Procedure p : getProcs()) {
            result += p.codeString();
        }
        return result;
    }

    public ArrayList<Procedure> getProcs() {
        return procs;
    }

    public void setProcs(ArrayList<Procedure> procs) {
        this.procs = procs;
    }
}
