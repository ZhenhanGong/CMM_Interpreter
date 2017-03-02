package Compilation;

import Toggle.Toggle;
import Type.Type;
import Expression.ExprCalcVisitor;
import Expression.ExprValue;
import Scope.GlobalScope;
import Scope.Scope;
import Scope.LocalScope;
import antlr.CmmBaseListener;
import IO.IO;
import antlr.CmmParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import Symbol.Symbol;
import IO.PrintError;

import java.util.List;

import static Type.Type.*;

/**
 * Created by marco on 15/12/2016.
 */
public class DeclareListener extends CmmBaseListener {

    private IO io;

    private GlobalScope globalScope;
    private Scope currentScope;
    private ParseTreeProperty<Scope> scopes;



    @Override
    public void enterProgram(CmmParser.ProgramContext ctx) {
        super.enterProgram(ctx);

        globalScope = new GlobalScope(null);
        currentScope = globalScope;
    }

    @Override
    public void exitProgram(CmmParser.ProgramContext ctx) {
        super.exitProgram(ctx);
    }

    @Override
    public void enterStmtblock(CmmParser.StmtblockContext ctx) {
        super.enterStmtblock(ctx);

        currentScope = new LocalScope(currentScope);
        saveScope(ctx, currentScope);
    }

    @Override
    public void exitStmtblock(CmmParser.StmtblockContext ctx) {
        super.exitStmtblock(ctx);
        currentScope = currentScope.getEnclosingScope();
    }

    @Override
    public void enterInitdecl(CmmParser.InitdeclContext ctx) {
        super.enterInitdecl(ctx);

        String varType = ctx.getParent().getParent().getChild(0).getText();

        variableDeclare(ctx, varType);

        if (ctx.array() != null) {
            CmmParser.ArrayContext arrayContext = ctx.array();
            arrayDeclare(arrayContext, varType);
        }

        if (ctx.declasign() != null) {
            CmmParser.DeclasignContext declasignContext = ctx.declasign();
            variableInitialize(declasignContext, varType);
        }

    }





    private void saveScope(ParserRuleContext context, Scope scope) {
        scopes.put(context, scope);
    }

    public DeclareListener(IO io) {

        this.io = io;
        scopes = new ParseTreeProperty<Scope>();
    }

    public GlobalScope getGlobalScope() {
        return globalScope;
    }

    public ParseTreeProperty<Scope> getScopes() {
        return scopes;
    }




    private void variableDeclare(CmmParser.InitdeclContext context, String type) {

        List<TerminalNode> allVariable = context.getTokens(CmmParser.ID);

        for (TerminalNode var : allVariable) {

            Token variable = var.getSymbol();
            String varName = variable.getText();
            boolean variableAlreadyExist = currentScope.isRedundant(varName);

            if (variableAlreadyExist) {

                PrintError.printConflictDeclare(io, variable);

            } else {

                Type varType = type.equals("int") ? cmmInt : cmmDouble;
                Symbol varSymbol = new Symbol(varName, varType);

                currentScope.declare(varSymbol);

                if (Toggle.DEBUG)
                    io.output("DEBUG:  <declaration>   - " + type + " " + varName);
            }
        }
    }

    private void arrayDeclare(CmmParser.ArrayContext arrayContext, String type) {

        Token array = arrayContext.ID().getSymbol();
        String arrName = array.getText();

        TerminalNode constant = arrayContext.INTCONST();
        boolean indexIsIntConstant = (constant != null);

        if (indexIsIntConstant) {

            int arraySize = Integer.parseInt(arrayContext.INTCONST().getText());
            boolean arrayAlreadyExist = currentScope.isRedundant(arrName);

            if (arrayAlreadyExist) {

                PrintError.printConflictDeclare(io, array);

            } else {

                boolean isInt = type.equals("int");
                Type varType = isInt ? cmmIntArray : cmmDoubleArray;
                Symbol varSymbol = new Symbol(arrName, varType, isInt ? new int[arraySize] : new double[arraySize]);

                currentScope.declare(varSymbol);

                if (Toggle.DEBUG)
                    io.output("DEBUG:  <declaration>   - " + type + " " + arrName
                            + "[" + arraySize + "]");
            }

        } else {

            PrintError.printIndexNotConstant(io, array);
        }
    }

    private void variableInitialize(CmmParser.DeclasignContext declasignContext, String type) {

        Token variable = declasignContext.ID().getSymbol();

        ExprCalcVisitor calcVisitor = new ExprCalcVisitor(currentScope, io);
        ExprValue initValue = calcVisitor.visit(declasignContext.expr());

        Type rightType = initValue.getType();
        Type leftType = type.equals("int") ? cmmInt : cmmDouble;

        if (!typeMatch(leftType, rightType)) {
            PrintError.printUnmatchType(io, variable);
            return;
        }

        String varName = variable.getText();
        boolean variableAlreadyExist = currentScope.isRedundant(varName);

        if (variableAlreadyExist) {

            PrintError.printConflictDeclare(io, variable);

         } else {

            Symbol varSymbol = new Symbol(varName, leftType, initValue.getValue());
            currentScope.declare(varSymbol);

            if (Toggle.DEBUG)
                io.output("DEBUG:  <declaration>   - " + type + " " + varName + " = " + initValue.getValue());
        }
    }



    private boolean typeMatch(Type leftType, Type rightType) {

        if (leftType == cmmInt && rightType == cmmInt)
            return true;
        else if (leftType == cmmDouble && rightType == cmmDouble)
            return true;
        else if (leftType == cmmDouble && rightType == cmmInt)
            return true;
        else
            return false;
    }

    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);

        io.output("ERROR:  error node " + node.getText()
                +" in line " + node.getSymbol().getLine()
                +":" +node.getSymbol().getCharPositionInLine());
    }

}
