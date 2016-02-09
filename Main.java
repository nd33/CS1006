import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws IOException {

        Scanner consoleInput = new Scanner(System.in);
        System.out.println("Enter file name: ");
        String fileName = consoleInput.nextLine();

        if (!fileName.contains(".t")) {
            System.out.println("Expecting a LOGO file");
            return;
        }

        Root program = Parser.parse(Lexer.tokenise(FileManager.contentsOfFile(fileName)));

        if (ErrorLog.containsErrors()) {
            ErrorLog.displayErrors();
            return;
        } else {
            FileManager.writeStringToFile(CodeGenerator.generateCodeText(program), fileName.replace(".t", ".ps"));
        }

       //FileManager.writeStringToFile(CodeGenerator.generateCodeText(Parser.parse(Lexer.tokenise(FileManager.contentsOfFile("dragon.t")))), "dragon.txt");


    }
}
