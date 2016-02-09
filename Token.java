public abstract class Token {
    private int lineNumber;

    public Token (int lineNumber) {
        this.setLineNumber(lineNumber);
    }

    public int getLineNumber () {
        return lineNumber;
    }

    public int getPrecedence (){
        //Override for Operators
        return 0;
    }
    public int getValue () {
        //Override for NumberToken
        return 0;
    }

    public String getName () {
        //Override for identifier
        return "";
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
