import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        Lexer.tokenise(FileManager.contentsOfFile("dragon.t"));
    }
}
