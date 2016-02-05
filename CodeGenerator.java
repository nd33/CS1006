

public class CodeGenerator {
    public static String generateCodeText (Root abstractSyntaxTree) {
        String result = abstractSyntaxTree.codeString();
        return result;
    }
}
