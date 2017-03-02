package Token;

import antlr.CmmParser;

/**
 * Created by marco on 11/12/2016.
 */
public class TokenDictionary {

    public static String getTokenType(int typeCode) {

        String type = null;

        switch (typeCode){
            case CmmParser.IF:
            case CmmParser.ELSE:
            case CmmParser.WHILE:
            case CmmParser.BREAK:
            case CmmParser.INT:
            case CmmParser.DOUBLE:
            case CmmParser.READ:
            case CmmParser.WRITE:
                type = "keyword";
                break;
            case CmmParser.PARENTHESISL:
            case CmmParser.PARENTHESISR:
            case CmmParser.SQUAREBRACKETL:
            case CmmParser.SQUAREBRACKETR:
            case CmmParser.CURLYBRACEL:
            case CmmParser.CURLYBRACER:
                type = "Bracket";
                break;
            case CmmParser.MULDIVMOD:
            case CmmParser.ADDSUB:
            case CmmParser.COMPARE:
                type = "Operator";
                break;
            case CmmParser.ASSIGNMENT:
                type = "Assignment";
                break;
            case CmmParser.COMMA:
                type = "Comma";
                break;
            case CmmParser.ENDLINE:
                type = "Semicolon";
                break;
            case CmmParser.WS:
                type = "White Space";
                break;
            case CmmParser.ID:
                type = "Identifier";
                break;
            case CmmParser.INTCONST:
                type = "Integer Constant";
                break;
            case CmmParser.DOUBLECONST:
                type = "Double Constant";
                break;
            case CmmParser.TRUE:
            case CmmParser.FALSE:
                type = "Boolean Constant";
                break;
            case CmmParser.MULTICMNT:
            case CmmParser.LINECMNT:
                type = "Comment";
                break;
            default:
                type = "Unknown Type";
        }
        return type;
    }

}
