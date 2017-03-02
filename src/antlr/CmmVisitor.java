// Generated from /Users/marco/Desktop/Desktop-Marcos-MacBook-Pro/IDEAProjects/cmm/src/Cmm.g4 by ANTLR 4.6
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CmmParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CmmVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CmmParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CmmParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(CmmParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#stmtblock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtblock(CmmParser.StmtblockContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#vardecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardecl(CmmParser.VardeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(CmmParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#varlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarlist(CmmParser.VarlistContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#initdecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitdecl(CmmParser.InitdeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#declasign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclasign(CmmParser.DeclasignContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#ifstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfstmt(CmmParser.IfstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#whilestmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhilestmt(CmmParser.WhilestmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#breakstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakstmt(CmmParser.BreakstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#readstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadstmt(CmmParser.ReadstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#writestmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWritestmt(CmmParser.WritestmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#assignstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignstmt(CmmParser.AssignstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(CmmParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(CmmParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(CmmParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenthsisExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthsisExpr(CmmParser.ParenthsisExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IDExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIDExpr(CmmParser.IDExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConstExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstExpr(CmmParser.ConstExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivModExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivModExpr(CmmParser.MulDivModExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CompareExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareExpr(CmmParser.CompareExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(CmmParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MinusExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinusExpr(CmmParser.MinusExprContext ctx);
}