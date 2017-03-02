package Expression;

import Toggle.Toggle;
import Symbol.Symbol;
import Type.Type;
import IO.IO;
import antlr.CmmBaseVisitor;
import Scope.Scope;
import antlr.CmmParser;
import org.antlr.v4.runtime.Token;
import Operation.Operation;
import org.antlr.v4.runtime.tree.TerminalNode;
import IO.PrintError;

import static Type.Type.*;

/**
 * Created by marco on 15/12/2016.
 */
public class ExprCalcVisitor extends CmmBaseVisitor<ExprValue> {

    private Scope currentScope;
    private IO io;

    public ExprCalcVisitor(Scope currentScope, IO io) {

        this.currentScope = currentScope;
        this.io = io;
    }



    @Override
    public ExprValue visitAddSubExpr(CmmParser.AddSubExprContext ctx) {

        Token operator = ctx.ADDSUB().getSymbol();
        ExprValue result = new ExprValue();

        ExprValue left = visit(ctx.expr(0));
        ExprValue right = visit(ctx.expr(1));

        boolean isAdd = operator.getText().equals("+");
        boolean isSubtract = operator.getText().equals("-");

        if (isAdd)
            result = calc(Operation.add, operator, left, right);
        else if (isSubtract)
            result = calc(Operation.subtract, operator, left, right);
        else
            result.setValue(null);

        return result;
    }

    @Override
    public ExprValue visitMulDivModExpr(CmmParser.MulDivModExprContext ctx) {

        Token operator = ctx.MULDIVMOD().getSymbol();
        ExprValue result = new ExprValue();

        ExprValue left = visit(ctx.expr(0));
        ExprValue right = visit(ctx.expr(1));

        boolean isMultiply = operator.getText().equals("*");
        boolean isDivide = operator.getText().equals("/");
        boolean isModule = operator.getText().equals("%");

        if (isMultiply)
            result = calc(Operation.multiply, operator, left, right);
        else if (isDivide)
            result = calc(Operation.divide, operator, left, right);
        else if (isModule)
            result = calc(Operation.module, operator, left, right);
        else
            result.setValue(null);

        return result;
    }

    @Override
    public ExprValue visitCompareExpr(CmmParser.CompareExprContext ctx) {

        Token operator = ctx.COMPARE().getSymbol();

        ExprValue left = visit(ctx.expr(0));
        ExprValue right = visit(ctx.expr(1));
        ExprValue result = compare(operator, left, right);

        if (Toggle.DEBUG)
            io.output("DEBUG:  <comparation>   - " + left.getValue()
                    + " " + operator.getText() + " " + right.getValue());

        return result;
    }

    @Override
    public ExprValue visitMinusExpr(CmmParser.MinusExprContext ctx) {

        Token operator = ctx.ADDSUB().getSymbol();
        ExprValue result = visit(ctx.expr());

        boolean isMinus = operator.getText().equals("-");
        boolean isAdd = operator.getText().equals("+");
        boolean expressionResultIsInt = (result.getType() == cmmInt);
        boolean expressionResultIsDouble = (result.getType() == cmmDouble);

        if (isMinus) {

            if (expressionResultIsInt)
                result.setValue(-(Integer)result.getValue());
            else if (expressionResultIsDouble)
                result.setValue(-(Double)result.getValue());
            else
                PrintError.printIllegalType(io, operator);

        } else if (isAdd) {

            // do nothing

        } else {

            result.setValue(null);
        }
        return result;
    }

    @Override
    public ExprValue visitParenthsisExpr(CmmParser.ParenthsisExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public ExprValue visitConstant(CmmParser.ConstantContext ctx) {

        boolean constantIsInt = (ctx.INTCONST() != null);
        boolean constantIsDouble = (ctx.DOUBLECONST() != null);
        boolean constantIsTrue = (ctx.TRUE() != null);
        boolean constantIsFalse = (ctx.FALSE() != null);

        if (constantIsInt) {

            int constantValue = Integer.valueOf(ctx.INTCONST().getText());
            return new ExprValue(Type.cmmInt, constantValue);

        } else if (constantIsDouble) {

            double constantValue = Double.valueOf(ctx.DOUBLECONST().getText());
            return new ExprValue(Type.cmmDouble, constantValue);

        } else if (constantIsTrue) {

            return new ExprValue(Type.cmmBoolean, true);

        } else if (constantIsFalse) {

            return new ExprValue(Type.cmmBoolean, false);

        } else {

            return null;
        }
    }

    @Override
    public ExprValue visitIDExpr(CmmParser.IDExprContext ctx) {

        ExprValue result = new ExprValue();

        TerminalNode id = ctx.identifier().ID();
        TerminalNode array = null;

        CmmParser.ArrayContext arrayContext = ctx.identifier().array();
        if (arrayContext != null)
            array = arrayContext.ID();

        boolean identifierIsVariable = (id != null);
        boolean identifierIsArray = (array != null);

        if (identifierIsVariable) {
            result = getVariableValue(id);

        } else if (identifierIsArray) {
            result = getArrayElementValue(arrayContext);

        } else {
            result.setValue(null);
        }
        return result;
    }





    private double getCompareExpressionValue(ExprValue exprValue, Token operator) {

        boolean valueIsInt = (exprValue.getValue() instanceof Integer);
        boolean valueIsDouble = (exprValue.getValue() instanceof Double);
        double leftValue;

        if (valueIsInt)
            leftValue = (Integer)exprValue.getValue();
        else if (valueIsDouble)
            leftValue = (Double)exprValue.getValue();
        else {
            PrintError.printIllegalType(io, operator);
            leftValue = 0.0;
        }
        return leftValue;
    }

    private ExprValue compare(Token operator, ExprValue left, ExprValue right) {

        ExprValue result = new ExprValue();
        String operation = operator.getText();
        double leftValue = getCompareExpressionValue(left, operator);
        double rightValue = getCompareExpressionValue(right, operator);

        switch (operation) {

            case ">":
                result.setValue(leftValue > rightValue);
                break;

            case ">=":
                result.setValue(leftValue >= rightValue);
                break;

            case "<":
                result.setValue(leftValue < rightValue);
                break;

            case "<=":
                result.setValue(leftValue <= rightValue);
                break;

            case "!=":
                result.setValue(leftValue != rightValue);
                break;

            case "==":
                result.setValue(leftValue == rightValue);
                break;

            default:
                result.setValue(null);
        }
        result.setType(cmmBoolean);
        return result;
    }





    private ExprValue calc(Operation operation, Token operator, ExprValue left, ExprValue right) {

        ExprValue result;

        //type checking
        if (bothInteger(left, right)) {

            result = calcBothInt(operation, operator, left, right);
            result.setType(cmmInt);

        } else if (leftInteger(left, right)) {

            result = calcLeftInt(operation, operator, left, right);
            result.setType(cmmDouble);

        } else if (rightInteger(left, right)) {

            result = calcRightInt(operation, operator, left, right);
            result.setType(cmmDouble);


        } else if (bothDouble(left, right)) {

            result = calcBothDouble(operation, operator, left, right);
            result.setType(cmmDouble);

        } else {

            result = new ExprValue();
            result.setValue(null);
            PrintError.printIllegalType(io, operator);
        }
        return result;
    }



    private ExprValue compute(Operation operation, double leftValue, double rightValue, Token operator) {

        ExprValue result = new ExprValue();

        switch (operation) {
            case add:
                result.setValue(DecimalCalc.add(leftValue, rightValue));
                break;
            case subtract:
                result.setValue(DecimalCalc.sub(leftValue, rightValue));
                break;
            case multiply:
                result.setValue(DecimalCalc.mul(leftValue, rightValue));
                break;
            case divide:
                if (rightValue == 0) {
                    PrintError.printDivideByZero(io, operator);
                    result.setValue(null);
                } else {
                    result.setValue(DecimalCalc.div(leftValue, rightValue));
                }
                break;
            case module:
                if (rightValue == 0) {
                    PrintError.printDivideByZero(io, operator);
                    result.setValue(null);
                } else {
                    result.setValue(leftValue % rightValue);
                }
                break;
            default:
                result.setValue(null);
                PrintError.printIllegalOperation(io, operator);
        }
        return result;
    }




    private boolean bothInteger(ExprValue left, ExprValue right) {
        return left.getType() == cmmInt && right.getType() == cmmInt;
    }

    private boolean leftInteger(ExprValue left, ExprValue right) {
        return left.getType() == cmmInt && right.getType() == cmmDouble;
    }

    private boolean rightInteger(ExprValue left, ExprValue right) {
        return right.getType() == cmmInt && left.getType() == cmmDouble;
    }

    private boolean bothDouble(ExprValue left, ExprValue right) {
        return left.getType() == cmmDouble && right.getType() == cmmDouble;
    }




    private ExprValue calcBothInt(Operation operation, Token operator, ExprValue left, ExprValue right) {

        int leftValue = (Integer)left.getValue();
        int rightValue = (Integer)right.getValue();
        ExprValue result = new ExprValue();

        switch (operation) {
            case add:
                result.setValue(leftValue + rightValue);
                break;
            case subtract:
                result.setValue(leftValue - rightValue);
                break;
            case multiply:
                result.setValue(leftValue * rightValue);
                break;
            case divide:
                if (rightValue == 0) {
                    PrintError.printDivideByZero(io, operator);
                    result.setValue(null);
                } else {
                    result.setValue(leftValue / rightValue);
                }
                break;
            case module:
                if (rightValue == 0) {
                    PrintError.printDivideByZero(io, operator);
                    result.setValue(null);
                } else {
                    result.setValue(leftValue % rightValue);
                }
                break;
            default:
                result.setValue(null);
                PrintError.printIllegalOperation(io, operator);
        }
        return result;
    }

    private ExprValue calcLeftInt(Operation operation, Token operator, ExprValue left, ExprValue right) {

        double leftValue = (Integer)left.getValue();
        double rightValue = (Double)right.getValue();
        ExprValue result = compute(operation, leftValue, rightValue, operator);
        return result;
    }

    private ExprValue calcRightInt(Operation operation, Token operator, ExprValue left, ExprValue right) {

        double leftValue = (Double)left.getValue();
        double rightValue = (Integer)right.getValue();
        ExprValue result = compute(operation, leftValue, rightValue, operator);
        return result;
    }

    private ExprValue calcBothDouble(Operation operation, Token operator, ExprValue left, ExprValue right) {

        double leftValue = (Double)left.getValue();
        double rightValue = (Double)right.getValue();
        ExprValue result = compute(operation, leftValue, rightValue, operator);
        return result;
    }




    private ExprValue getVariableValue(TerminalNode id) {

        ExprValue result = new ExprValue();
        Token variable = id.getSymbol();
        String varName = variable.getText();
        Symbol varSymbol = currentScope.resolve(varName);
        boolean variableExist = (varSymbol != null);

        if (variableExist) {

            result.setType(varSymbol.getType());
            result.setValue(varSymbol.getValue());

        } else {

            PrintError.printCannotResloveSymbol(io, variable);
            result.setValue(null);
        }
        return result;
    }

    private ExprValue getArrayElementValue(CmmParser.ArrayContext arrayContext) {

        ExprValue result = new ExprValue();
        Token arr = arrayContext.ID().getSymbol();
        String arrName = arr.getText();
        int index = calcIndex(arrayContext, arr);

        if (index == -1) {
            return null;
        }

        Symbol varSymbol = currentScope.resolve(arrName);
        boolean arrayExist = (varSymbol != null);

        if (arrayExist) {

            boolean isIntArray = (varSymbol.getType() == cmmIntArray);
            boolean isDoubleArray = (varSymbol.getType() == cmmDoubleArray);

            if (isIntArray) {

                int[] varArray = (int[]) varSymbol.getValue();
                boolean indexOutOfBound = (index > varArray.length);
                result = getIntValue(indexOutOfBound, arr, varArray, index);

            } else if (isDoubleArray) {

                double[] varArray = (double[]) varSymbol.getValue();
                boolean indexOutOfBound = (index > varArray.length);
                result = getDoubleValue(indexOutOfBound, arr, varArray, index);

            } else {
                PrintError.printNotArray(io, arr);
            }

        } else {
            PrintError.printCannotResloveSymbol(io, arr);
        }
        return result;
    }




    private int calcIndex(CmmParser.ArrayContext arrayContext, Token array) {

        int index;
        boolean indexIsConstant = (arrayContext.INTCONST() != null);
        boolean indexIsExpression = (arrayContext.expr() != null);

        if (indexIsConstant) {

            index = Integer.parseInt(arrayContext.INTCONST().getText());

        } else if (indexIsExpression) {

            ExprCalcVisitor indexCalcVisitor = new ExprCalcVisitor(currentScope, io);
            ExprValue indexValue = indexCalcVisitor.visit(arrayContext.expr());
            boolean indexNotInt = (indexValue.getType() != cmmInt);

            if (indexNotInt) {
                PrintError.printIndexNotInt(io, array);
                index = -1;
            } else {
                index = (Integer) indexValue.getValue();
            }

        } else {

            index = -1;
        }

        return index;
    }




    private ExprValue getIntValue(boolean outOfBound, Token arr, int[] varArray, int index) {

        ExprValue result = new ExprValue();

        if (outOfBound) {
            result.setValue(null);
            PrintError.printIndexOutOfBound(io, arr);
            return null;
        }

        result.setType(cmmInt);
        result.setValue(varArray[index]);

        return result;
    }

    private ExprValue getDoubleValue(boolean outOfBound, Token arr, double[] varArray, int index) {

        ExprValue result = new ExprValue();

        if (outOfBound) {
            result.setValue(null);
            PrintError.printIndexOutOfBound(io, arr);
            return null;
        }

        result.setType(cmmDouble);
        result.setValue(varArray[index]);

        return result;
    }

}
