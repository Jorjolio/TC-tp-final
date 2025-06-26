package com.compilador.semantic;

import java.util.*;

public class SymbolTable {
    private Map<String, Symbol> symbols;

    public SymbolTable() {
        this.symbols = new HashMap<>();
    }

    public void declare(String name, String type) {
        symbols.put(name, new Symbol(name, type));
    }

    public Symbol lookup(String name) {
        return symbols.get(name);
    }

    public boolean exists(String name) {
        return symbols.containsKey(name);
    }

    public void printTable() {
        System.out.println("\n=== TABLA DE SÍMBOLOS ===");
        System.out.printf("%-15s %-15s%n", "IDENTIFICADOR", "TIPO");
        System.out.println("--------------------------------");

        for (Symbol symbol : symbols.values()) {
            System.out.printf("%-15s %-15s%n", symbol.getName(), symbol.getType());
        }
    }

    public static class Symbol {
        private final String name;
        private final String type;

        public Symbol(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() { return name; }
        public String getType() { return type; }
    }
}