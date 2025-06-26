package com.compilador.parser;

import com.compilador.ast.*;
import com.compilador.grammar.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.*;

public class SyntacticAnalyzer extends TC25GrammarBaseVisitor<ASTNode> {
    private List<String> errors;

    public SyntacticAnalyzer() {
        this.errors = new ArrayList<>();
    }

    public ProgramNode parse(String input) {
        errors.clear();

        try {
            CharStream inputStream = CharStreams.fromString(input);
            TC25GrammarLexer lexer = new TC25GrammarLexer(inputStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            TC25GrammarParser parser = new TC25GrammarParser(tokens);

            // Agregar listener para errores
            parser.removeErrorListeners();
            parser.addErrorListener(new SyntaxErrorListener());

            ParseTree tree = parser.program();

            if (errors.isEmpty()) {
                return (ProgramNode) visit(tree);
            }

        } catch (Exception e) {
            errors.add("Error en análisis sintáctico: " + e.getMessage());
        }

        return null;
    }

    @Override
    public ASTNode visitProgram(TC25GrammarParser.ProgramContext ctx) {
        StatementNode stmt = (StatementNode) visit(ctx.statement());
        return new ProgramNode(stmt);
    }

    @Override
    public ASTNode visitIfStatement(TC25GrammarParser.IfStatementContext ctx) {
        ExpressionNode condition = (ExpressionNode) visit(ctx.condition());
        StatementNode thenStmt = (StatementNode) visit(ctx.statement(0));
        StatementNode elseStmt = (StatementNode) visit(ctx.statement(1));
        return new IfStatementNode(condition, thenStmt, elseStmt);
    }

    @Override
    public ASTNode visitAssignmentStatement(TC25GrammarParser.AssignmentStatementContext ctx) {
        return visit(ctx.assignment());
    }

    @Override
    public ASTNode visitAssignment(TC25GrammarParser.AssignmentContext ctx) {
        String id = ctx.ID().getText();
        ExpressionNode expr = (ExpressionNode) visit(ctx.expression());
        return new AssignmentNode(id, expr);
    }

    @Override
    public ASTNode visitRelationalCondition(TC25GrammarParser.RelationalConditionContext ctx) {
        ExpressionNode left = (ExpressionNode) visit(ctx.expression(0));
        String op = ctx.relop().getText();
        ExpressionNode right = (ExpressionNode) visit(ctx.expression(1));
        return new BinaryExpressionNode(left, op, right);
    }

    @Override
    public ASTNode visitTrueCondition(TC25GrammarParser.TrueConditionContext ctx) {
        return new BooleanNode(true);
    }

    @Override
    public ASTNode visitFalseCondition(TC25GrammarParser.FalseConditionContext ctx) {
        return new BooleanNode(false);
    }

    @Override
    public ASTNode visitAddExpression(TC25GrammarParser.AddExpressionContext ctx) {
        ExpressionNode left = (ExpressionNode) visit(ctx.expression());
        ExpressionNode right = (ExpressionNode) visit(ctx.term());
        return new BinaryExpressionNode(left, "+", right);
    }

    @Override
    public ASTNode visitTermExpression(TC25GrammarParser.TermExpressionContext ctx) {
        return visit(ctx.term());
    }

    @Override
    public ASTNode visitFactorTerm(TC25GrammarParser.FactorTermContext ctx) {
        return visit(ctx.factor());
    }

    @Override
    public ASTNode visitMultTerm(TC25GrammarParser.MultTermContext ctx) {
        ExpressionNode left = (ExpressionNode) visit(ctx.term());
        ExpressionNode right = (ExpressionNode) visit(ctx.factor());
        return new BinaryExpressionNode(left, "*", right);
    }

    @Override
    public ASTNode visitIdFactor(TC25GrammarParser.IdFactorContext ctx) {
        return new IdentifierNode(ctx.ID().getText());
    }

    @Override
    public ASTNode visitNumFactor(TC25GrammarParser.NumFactorContext ctx) {
        return new NumberNode(Integer.parseInt(ctx.NUM().getText()));
    }

    @Override
    public ASTNode visitParenFactor(TC25GrammarParser.ParenFactorContext ctx) {
        return visit(ctx.expression());
    }

    public List<String> getErrors() {
        return errors;
    }

    private class SyntaxErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                int line, int charPositionInLine, String msg,
                                RecognitionException e) {
            errors.add(String.format("Error sintáctico en línea %d:%d - %s",
                    line, charPositionInLine, msg));
        }
    }
}