grammar Cmm;

//Parser
program   : stmt+;

stmt      : vardecl
          | ifstmt
          | whilestmt
          | breakstmt
          | assignstmt
          | readstmt
          | writestmt
          | stmtblock
          ;

stmtblock : CURLYBRACEL stmt+ CURLYBRACER;

vardecl   : type varlist ENDLINE;

type      : INT
          | DOUBLE
          ;

varlist   : initdecl (COMMA initdecl)*;

initdecl  : ID | array | declasign;

declasign : ID ASSIGNMENT expr;

ifstmt    : IF PARENTHESISL expr PARENTHESISR stmt ( ELSE stmt )?;

whilestmt : WHILE PARENTHESISL expr PARENTHESISR stmt;

breakstmt : BREAK ENDLINE;

readstmt  : READ PARENTHESISL identifier PARENTHESISR ENDLINE;

writestmt : WRITE PARENTHESISL (STRINGCONST | expr) PARENTHESISR ENDLINE;

assignstmt: identifier ASSIGNMENT expr ENDLINE;

identifier: array
          | ID
          ;

array     : ID SQUAREBRACKETL (INTCONST|expr) SQUAREBRACKETR;

constant  : INTCONST
          | DOUBLECONST
          | TRUE
          | FALSE
          ;

expr      : ADDSUB expr          #MinusExpr
          | expr MULDIVMOD expr #MulDivModExpr
          | expr ADDSUB expr    #AddSubExpr
          | expr COMPARE expr   #CompareExpr
          | PARENTHESISL expr PARENTHESISR  #ParenthsisExpr
          | constant            #ConstExpr
          | identifier          #IDExpr
          ;


//Lexer
IF         : 'if';
ELSE       : 'else';
WHILE      : 'while';
BREAK      : 'break';

INT        : 'int';
DOUBLE     : 'double';
TRUE       : 'true';
FALSE      : 'false';

READ       : 'read';
WRITE      : 'write';

PARENTHESISL   : '(';
PARENTHESISR   : ')';
SQUAREBRACKETL : '[';
SQUAREBRACKETR : ']';
CURLYBRACEL    : '{';
CURLYBRACER    : '}';

MULDIVMOD      : '*'|'/'|'%';
ADDSUB         : '+'|'-';
COMPARE        : '>'|'<'|'>='|'<='|'!='|'==';

COMMA      : ',';
ASSIGNMENT : '=';

ID         : [a-zA-Z][a-zA-Z0-9_]*;

INTCONST   : [1-9][0-9]*
           | '0'
           ;

DOUBLECONST: [1-9][0-9]*'.'[0-9]*
           | '0''.'[0-9]*;

STRINGCONST: '"'[a-zA-Z ,.?:*&^%$#@!~]+'"';

ENDLINE    : ';';

WS         : [ \r\n\t] -> skip;

LINECMNT   : '//' ~[\r\n]* -> skip;

MULTICMNT  : '/*' .*? '*/' -> skip;
