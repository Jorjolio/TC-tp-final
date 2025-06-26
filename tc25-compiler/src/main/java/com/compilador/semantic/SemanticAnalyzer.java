package com.compilador.semantic;

import com.compilador.ast.*;
import java.util.*;

public class SemanticAnalyzer implements ASTVisitor {
    private SymbolTable symbolTable;
    private List<String> errors;
    private List<String> warnings;

    public SemanticAnalyzer() {
        this.symbolTable = new SymbolTable();
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    public boolean analyze(ProgramNode program) {
        errors.clear();
        warnings.clear();

        if (program != null) {
            program.accept(this);
        }

        return errors.isEmpty();
    }

    @Override
    public void visit(ProgramNode node) {
        node.getStatement().accept(this);
    }

    @Override
    public void visit(IfStatementNode node) {
        node.getCondition().accept(this);
        node.getThenStatement().accept(this);
        node.getElseStatement().accept(this);
    }

    @Override
    public void visit(AssignmentNode node) {
        // Declarar la variable si no existe
        if (!symbolTable.exists(node.getIdentifier())) {
            symbolTable.declare(node.getIdentifier(), "int"); // Tipo por defecto
        }

        node.getExpression().accept(this);
    }

    @Override
    public void visit(BinaryExpressionNode node) {
        node.getLeft().accept(this);
        node.getRight().accept(this);

        // Verificar compatibilidad de tipos para operadores relacionales
        String op = node.getOperator();
        if (isRelationalOperator(op)) {
            // En un compilador completo, aquí verificaríamos tipos
            // Por simplicidad, asumimos que son compatibles
        }
    }

    @Override
    public void visit(IdentifierNode node) {
        if (!symbolTable.exists(node.getName())) {
            errors.add("Variable no declarada: " + node.getName());
        }
    }

    @Override
    public void visit(NumberNode node) {
        // Los números son válidos por sí mismos
    }

    @Override
    public void visit(BooleanNode node) {
        // Los booleanos son válidos por sí mismos
    }

    private boolean isRelationalOperator(String op) {
        return op.equals("=") || op.equals("<>") || op.equals("<") ||
                op.equals("<=") || op.equals(">") || op.equals(">=");
    }

    public List<String> getErrors() {
        return errors;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }
}