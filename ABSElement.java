
public abstract class ABSElement {
    private Token token;

    public ABSElement(Token token) {
        this.setToken(token);
    }

    public ABSElement() { //default constructor
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
