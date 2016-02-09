public class MoveToken extends Token {
    private String name;
    
    public MoveToken (String move, int lineNumber)
    {
        super(lineNumber);
        this.setName(move);
    }
    
    @Override public String getName () {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
