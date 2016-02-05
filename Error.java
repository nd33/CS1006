/**
 * Created by joemarch on 05/02/2016.
 */
public class Error {
    int lineNumber;
    String problem;
    String potentialSolution;

    public Error (String problem) {
        this.problem = problem;
        lineNumber = -1;
    }

    public Error (int lineNumber, String problem, String potentialSolution) {
        this.lineNumber = lineNumber;
        this.problem = problem;
        this.potentialSolution = potentialSolution;
    }

    public String toString () {
        String result = "Error : ";
        if (lineNumber != 1) {
            result += "(" + lineNumber + ") ";
        }
        result += problem;
        if (potentialSolution != null) {
            result += " Try : " + potentialSolution;
        }
        return result;
    }
}