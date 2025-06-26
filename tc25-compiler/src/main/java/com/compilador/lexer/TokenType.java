package com.compilador.lexer;

public enum TokenType {
    // Palabras clave
    IF("if"),
    THEN("then"),
    ELSE("else"),
    TRUE("true"),
    FALSE("false"),

    // Operadores
    ASSIGN(":="),
    PLUS("+"),
    MULT("*"),
    EQUAL("="),
    NOT_EQUAL("<>"),
    LESS("<"),
    LESS_EQUAL("<="),
    GREATER(">"),
    GREATER_EQUAL(">="),

    // Delimitadores
    LPAREN("("),
    RPAREN(")"),

    // Literales
    ID("ID"),
    NUM("NUM"),

    // Especiales
    EOF("EOF"),
    UNKNOWN("UNKNOWN");

    private final String representation;

    TokenType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}