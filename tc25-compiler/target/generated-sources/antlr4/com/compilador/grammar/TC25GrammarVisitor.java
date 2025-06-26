// Generated from com/compilador/grammar/TC25Grammar.g4 by ANTLR 4.13.1
package com.compilador.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TC25GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TC25GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TC25GrammarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(TC25GrammarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfStatement}
	 * labeled alternative in {@link TC25GrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(TC25GrammarParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignmentStatement}
	 * labeled alternative in {@link TC25GrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(TC25GrammarParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RelationalCondition}
	 * labeled alternative in {@link TC25GrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalCondition(TC25GrammarParser.RelationalConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TrueCondition}
	 * labeled alternative in {@link TC25GrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueCondition(TC25GrammarParser.TrueConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FalseCondition}
	 * labeled alternative in {@link TC25GrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseCondition(TC25GrammarParser.FalseConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TermExpression}
	 * labeled alternative in {@link TC25GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermExpression(TC25GrammarParser.TermExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddExpression}
	 * labeled alternative in {@link TC25GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpression(TC25GrammarParser.AddExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultTerm}
	 * labeled alternative in {@link TC25GrammarParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultTerm(TC25GrammarParser.MultTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FactorTerm}
	 * labeled alternative in {@link TC25GrammarParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorTerm(TC25GrammarParser.FactorTermContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdFactor}
	 * labeled alternative in {@link TC25GrammarParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdFactor(TC25GrammarParser.IdFactorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumFactor}
	 * labeled alternative in {@link TC25GrammarParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumFactor(TC25GrammarParser.NumFactorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenFactor}
	 * labeled alternative in {@link TC25GrammarParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenFactor(TC25GrammarParser.ParenFactorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignmentAlt}
	 * labeled alternative in {@link TC25GrammarParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentAlt(TC25GrammarParser.AssignmentAltContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualOp}
	 * labeled alternative in {@link TC25GrammarParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualOp(TC25GrammarParser.EqualOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotEqualOp}
	 * labeled alternative in {@link TC25GrammarParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotEqualOp(TC25GrammarParser.NotEqualOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessOp}
	 * labeled alternative in {@link TC25GrammarParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessOp(TC25GrammarParser.LessOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessEqualOp}
	 * labeled alternative in {@link TC25GrammarParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessEqualOp(TC25GrammarParser.LessEqualOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GreaterOp}
	 * labeled alternative in {@link TC25GrammarParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterOp(TC25GrammarParser.GreaterOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GreaterEqualOp}
	 * labeled alternative in {@link TC25GrammarParser#relop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGreaterEqualOp(TC25GrammarParser.GreaterEqualOpContext ctx);
}