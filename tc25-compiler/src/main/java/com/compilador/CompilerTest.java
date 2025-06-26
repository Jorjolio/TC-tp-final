package com.compilador;

import com.compilador.lexer.*;
import com.compilador.parser.*;
import com.compilador.semantic.*;
import com.compilador.ast.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class CompilerTest {

    @Test
    public void testLexicalAnalysisValid() {
        String input = "if id < id + num then id := num else id := num";
        LexicalAnalyzer lexer = new LexicalAnalyzer();
        List<Token> tokens = lexer.analyze(input);

        assertTrue("No debe haber errores léxicos", lexer.getErrors().isEmpty());
        assertTrue("Debe haber tokens", tokens.size() > 0);

        // Verificar algunos tokens específicos
        assertEquals("Primer token debe ser IF", TokenType.IF, tokens.get(0).getType());
        assertEquals("Segundo token debe ser ID", TokenType.ID, tokens.get(1).getType());
        assertEquals("Tercer token debe ser LESS", TokenType.LESS, tokens.get(2).getType());
    }

    @Test
    public void testSyntacticAnalysisValid() {
        String input = "if id < id + num then id := num else id := num";
        SyntacticAnalyzer parser = new SyntacticAnalyzer();
        ProgramNode ast = parser.parse(input);

        assertTrue("No debe haber errores sintácticos", parser.getErrors().isEmpty());
        assertNotNull("AST no debe ser null", ast);
        assertTrue("Statement debe ser IfStatement",
                ast.getStatement() instanceof IfStatementNode);
    }

    @Test
    public void testSemanticAnalysis() {
        String input = "if id < id + num then x := num else y := num";
        SyntacticAnalyzer parser = new SyntacticAnalyzer();
        ProgramNode ast = parser.parse(input);

        SemanticAnalyzer semantic = new SemanticAnalyzer();
        boolean result = semantic.analyze(ast);

        // Debe haber errores semánticos por variables no declaradas
        assertFalse("Debe haber errores semánticos", result);
        assertFalse("Debe haber errores", semantic.getErrors().isEmpty());
        assertNotNull("Tabla de símbolos no debe ser null", semantic.getSymbolTable());
    }

    @Test
    public void testInvalidSyntax() {
        String input = "if id < then id := num"; // Sintaxis incorrecta
        SyntacticAnalyzer parser = new SyntacticAnalyzer();
        ProgramNode ast = parser.parse(input);

        assertFalse("Debe haber errores sintácticos", parser.getErrors().isEmpty());
        assertNull("AST debe ser null", ast);
    }

    @Test
    public void testValidAssignment() {
        String input = "x := 5 + 3";
        SyntacticAnalyzer parser = new SyntacticAnalyzer();
        ProgramNode ast = parser.parse(input);

        assertTrue("No debe haber errores sintácticos", parser.getErrors().isEmpty());
        assertNotNull("AST no debe ser null", ast);
        assertTrue("Statement debe ser Assignment",
                ast.getStatement() instanceof AssignmentNode);
    }

    @Test
    public void testBooleanConditions() {
        String input = "if true then x := 1 else y := 2";
        SyntacticAnalyzer parser = new SyntacticAnalyzer();
        ProgramNode ast = parser.parse(input);

        assertTrue("No debe haber errores sintácticos", parser.getErrors().isEmpty());
        assertNotNull("AST no debe ser null", ast);

        IfStatementNode ifNode = (IfStatementNode) ast.getStatement();
        assertTrue("Condición debe ser BooleanNode",
                ifNode.getCondition() instanceof BooleanNode);
    }
}