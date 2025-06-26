package com.compilador.ast;

public abstract class ASTNode {
    public abstract void accept(ASTVisitor visitor);
    public abstract String toString();
}
