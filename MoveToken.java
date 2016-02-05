public class MoveToken extends Token {
    String name;
    
    public MoveToken (String move) {
        this.name = move;
    }
    
    @Override public String getName () {
        return name;
    }
}
