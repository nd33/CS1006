import java.util.ArrayList;

public class ErrorLog {
    private static ArrayList<Error> errors;
    private static boolean containsErrors = false;

    public static void logError (Error toLog) {
        if (getErrors() == null) {
            setErrors(new ArrayList<Error>());
        }
        getErrors().add(toLog);
        setContainsErrors(true);
    }

    public static void clear () {
        getErrors().clear();
        setContainsErrors(false);
    }

    public static boolean containsErrors () {
        return isContainsErrors();
    }

    public static void displayErrors () {
        if (getErrors() == null) {
            return;
        }

        for (Error e : getErrors()) {
            System.out.println(e.toString());
        }
    }

    public static ArrayList<Error> getErrors() {
        return errors;
    }

    public static void setErrors(ArrayList<Error> errors) {
        ErrorLog.errors = errors;
    }

    public static boolean isContainsErrors() {
        return containsErrors;
    }

    public static void setContainsErrors(boolean containsErrors) {
        ErrorLog.containsErrors = containsErrors;
    }
}
