import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {
    static String contentsOfFile(String fileLocation) throws IOException {
        String result = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation));
        String currentLine = bufferedReader.readLine();

        while (currentLine != null) {
            if (!currentLine.equals("")) {
                result += currentLine + "\n";
            }
            currentLine = bufferedReader.readLine();
        }

        return result;
    }
}
