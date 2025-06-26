package com.compilador.util;

import com.compilador.ast.*;

public class ASTVisualizer {
    public static void printAST(ASTNode node) {
        printAST(node, "", true);
    }

    private static void printAST(ASTNode node, String prefix, boolean isLast) {
        if (node == null) return;

        System.out.println(prefix + (isLast ? "└── " : "├── ") + getNodeDescription(node));

        String childPrefix = prefix + (isLast ? "    " : "│   ");

        if (node instanceof ProgramNode) {
            ProgramNode program = (ProgramNode) node;
            printAST(program.getStatement(), childPrefix, true);
        } else if (node instanceof IfStatementNode) {
            IfStatementNode ifNode = (IfStatementNode) node;
            System.out.println(childPrefix + "├── condition:");
            printAST(ifNode.getCondition(), childPrefix + "│   ", true);
            System.out.println(childPrefix + "├── then:");
            printAST(ifNode.getThenStatement(), childPrefix + "│   ", true);
            System.out.println(childPrefix + "└── else:");
            printAST(ifNode.getElseStatement(), childPrefix + "    ", true);
        } else if (node instanceof AssignmentNode) {
            AssignmentNode assign = (AssignmentNode) node;
            System.out.println(childPrefix + "├── variable: " + assign.getIdentifier());
            System.out.println(childPrefix + "└── expression:");
            printAST(assign.getExpression(), childPrefix + "    ", true);
        } else if (node instanceof BinaryExpressionNode) {
            BinaryExpressionNode binOp = (BinaryExpressionNode) node;
            System.out.println(childPrefix + "├── left:");
            printAST(binOp.getLeft(), childPrefix + "│   ", true);
            System.out.println(childPrefix + "├── operator: " + binOp.getOperator());
            System.out.println(childPrefix + "└── right:");
            printAST(binOp.getRight(), childPrefix + "    ", true);
        }
    }

    private static String getNodeDescription(ASTNode node) {
        if (node instanceof ProgramNode) {
            return "Program";
        } else if (node instanceof IfStatementNode) {
            return "If Statement";
        } else if (node instanceof AssignmentNode) {
            return "Assignment";
        } else if (node instanceof BinaryExpressionNode) {
            BinaryExpressionNode binOp = (BinaryExpressionNode) node;
            return "Binary Operation (" + binOp.getOperator() + ")";
        } else if (node instanceof IdentifierNode) {
            IdentifierNode id = (IdentifierNode) node;
            return "Identifier: " + id.getName();
        } else if (node instanceof NumberNode) {
            NumberNode num = (NumberNode) node;
            return "Number: " + num.getValue();
        } else if (node instanceof BooleanNode) {
            BooleanNode bool = (BooleanNode) node;
            return "Boolean: " + bool.getValue();
        }
        return node.getClass().getSimpleName();
    }
}