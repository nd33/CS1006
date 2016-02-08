import java.util.ArrayList;

public class ErrorLog {
    private static ArrayList<Error> errors;
    private static boolean containsErrors = false;

    public static void logError (Error toLog) {
        if (errors == null) {
            errors = new ArrayList<Error>();
        }
        errors.add(toLog);
        containsErrors = true;
    }

    public static void clear () {
        errors.clear();
        containsErrors = false;
    }

    public static boolean containsErrors () {
        return containsErrors;
    }

    public static void displayErrors () {
        if (errors == null) {
            return;
        }

        for (Error e : errors) {
            System.out.println(e.toString());
        }
    }
}
