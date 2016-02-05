import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        Parser.parse(Lexer.tokenise(FileManager.contentsOfFile("dragon.t")));
    }
}
