grammar TC25Grammar;

// Reglas del parser (sintáctica)
program : statement EOF ;

statement
    : 'if' condition 'then' statement 'else' statement    # IfStatement
    | assignment                                           # AssignmentStatement
    ;

condition
    : expression relop expression                          # RelationalCondition
    | 'true'                                              # TrueCondition
    | 'false'                                             # FalseCondition
    ;

expression
    : expression '+' term                                  # AddExpression
    | term                                                # TermExpression
    ;

term
    : factor                                              # FactorTerm
    | term '*' factor                                     # MultTerm
    ;

factor
    : ID                                                  # IdFactor
    | NUM                                                 # NumFactor
    | '(' expression ')'                                  # ParenFactor
    ;

assignment
    : ID ':=' expression                                  # AssignmentAlt
    ;

relop
    : '='                                                 # EqualOp
    | '<>'                                                # NotEqualOp
    | '<'                                                 # LessOp
    | '<='                                                # LessEqualOp
    | '>'                                                 # GreaterOp
    | '>='                                                # GreaterEqualOp
    ;

// Tokens (léxico)
ID : [a-zA-Z][a-zA-Z0-9]* ;
NUM : [0-9]+ ;
WS : [ \t\r\n]+ -> skip ;