# Compilador TC25

Compilador desarrollado para la materia Técnicas de Compilación 2025.

## Características

- Análisis Léxico con ANTLR4
- Análisis Sintáctico con construcción de AST
- Análisis Semántico con tabla de símbolos
- Detección de errores en todas las fases
- Visualización colorizada de resultados

## Compilar y Ejecutar

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.compilador.Compiler"