package compiler;

public enum TokenType {
    KEYWORD,        // "int", "class", "if", "return", etc.
    IDENTIFIER,     // Variable names: x, myVar
    OPERATOR,       // +, -, *, /, =
    INTEGER_LITERAL,// Numbers like 5, 100
    STRING_LITERAL, // Strings like "hello"
    SYMBOL,         // Punctuation: { } ( ) ;
    EOF             // End of file
}