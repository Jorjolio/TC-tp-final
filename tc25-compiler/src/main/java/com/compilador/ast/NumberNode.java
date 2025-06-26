package com.compilador.ast;

public class NumberNode extends ExpressionNode {
    private final int value;

    public NumberNode(int value) {
        this.value = value;
    }

    public int getValue() { return value; }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Num(" + value + ")";
    }
}