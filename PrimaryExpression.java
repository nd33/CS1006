import java.util.ArrayList;

public class PrimaryExpression extends Expression {
    private int value;
    private boolean isNumber;
    private String name;

    public static PrimaryExpression parse (ArrayList<Token> RPNExpression) {
        PrimaryExpression result = new PrimaryExpression();
        if (RPNExpression.get(0) instanceof NumberToken) {
            result.setValue(RPNExpression.get(0).getValue());
            result.setNumber(true);
        } else if (RPNExpression.get(0) instanceof IdentifierToken) {
            result.setName(RPNExpression.get(0).getName());
            result.setNumber(false);
        } else {
            //Add Errors
        }
        //Parser.nextToken();
        return result;
    }

    public String codeString () {
        if (isNumber()) {
            return getValue() + " ";
        }else if(this.getName().equals(Parser.getCurrProcArg())){
            return "Arg " ;
        }
        else{
            return getName() + " ";
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public void setNumber(boolean number) {
        isNumber = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
