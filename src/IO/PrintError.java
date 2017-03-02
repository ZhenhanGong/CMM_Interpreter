package IO;

import org.antlr.v4.runtime.Token;

/**
 * Created by marco on 18/12/2016.
 */

// a specialized class for printing ERROR information (DEBUG information not included)
public class PrintError {

    // declare symbol and reslove symbol error

    public static void printConflictDeclare(IO io, Token token) {

        io.output("ERROR  :  conflict declaration <"
                + token.getText() + "> in same scope in line "
                + token.getLine() + ":" + token.getCharPositionInLine());
    }

    public static void printCannotResloveSymbol(IO io, Token token) {

        io.output("ERROR  :  cannot reslove symbol <"
                + token.getText() + "> in line "
                + token.getLine() + ":" + token.getCharPositionInLine());
    }



    // variable type error

    public static void printUnmatchType(IO io, Token token) {

        io.output("ERROR  :  unmatched type on two side of = <"
                + token.getText() + "> in line "
                + token.getLine() +":" + token.getCharPositionInLine());
    }

    public static void printIllegalType(IO io, Token token) {

        io.output("ERROR  :  expect int or double in calculation <"
                + token.getText() + "> in line "
                + token.getLine() +":" + token.getCharPositionInLine());
    }

    public static void printNotArray(IO io, Token token) {

        io.output("ERROR  :  expect array variable <"
                + token.getText() + "[]> in line "
                + token.getLine() + ":" + token.getCharPositionInLine());
    }

    public static void printConditionNotBool(IO io, Token token) {

        io.output("ERROR  :  condition is not boolean <"
                + token.getText() + "()> in line "
                + token.getLine() + ":" + token.getCharPositionInLine());
    }



    // index error

    public static void printIndexNotConstant(IO io, Token token) {

        io.output("ERROR  :  expect integer constant index in array declaration <"
                + token.getText() + "[]> in line "
                + token.getLine() +":" + token.getCharPositionInLine());
    }

    public static void printIndexNotInt(IO io, Token token) {

        io.output("ERROR  :  array index should be integer <"
                + token.getText() + "[]> in line "
                + token.getLine() +":" + token.getCharPositionInLine());
    }

    public static void printIndexOutOfBound(IO io, Token token) {

        io.output("ERROR  :  index out of boundary of array <"
                + token.getText() + "[]> in line "
                + token.getLine() + ":" + token.getCharPositionInLine());
    }



    public static void printDivideByZero(IO io, Token token) {

        io.output("ERROR  :  divided by 0 in line " + token.getLine() + ": "
                + token.getCharPositionInLine());
    }


    public static void printIllegalOperation(IO io, Token token) {

        io.output("ERROR  :  illegal operation <"
                + token.getText() + "[]> in line "
                + token.getLine() + ":" + token.getCharPositionInLine());
    }


}
