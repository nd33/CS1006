import java.util.ArrayList;

public class PrimaryExpression extends Expression {
    int value;
    boolean isNumber;
    String name;

    public static PrimaryExpression parse (ArrayList<Token> RPNExpression) {
        PrimaryExpression result = new PrimaryExpression();
        if (RPNExpression.get(0) instanceof NumberToken) {
            result.value = RPNExpression.get(0).getValue();
            result.isNumber = true;
        } else if (RPNExpression.get(0) instanceof IdentifierToken) {
            result.name = RPNExpression.get(0).getName();
            result.isNumber = false;
        } else {
            //Add Errors
        }
        //Parser.nextToken();
        return result;
    }

    public String codeString () {
        if (isNumber) {
            return value + "\n";
        }else if(name.equals(Procedure.getArgument())){
            return "Arg" + "\n";

        }
        else{
            return name + "\n";
        }
    }
}
