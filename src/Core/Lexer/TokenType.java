package Core.Lexer;

public enum TokenType {
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    EQUALS,
    LEQUALS,

    WRITE,
    WRITELN,
    READ,

    TEXT,
    NUMBER,
    WORD,
    SEMICOLON,
    FUNCTION,

    RPAREN,
    LPAREN,
    LBRACK,
    RBRACK,

    IF,
    ELSE,

    FOR,
    WHILE,
    EOF, HEXNUMBER;
}
