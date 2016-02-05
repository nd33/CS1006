public abstract class Token {
    protected int lineNumber;
    boolean legal;
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
