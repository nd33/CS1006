
public class MethodCallToken extends Token {

    private String method;

    public MethodCallToken(int lineNumber, String method) {
        super(lineNumber);
        this.method = method;
    }

    public String getName() {
        return method;
    }

}
