package com.compilador.ast;

public class ProgramNode extends ASTNode {
    private final StatementNode statement;

    public ProgramNode(StatementNode statement) {
        this.statement = statement;
    }

    public StatementNode getStatement() {
        return statement;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Program(" + statement + ")";
    }
}