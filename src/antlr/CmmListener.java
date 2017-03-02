// Generated from /Users/marco/Desktop/Desktop-Marcos-MacBook-Pro/IDEAProjects/cmm/src/Cmm.g4 by ANTLR 4.6
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CmmParser}.
 */
public interface CmmListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CmmParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CmmParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CmmParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(CmmParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(CmmParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#stmtblock}.
	 * @param ctx the parse tree
	 */
	void enterStmtblock(CmmParser.StmtblockContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#stmtblock}.
	 * @param ctx the parse tree
	 */
	void exitStmtblock(CmmParser.StmtblockContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#vardecl}.
	 * @param ctx the parse tree
	 */
	void enterVardecl(CmmParser.VardeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#vardecl}.
	 * @param ctx the parse tree
	 */
	void exitVardecl(CmmParser.VardeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(CmmParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(CmmParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#varlist}.
	 * @param ctx the parse tree
	 */
	void enterVarlist(CmmParser.VarlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#varlist}.
	 * @param ctx the parse tree
	 */
	void exitVarlist(CmmParser.VarlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#initdecl}.
	 * @param ctx the parse tree
	 */
	void enterInitdecl(CmmParser.InitdeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#initdecl}.
	 * @param ctx the parse tree
	 */
	void exitInitdecl(CmmParser.InitdeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#declasign}.
	 * @param ctx the parse tree
	 */
	void enterDeclasign(CmmParser.DeclasignContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#declasign}.
	 * @param ctx the parse tree
	 */
	void exitDeclasign(CmmParser.DeclasignContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#ifstmt}.
	 * @param ctx the parse tree
	 */
	void enterIfstmt(CmmParser.IfstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#ifstmt}.
	 * @param ctx the parse tree
	 */
	void exitIfstmt(CmmParser.IfstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#whilestmt}.
	 * @param ctx the parse tree
	 */
	void enterWhilestmt(CmmParser.WhilestmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#whilestmt}.
	 * @param ctx the parse tree
	 */
	void exitWhilestmt(CmmParser.WhilestmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#breakstmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakstmt(CmmParser.BreakstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#breakstmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakstmt(CmmParser.BreakstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#readstmt}.
	 * @param ctx the parse tree
	 */
	void enterReadstmt(CmmParser.ReadstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#readstmt}.
	 * @param ctx the parse tree
	 */
	void exitReadstmt(CmmParser.ReadstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#writestmt}.
	 * @param ctx the parse tree
	 */
	void enterWritestmt(CmmParser.WritestmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#writestmt}.
	 * @param ctx the parse tree
	 */
	void exitWritestmt(CmmParser.WritestmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#assignstmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignstmt(CmmParser.AssignstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#assignstmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignstmt(CmmParser.AssignstmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(CmmParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(CmmParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(CmmParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(CmmParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(CmmParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(CmmParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenthsisExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenthsisExpr(CmmParser.ParenthsisExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenthsisExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenthsisExpr(CmmParser.ParenthsisExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IDExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIDExpr(CmmParser.IDExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IDExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIDExpr(CmmParser.IDExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstExpr(CmmParser.ConstExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstExpr(CmmParser.ConstExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivModExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivModExpr(CmmParser.MulDivModExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivModExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivModExpr(CmmParser.MulDivModExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CompareExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompareExpr(CmmParser.CompareExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CompareExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompareExpr(CmmParser.CompareExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(CmmParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(CmmParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MinusExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMinusExpr(CmmParser.MinusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MinusExpr}
	 * labeled alternative in {@link CmmParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMinusExpr(CmmParser.MinusExprContext ctx);
}