public class MoveToken extends Token {
    String name;
    
    public MoveToken (String move, int lineNumber)
    {
        super(lineNumber);
        this.name = move;
    }
    
    @Override public String getName () {
        return name;
    }
}
