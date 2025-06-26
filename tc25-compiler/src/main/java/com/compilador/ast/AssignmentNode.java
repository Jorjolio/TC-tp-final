package com.compilador.ast;

public class AssignmentNode extends StatementNode {
    private final String identifier;
    private final ExpressionNode expression;

    public AssignmentNode(String identifier, ExpressionNode expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public String getIdentifier() { return identifier; }
    public ExpressionNode getExpression() { return expression; }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("Assign(%s, %s)", identifier, expression);
    }
}