package Compilation;

import Toggle.Toggle;
import Expression.ExprCalcVisitor;
import Expression.ExprValue;
import Symbol.Symbol;
import antlr.CmmBaseVisitor;
import IO.IO;
import antlr.CmmParser;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.Token;
import Scope.Scope;
import Scope.GlobalScope;
import IO.PrintError;
import Type.Type;

import static Type.Type.*;

/**
 * Created by marco on 18/12/2016.
 */
public class ReferVisitor extends CmmBaseVisitor<ExprValue> {

    private IO io;

    private ParseTreeProperty<Scope> scopes;
    private GlobalScope globalScope;
    private Scope currentScope;

    boolean willContinue = true;



    @Override
    public ExprValue visitProgram(CmmParser.ProgramContext ctx) {

        currentScope = globalScope;
        super.visitProgram(ctx);
        return null;
    }

    @Override
    public ExprValue visitStmtblock(CmmParser.StmtblockContext ctx) {

        currentScope = scopes.get(ctx);
        super.visitStmtblock(ctx);
        currentScope = currentScope.getEnclosingScope();
        return null;
    }

    @Override
    public ExprValue visitAssignstmt(CmmParser.AssignstmtContext ctx) {
        super.visitAssignstmt(ctx);

        boolean identifierIsVariable = (ctx.identifier().ID() != null);
        boolean identifierIsArray = (ctx.identifier().array() != null);

        if (identifierIsVariable) {
            assignExpressionToVariable(ctx);
        } else if (identifierIsArray) {
            assignExpressionToArrayElement(ctx);
        }
        return null;
    }

    @Override
    public ExprValue visitReadstmt(CmmParser.ReadstmtContext ctx) {
        super.visitReadstmt(ctx);

        boolean identifierIsVariable = (ctx.identifier().ID() != null);
        boolean identifierIsArray = (ctx.identifier().array() != null);

        if (identifierIsVariable) {
            assignInputToVariable(ctx);
        } else if (identifierIsArray) {
            assignInputToArrayElement(ctx);
        }
        return null;
    }

    @Override
    public ExprValue visitWritestmt(CmmParser.WritestmtContext ctx) {
        super.visitWritestmt(ctx);

        boolean constantIsString = (ctx.STRINGCONST() != null);
        if (constantIsString) {
            String original = ctx.STRINGCONST().getText();
            int length = original.length();
            io.output(original.substring(1,length-1));
            return null;
        }

        ExprCalcVisitor calcVisitor = new ExprCalcVisitor(currentScope, io);
        Object value = calcVisitor.visit(ctx.expr()).getValue();
        io.output(value);

        return null;
    }

    @Override
    public ExprValue visitWhilestmt(CmmParser.WhilestmtContext ctx) {

        Token whileToken = ctx.WHILE().getSymbol();
        int i = 0;

        willContinue = true;
        while (isConditionTrue(ctx.expr(), whileToken) && willContinue) {

            visit(ctx.stmt());
            if (Toggle.DEBUG) {
                io.output("DEBUG:  <while loop>    - loop " + i + ": true");
            }
            i++;
        }
        return null;
    }

    @Override
    public ExprValue visitBreakstmt(CmmParser.BreakstmtContext ctx) {

        willContinue = false;
        return super.visitBreakstmt(ctx);
    }

    @Override
    public ExprValue visitIfstmt(CmmParser.IfstmtContext ctx) {

        Token ifToken = ctx.IF().getSymbol();
        boolean hasElseBranch = (ctx.ELSE() != null);

        if (isConditionTrue(ctx.expr(), ifToken)) {
            visit(ctx.stmt(0));
        } else if (hasElseBranch) {
            visit(ctx.stmt(1));
        }

        return null;
    }





    public ReferVisitor(IO io, ParseTreeProperty<Scope> scopes, GlobalScope globalScope) {

        this.io = io;
        this.scopes = scopes;
        this.globalScope = globalScope;
    }



    private boolean isConditionTrue(CmmParser.ExprContext exprContext, Token token) {

        boolean isTrue;
        ExprCalcVisitor calcVisitor = new ExprCalcVisitor(currentScope, io);
        ExprValue exprValue = calcVisitor.visit(exprContext);
        boolean conditionIsBool = (exprValue.getType() == cmmBoolean);

        if (conditionIsBool) {
            isTrue =  (Boolean)exprValue.getValue();
        } else {
            isTrue = (Boolean)((Double)exprValue.getValue() > 0.0);
        }
        return isTrue;
    }



    private void assignExpressionToVariable(CmmParser.AssignstmtContext assignstmtContext) {

        Token variable = assignstmtContext.identifier().ID().getSymbol();
        String varName = variable.getText();
        Symbol varSymbol = currentScope.resolve(varName);
        boolean variableExist = (varSymbol != null);

        if (variableExist) {

            Token assignment = assignstmtContext.ASSIGNMENT().getSymbol();
            ExprCalcVisitor calcVisitor = new ExprCalcVisitor(currentScope, io);
            ExprValue exprValue = calcVisitor.visit(assignstmtContext.expr());

            if (typeMatched(varSymbol, exprValue)) {
                varSymbol.setValue(exprValue.getValue());
                if (Toggle.DEBUG) {
                    io.output("DEBUG:  <assignment>    - " + varName + " = " + exprValue.getValue());
                }
            } else {
                PrintError.printUnmatchType(io, assignment);
            }

        } else {

            PrintError.printCannotResloveSymbol(io, variable);
        }
    }

    private void assignExpressionToArrayElement(CmmParser.AssignstmtContext assignstmtContext) {

        Token array = assignstmtContext.identifier().array().ID().getSymbol();
        String arrName = array.getText();
        Symbol arrSymbol = currentScope.resolve(arrName);
        boolean arrayExist = (arrSymbol != null);

        if (arrayExist) {

            ExprCalcVisitor calcVisitor = new ExprCalcVisitor(currentScope, io);
            ExprValue exprValue = calcVisitor.visit(assignstmtContext.expr());
            CmmParser.ArrayContext arrayContext = assignstmtContext.identifier().array();

            int index = calcIndex(arrayContext);
            if (index == -1) {
                return;
            }

            boolean isIntArray = (arrSymbol.getType() == cmmIntArray);
            boolean isDoubleArray = (arrSymbol.getType() == cmmDoubleArray);

            if (isIntArray) {
                assignIntValue(arrSymbol, index, array, exprValue);
            } else if (isDoubleArray) {
                assignDoubleValue(arrSymbol, index, array, exprValue);
            } else {
                PrintError.printNotArray(io, array);
            }

        } else {

            PrintError.printCannotResloveSymbol(io, array);
        }
    }

    private void assignInputToVariable(CmmParser.ReadstmtContext readstmtContext) {

        Token variable = readstmtContext.identifier().ID().getSymbol();
        String varName = variable.getText();
        Symbol varSymbol = currentScope.resolve(varName);
        boolean variableExist = (varSymbol != null);

        if (variableExist) {

            boolean variableIsInt = (varSymbol.getType() == cmmInt);
            boolean variableIsDouble = (varSymbol.getType() == cmmDouble);

            if (variableIsInt) {
                int input = Integer.parseInt(io.input());
                varSymbol.setValue(input);
                if (Toggle.DEBUG) {
                    io.output("DEBUG:  <read>          - " + varName + " = " + input);
                }
            } else if (variableIsDouble) {
                double input = Double.parseDouble(io.input());
                varSymbol.setValue(input);
                if (Toggle.DEBUG) {
                    io.output("DEBUG:  <read>          - " + varName + " = " + input);
                }
            } else {
                varSymbol.setValue(null);
            }

        } else {

            PrintError.printCannotResloveSymbol(io, variable);
        }
    }

    private void assignInputToArrayElement(CmmParser.ReadstmtContext readstmtContext) {

        Token array = readstmtContext.identifier().array().ID().getSymbol();
        String arrName = array.getText();
        Symbol arrSymbol = currentScope.resolve(arrName);
        boolean arrayExist = (arrSymbol != null);

        if (arrayExist) {

            CmmParser.ArrayContext arrayContext = readstmtContext.identifier().array();
            int index = calcIndex(arrayContext);
            if (index == -1) {
                return;
            }
            boolean isIntArray = (arrSymbol.getType() == cmmIntArray);
            boolean isDoubleArray = (arrSymbol.getType() == cmmDoubleArray);

            if (isIntArray) {
                int[] varArray = (int[])arrSymbol.getValue();
                boolean indexOutOfBound = (index < 0 || index >= varArray.length);
                if (indexOutOfBound) {
                    PrintError.printIndexOutOfBound(io, array);
                    return;
                } else {
                    int input = Integer.parseInt(io.input());
                    varArray[index] = input;
                    if (Toggle.DEBUG) {
                        io.output("DEBUG:  <read>          - " + array.getText() + "[" + index + "] = " + input);
                    }
                }
            } else if (isDoubleArray) {
                double[] varArray = (double[])arrSymbol.getValue();
                boolean indexOutOfBound = (index < 0 || index >= varArray.length);
                if (indexOutOfBound) {
                    PrintError.printIndexOutOfBound(io, array);
                    return;
                } else {
                    double input = Double.parseDouble(io.input());
                    varArray[index] = input;
                    if (Toggle.DEBUG) {
                        io.output("DEBUG:  <read>          - " + array.getText() + "[" + index + "] = " + input);
                    }
                }
            } else {
                PrintError.printNotArray(io, array);
            }

        } else {

            PrintError.printCannotResloveSymbol(io, array);
        }
    }



    private int calcIndex(CmmParser.ArrayContext arrayContext) {

        int index;
        ExprValue indexValue;
        boolean indexIsConstant = (arrayContext.INTCONST() != null);
        boolean indexIsExpression = (arrayContext.expr() != null);

        if (indexIsConstant) {

            index = Integer.parseInt(arrayContext.INTCONST().getText());

        } else if (indexIsExpression) {

            Token array = arrayContext.ID().getSymbol();
            ExprCalcVisitor indexCalcVisitor = new ExprCalcVisitor(currentScope, io);
            indexValue = indexCalcVisitor.visit(arrayContext.expr());

            boolean isIndexInteger = (indexValue.getType() == cmmInt);
            if (isIndexInteger) {
                index = (Integer)indexValue.getValue();
            } else {
                PrintError.printIndexNotInt(io, array);
                index = -1;
            }

        } else {

            index = -1;
        }
        return index;
    }

    private boolean typeMatched(Symbol variableSymbol, ExprValue expressionValue) {

        Type leftType = variableSymbol.getType();
        Type rightType = expressionValue.getType();

        if (leftType == cmmInt && rightType == cmmInt)
            return true;
        else if (leftType == cmmDouble && rightType == cmmDouble)
            return true;
        else if (leftType == cmmDouble && rightType == cmmInt)
            return true;
        else
            return false;
    }


    private void assignIntValue(Symbol arrSymbol, int index, Token array, ExprValue exprValue) {

        int[] varArray = (int[])arrSymbol.getValue();
        boolean indexOutOfBound = (index < 0 || index >= varArray.length);

        if (!indexOutOfBound) {

            boolean typeMatch = (exprValue.getValue() instanceof Integer);

            if (typeMatch) {

                varArray[index] = (Integer)exprValue.getValue();
                if (Toggle.DEBUG) {
                    io.output("DEBUG:  <assignment>    - " + array.getText() + "[" +
                            index + "] = " + exprValue.getValue());
                }
            } else {
                PrintError.printUnmatchType(io, array);
            }

        } else {

            PrintError.printIndexOutOfBound(io, array);
        }
    }

    private void assignDoubleValue(Symbol arrSymbol, int index, Token array, ExprValue exprValue) {

        double[] varArray = (double[])arrSymbol.getValue();
        boolean indexOutOfBound = (index < 0 || index >= varArray.length);

        if (!indexOutOfBound) {

            boolean typeMatch = (exprValue.getValue() instanceof Double);
            boolean needCastType = (exprValue.getValue() instanceof Integer);

            if (typeMatch) {

                varArray[index] = (Double)exprValue.getValue();
                if (Toggle.DEBUG) {
                    io.output("DEBUG:  <assignment>    - " + array.getText() + "[" +
                            index + "] = " + exprValue.getValue());
                }
            } else if (needCastType) {

                int value = (Integer)exprValue.getValue();
                varArray[index] = (double)value;
                if (Toggle.DEBUG) {
                    io.output("DEBUG:  <assignment>    - " + array.getText() + "[" +
                            index + "] = " + exprValue.getValue());
                }
            } else {
                PrintError.printUnmatchType(io, array);
            }

        } else {

            PrintError.printIndexOutOfBound(io, array);
        }
    }

}
