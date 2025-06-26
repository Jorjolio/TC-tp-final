package com.compilador.ast;

public class IfStatementNode extends StatementNode {
    private final ExpressionNode condition;
    private final StatementNode thenStatement;
    private final StatementNode elseStatement;

    public IfStatementNode(ExpressionNode condition, StatementNode thenStatement,
                           StatementNode elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public ExpressionNode getCondition() { return condition; }
    public StatementNode getThenStatement() { return thenStatement; }
    public StatementNode getElseStatement() { return elseStatement; }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("If(%s, %s, %s)", condition, thenStatement, elseStatement);
    }
}