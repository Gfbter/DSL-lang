package compile.core.Lexer.RegExp;

public enum LexemType {

    // 1 SYMBOL
    // PAREN
    LEFT_PAREN,      // '('
    RIGHT_PAREN,     // ')'
    LEFT_BRACE,      // '{'
    RIGHT_BRACE,     // '}'
    // .,;
    COMMA,          // ','
    //DOT,            // '.'
    SEMICOLON,      // ';'
    QUOTES,         // '"'
    SEPARATOR,      // '\t', ' ', '\n'
    EOF,            // END OF FILE

    // MATH
    MINUS,          // '-'
    PLUS,           // '+'
    DIVISION,          // '/'
    MULT,           // '*'
    MOD,            // '%'

    // 1(+1) SYMBOL
    ASSIGN,         // '='
    EQUAL,          // '=='
    NOT,            // '!'
    NOT_EQUAL,       // '!='
    GREATER,        // '>'
    GREATER_EQUAL,   // '>='
    LESS,           // '<'
    LESS_EQUAL,      // '<='

    //DEC,   // '--' DEC
    //INC,   // '++' INC

    // VALUE TYPES
    STRING,
    INT,
    VARIABLE,

    //TEXT
    DIGIT,
    TEXT, //fix text -> string

    // KEYWORDS
    //TRUE,
    //FALSE,
    NULL,
    AND,
    OR,
    IF,
    ELSE,
    FOR,
    WHILE,
    DO,
    FUNCTION,
    RETURN,
    IDENTIFIER,
    NATIVE_FUNC,

    // BUILTIN FUNCTION
    PRINT,
    //SIN,
    //COS,
    // ETC
}
