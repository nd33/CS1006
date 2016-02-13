
public abstract class ABSElement {
    private boolean empty;
    private int lineNumber;
    private String line;
    private Token token;

    public ABSElement(Token token){
        this.setToken(token);
    }

    public ABSElement(){
    }
    public void setEmpty (boolean empty) {
        empty = empty;
    }

    public boolean isEmpty() {
        return empty;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }


    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
