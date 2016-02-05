import java.util.ArrayList;

public class ErrorLog {
    private static ArrayList<Error> errors;

    public static void logError (Error toLog) {
        if (errors == null) {
            errors = new ArrayList<Error>();
        }
        errors.add(toLog);
    }

    public static void clear () {
        errors.clear();
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
