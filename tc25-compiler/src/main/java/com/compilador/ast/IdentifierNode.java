package com.compilador.ast;

public class IdentifierNode extends ExpressionNode {
    private final String name;

    public IdentifierNode(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Id(" + name + ")";
    }
}