import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        FileManager.writeStringToFile(CodeGenerator.generateCodeText(Parser.parse(Lexer.tokenise(FileManager.contentsOfFile("dragon.t")))), "dragon.txt");
    }
}
