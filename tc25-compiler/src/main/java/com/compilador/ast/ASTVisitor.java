package com.compilador.ast;

public interface ASTVisitor {
    void visit(ProgramNode node);
    void visit(IfStatementNode node);
    void visit(AssignmentNode node);
    void visit(BinaryExpressionNode node);
    void visit(IdentifierNode node);
    void visit(NumberNode node);
    void visit(BooleanNode node);
}