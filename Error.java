
public class Error {
    private int lineNumber;
    private String problem;
    private String potentialSolution;

    public Error (String problem) {
        this.setProblem(problem);
        setLineNumber(-1);
    }

    public Error (int lineNumber, String problem) {
        this(lineNumber, problem, null);
    }

    public Error (int lineNumber, String problem, String potentialSolution) {
        this.setLineNumber(lineNumber);
        this.setProblem(problem);
        this.setPotentialSolution(potentialSolution);
    }

    public String toString () {
        String result = "Error : ";
        if (getLineNumber() != 1) {
            result += "(" + getLineNumber() + ") ";
        }
        result += getProblem();
        if (getPotentialSolution() != null) {
            result += " Try : " + getPotentialSolution();
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
