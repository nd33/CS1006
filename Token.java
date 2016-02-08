public abstract class Token {
    protected int lineNumber;
    boolean legal;

    public Token (int lineNumber) {
        this.lineNumber = lineNumber;
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
}
