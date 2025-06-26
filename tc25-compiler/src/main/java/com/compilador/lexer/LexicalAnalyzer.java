package com.compilador.lexer;

import com.compilador.grammar.TC25GrammarLexer;
import org.antlr.v4.runtime.*;
import java.util.*;

public class LexicalAnalyzer {
    private List<Token> tokens;
    private List<String> errors;

    public LexicalAnalyzer() {
        this.tokens = new ArrayList<>();
        this.errors = new ArrayList<>();
    }

    public List<Token> analyze(String input) {
        tokens.clear();
        errors.clear();

        try {
            CharStream inputStream = CharStreams.fromString(input);
            TC25GrammarLexer lexer = new TC25GrammarLexer(inputStream);

            // Agregar listener para errores
            lexer.removeErrorListeners();
            lexer.addErrorListener(new LexerErrorListener());

            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            tokenStream.fill();

            // Convertir tokens de ANTLR a nuestros tokens
            for (org.antlr.v4.runtime.Token antlrToken : tokenStream.getTokens()) {
                TokenType type = convertAntlrTokenType(antlrToken.getType(), antlrToken.getText());
                Token token = new Token(type, antlrToken.getText(),
                        antlrToken.getLine(), antlrToken.getCharPositionInLine());
                tokens.add(token);
            }

        } catch (Exception e) {
            errors.add("Error en análisis léxico: " + e.getMessage());
        }

        return tokens;
    }

    private TokenType convertAntlrTokenType(int antlrType, String text) {
        switch (antlrType) {
            case TC25GrammarLexer.ID: return TokenType.ID;
            case TC25GrammarLexer.NUM: return TokenType.NUM;
            case TC25GrammarLexer.EOF: return TokenType.EOF;
            default:
                // Mapear por texto para palabras clave y operadores
                switch (text) {
                    case "if": return TokenType.IF;
                    case "then": return TokenType.THEN;
                    case "else": return TokenType.ELSE;
                    case "true": return TokenType.TRUE;
                    case "false": return TokenType.FALSE;
                    case ":=": return TokenType.ASSIGN;
                    case "+": return TokenType.PLUS;
                    case "*": return TokenType.MULT;
                    case "=": return TokenType.EQUAL;
                    case "<>": return TokenType.NOT_EQUAL;
                    case "<": return TokenType.LESS;
                    case "<=": return TokenType.LESS_EQUAL;
                    case ">": return TokenType.GREATER;
                    case ">=": return TokenType.GREATER_EQUAL;
                    case "(": return TokenType.LPAREN;
                    case ")": return TokenType.RPAREN;
                    default: return TokenType.UNKNOWN;
                }
        }
    }

    public List<String> getErrors() {
        return errors;
    }

    public void printTokenTable() {
        System.out.println("\n=== TABLA DE TOKENS ===");
        System.out.printf("%-15s %-15s %-8s %-8s%n", "TIPO", "VALOR", "LÍNEA", "COLUMNA");
        System.out.println("--------------------------------------------------------");

        for (Token token : tokens) {
            System.out.printf("%-15s %-15s %-8d %-8d%n",
                    token.getType(),
                    token.getValue(),
                    token.getLine(),
                    token.getColumn());
        }
    }

    private class LexerErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                int line, int charPositionInLine, String msg,
                                RecognitionException e) {
            errors.add(String.format("Error léxico en línea %d:%d - %s",
                    line, charPositionInLine, msg));
        }
    }
}