

public class CodeGenerator {

        final static String PROLOGUE = "%!PS-Adobe-3.0\n" +
                "/Xpos    { 300 } def\n" +
                "/Ypos    { 500 } def\n" +
                "/Heading { 0   } def\n" +
                "/Arg     { 0   } def\n" +
                "/RIGHT   {\n" +
                "Heading exch add Trueheading\n" +
                "/Heading exch def\n" +
                "} def\n" +
                "/LEFT {\n" +
                "Heading exch sub Trueheading\n" +
                "/Heading exch def\n" +
                "} def\n" +
                "/Trueheading {\n" +
                "360 mod dup\n" +
                "0 lt { 360 add } if\n" +
                "} def\n" +
                "/FORWARD {\n" +
                "dup  Heading sin mul\n" +
                "exch Heading cos mul\n" +
                "2 copy Newposition\n" +
                "rlineto\n" +
                "} def\n" +
                "/Newposition {\n" +
                "Heading 180 gt Heading 360 lt\n" +
                "and { neg } if exch\n" +
                "Heading  90 gt Heading 270 lt\n" +
                "and { neg } if exch\n" +
                "Ypos add /Ypos exch def\n" +
                "Xpos add /Xpos exch def\n" +
                "} def\n";

        final static String EPILOGUE = "Xpos Ypos moveto\n" +
                "MAIN\n" +
                "stroke\n" +
                "showpage\n";

        public static String generateCodeText(Root abstractSyntaxTree) {
                String result = abstractSyntaxTree.codeString();
                return PROLOGUE + "\n" + result + "\n" + EPILOGUE;
        }
}
