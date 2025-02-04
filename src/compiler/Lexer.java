package compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private String input;
    private int position = 0;
    private static final String[] KEYWORDS = {"int", "class", "return", "if", "else"};

    public Lexer(String input) {
        this.input = input;
    }

    private boolean isKeyword(String word) {
        for (String keyword : KEYWORDS) {
            if (keyword.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (position < input.length()) {
            char current = input.charAt(position);

            if (Character.isWhitespace(current)) {
                position++; // Skip whitespace
            } else if (Character.isLetter(current)) {
                tokens.add(lexWord());
            } else if (Character.isDigit(current)) {
                tokens.add(lexNumber());
            } else {
                tokens.add(lexSymbol());
            }
        }

        tokens.add(new Token(TokenType.EOF, "EOF"));
        return tokens;
    }

    private Token lexWord() {
        StringBuilder buffer = new StringBuilder();
        while (position < input.length() && Character.isLetterOrDigit(input.charAt(position))) {
            buffer.append(input.charAt(position++));
        }
        String word = buffer.toString();
        if (isKeyword(word)) {
            return new Token(TokenType.KEYWORD, word);
        } else {
            return new Token(TokenType.IDENTIFIER, word);
        }
    }

    private Token lexNumber() {
        StringBuilder buffer = new StringBuilder();
        while (position < input.length() && Character.isDigit(input.charAt(position))) {
            buffer.append(input.charAt(position++));
        }
        return new Token(TokenType.INTEGER_LITERAL, buffer.toString());
    }

    private Token lexSymbol() {
        char symbol = input.charAt(position++);
        switch (symbol) {
            case '=': return new Token(TokenType.OPERATOR, "=");
            case '+': return new Token(TokenType.OPERATOR, "+");
            case '-': return new Token(TokenType.OPERATOR, "-");
            case '*': return new Token(TokenType.OPERATOR, "*");
            case '/': return new Token(TokenType.OPERATOR, "/");
            case ';': return new Token(TokenType.SYMBOL, ";");
            case '{': return new Token(TokenType.SYMBOL, "{");
            case '}': return new Token(TokenType.SYMBOL, "}");
            default: throw new RuntimeException("Unexpected character: " + symbol);
        }
    }
}
