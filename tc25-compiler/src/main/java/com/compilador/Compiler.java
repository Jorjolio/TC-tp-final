package com.compilador;

import com.compilador.ast.*;
import com.compilador.lexer.*;
import com.compilador.parser.*;
import com.compilador.semantic.*;
import com.compilador.util.*;
import java.util.*;

public class Compiler {
    public static void main(String[] args) {
        // Entrada de prueba según la gramática
        String input = "if id < id + num then id := num else id := num";

        System.out.println("=== COMPILADOR TC25 ===");
        System.out.println("Entrada: " + input);

        // Fase 1: Análisis Léxico
        System.out.println("\n" + ColorUtil.BLUE + "=== FASE 1: ANÁLISIS LÉXICO ===" + ColorUtil.RESET);
        LexicalAnalyzer lexer = new LexicalAnalyzer();
        List<Token> tokens = lexer.analyze(input);

        if (!lexer.getErrors().isEmpty()) {
            System.out.println(ColorUtil.RED + "ERRORES LÉXICOS:" + ColorUtil.RESET);
            for (String error : lexer.getErrors()) {
                System.out.println(ColorUtil.RED + "❌ " + error + ColorUtil.RESET);
            }
            return;
        }

        System.out.println(ColorUtil.GREEN + "✅ Análisis léxico completado exitosamente" + ColorUtil.RESET);
        lexer.printTokenTable();

        // Fase 2: Análisis Sintáctico
        System.out.println("\n" + ColorUtil.BLUE + "=== FASE 2: ANÁLISIS SINTÁCTICO ===" + ColorUtil.RESET);
        SyntacticAnalyzer parser = new SyntacticAnalyzer();
        ProgramNode ast = parser.parse(input);

        if (!parser.getErrors().isEmpty()) {
            System.out.println(ColorUtil.RED + "ERRORES SINTÁCTICOS:" + ColorUtil.RESET);
            for (String error : parser.getErrors()) {
                System.out.println(ColorUtil.RED + "❌ " + error + ColorUtil.RESET);
            }
            return;
        }

        System.out.println(ColorUtil.GREEN + "✅ Análisis sintáctico completado exitosamente" + ColorUtil.RESET);
        System.out.println("\nÁRBOL SINTÁCTICO ABSTRACTO (AST):");
        ASTVisualizer.printAST(ast);

        // Fase 3: Análisis Semántico
        System.out.println("\n" + ColorUtil.BLUE + "=== FASE 3: ANÁLISIS SEMÁNTICO ===" + ColorUtil.RESET);
        SemanticAnalyzer semantic = new SemanticAnalyzer();
        boolean semanticSuccess = semantic.analyze(ast);

        if (!semantic.getErrors().isEmpty()) {
            System.out.println(ColorUtil.RED + "ERRORES SEMÁNTICOS:" + ColorUtil.RESET);
            for (String error : semantic.getErrors()) {
                System.out.println(ColorUtil.RED + "❌ " + error + ColorUtil.RESET);
            }
        }

        if (!semantic.getWarnings().isEmpty()) {
            System.out.println(ColorUtil.YELLOW + "ADVERTENCIAS SEMÁNTICAS:" + ColorUtil.RESET);
            for (String warning : semantic.getWarnings()) {
                System.out.println(ColorUtil.YELLOW + "⚠️ " + warning + ColorUtil.RESET);
            }
        }

        if (semanticSuccess) {
            System.out.println(ColorUtil.GREEN + "✅ Análisis semántico completado exitosamente" + ColorUtil.RESET);
        }

        semantic.getSymbolTable().printTable();

        // Resumen final
        System.out.println("\n" + ColorUtil.BLUE + "=== RESUMEN DE COMPILACIÓN ===" + ColorUtil.RESET);
        if (semanticSuccess) {
            System.out.println(ColorUtil.GREEN + "🎉 Compilación exitosa! Todas las fases completadas." + ColorUtil.RESET);
        } else {
            System.out.println(ColorUtil.RED + "❌ Compilación falló en la fase semántica." + ColorUtil.RESET);
        }
    }
}