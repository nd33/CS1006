import java.io.*;

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

    static void writeStringToFile(String string, String fileLocation) throws IOException {
        PrintWriter printWriter = new PrintWriter(fileLocation);
        printWriter.print(string);
        printWriter.close();
    }

}
