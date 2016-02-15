import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            Scanner consoleInput = new Scanner(System.in);
            System.out.println("Enter file name: ");
            String fileName = consoleInput.next();

            if (!fileName.endsWith(".t")) {
                System.out.println("Expecting a LOGO file");
                return;
            }

            Root program = Parser.parse(Lexer.tokenise(FileManager.contentsOfFile(fileName)));

            System.out.println("Enter output file name");
            String outFileName = consoleInput.next();
            if (!outFileName.endsWith(".ps") && !outFileName.endsWith(".eps")) {
                outFileName += ".ps";
            }


            if (ErrorLog.containsErrors()) {
                ErrorLog.displayErrors();
                return;
            } else {
                String output = CodeGenerator.generateCodeText(program);

                if (ErrorLog.containsErrors()) {
                    ErrorLog.displayErrors();
                    return;
                } else {
                    FileManager.writeStringToFile(CodeGenerator.generateCodeText(program), outFileName);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException IO) {
            System.out.println(IO.getMessage());
        }
    }
}
