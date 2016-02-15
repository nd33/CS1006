import java.util.ArrayList;

public abstract class Token {
    private int lineNumber;


    private ArrayList<Token> line;

    public Token(int lineNumber) {
        this.setLineNumber(lineNumber);
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getPrecedence() {
        //Override for Operators
        return 0;
    }

    public int getValue() {
        //Override for NumberToken
        return 0;
    }

    public String getName() {
        return "";
    }

    public ArrayList<Token> getLine() {
        return line;
    }

    public void setLine(ArrayList<Token> line) {
        this.line = line;
    }


    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
