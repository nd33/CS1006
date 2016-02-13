import java.util.ArrayList;

public class Error {
    private int lineNumber;
    private String problem;
    private String potentialSolution;
    private Token cause;

    public Error (String problem) {
        this.setProblem(problem);
        setLineNumber(-1);
    }

    public Error (int lineNumber, String problem) {
        this.lineNumber = lineNumber;
        this.problem = problem;
        this.potentialSolution = null;
    }

    public Error (int lineNumber, String problem, String potentialSolution) {
        this.setLineNumber(lineNumber);
        this.setProblem(problem);
        this.setPotentialSolution(potentialSolution);
    }

    public Error (int lineNumber, String problem, String potentialSolution, Token cause) {
        this.setLineNumber(lineNumber);
        this.setProblem(problem);
        this.setPotentialSolution(potentialSolution);
        this.cause = cause;
    }

    public Error (int lineNumber, String problem, Token cause) {
        this(lineNumber, problem);
        this.cause = cause;
    }

    public String toString () {
        String result = "Error : ";
        if (getLineNumber() != -1) {
            result += "(" + getLineNumber() + ") ";
        }
        result += getProblem();
        if (getPotentialSolution() != null) {
            result += " Try : " + getPotentialSolution();
        }

        if (cause != null) {
            result += " In : ";
            for (Token t : cause.getLine()) {
                if (t == cause) {
                    result += "\033[91m";
                }
                result += t.getName() + " ";
                result += "\033[39m";
            }
        }
        return result;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getPotentialSolution() {
        return potentialSolution;
    }

    public void setPotentialSolution(String potentialSolution) {
        this.potentialSolution = potentialSolution;
    }
}
