package com.RyanJeffares.lox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.RyanJeffares.lox.TokenType.*;

class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private static final Map<String, TokenType> keywords;
    private int start = 0;
    private int current = 0;
    private int line = 1;

    static {
        keywords = new HashMap<>();
        keywords.put("and",     AND);
        keywords.put("class",   CLASS);
        keywords.put("else",    ELSE);
        keywords.put("false",   FALSE);
        keywords.put("for",     FOR);
        keywords.put("fun",     FUN);
        keywords.put("if",      IF);
        keywords.put("nil",     NIL);
        keywords.put("or",      OR);
        keywords.put("print",   PRINT);
        keywords.put("return",  RETURN);
        keywords.put("super",   SUPER);
        keywords.put("this",    THIS);
        keywords.put("true",    TRUE);
        keywords.put("var",     VAR);
        keywords.put("while",   WHILE);
    }

    Scanner(String source) {
        this.source = source;
    }

    // scans all tokens until EOF and returns them as a list to Lox.main
    List<Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    // gets the next token and consumes it
    private void scanToken() {
        char c = advance();
        switch (c) {
            // single character tokens
            case '(': addToken(LEFT_PAREN); break;
            case ')': addToken(RIGHT_PAREN); break;
            case '{': addToken(LEFT_BRACE); break;
            case '}': addToken(RIGHT_BRACE); break;
            case ',': addToken(COMMA); break;
            case '.': addToken(DOT); break;
            case '-': addToken(MINUS); break;
            case '+': addToken(PLUS); break;
            case ';': addToken(SEMICOLON); break;
            case '*': addToken(STAR); break;
            // tokens that may be a single character or may have 2 characters
            case '!':
                addToken(match('=') ? BANG_EQUAL : BANG);
                break;
            case '=':
                addToken(match('=') ? EQUAL_EQUAL : EQUAL);
                break;
            case '<':
                addToken(match('=') ? LESS_EQUAL : LESS);
                break;
            case '>':
                addToken(match('=') ? GREATER_EQUAL : GREATER);
                break;
            // could be a comment or a divide
            case '/':
                if (match('/')) {
                    while (peek() != '\n' && !isAtEnd()) advance();
                } else if (match('*')) {
                    while (advance() != '*' && !isAtEnd()) {
                        if (peek() == '\n') line++;
                    }

                    if (isAtEnd() || peek() != '/') {
                        Lox.error(line, "Unterminated block comment.");
                        break;
                    }

                    advance();
                } else {
                    addToken(SLASH);
                }
                break;
            // ignore whitespace
            case ' ':
            case '\r':
            case '\t':
                break;
            // also ignore new lines but increment the line counter
            case '\n':
                line++;
                break;
            // handle strings
            case '"':
                string();
                break;
            // handle digit or invalid character
            default:
                if (isDigit(c)) {
                    number();
                } else if (isAlpha(c)) {
                    identifier();
                } else {
                    Lox.error(line, "Unexpected character.");
                }
                break;
        }
    }

    private void identifier() {
        while (isAlphanumeric(peek())) advance();

        var text = source.substring(start, current);
        var type = keywords.get(text);
        if (type == null) type = IDENTIFIER;
        addToken(type);
    }

    // all numbers are represented as a java double
    private void number() {
        while (isDigit(peek())) advance();

        if (peek() == '.' && isDigit(peekNext())) {
            advance();
            while (isDigit(peek())) advance();
        }

        addToken(NUMBER, Double.parseDouble(source.substring(start, current)));
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }

        if (isAtEnd()) {
            Lox.error(line, "Unterminated string.");
            return;
        }

        advance();

        var value = source.substring(start + 1, current - 1);
        addToken(STRING, value);
    }

    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;

        current++;
        return true;
    }

    // get the character without consuming it
    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    // get the next character without consuming it
    private char peekNext() {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    private boolean isAlphanumeric(char c) {
        return isDigit(c) || isAlpha(c);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private char advance() {
        return source.charAt(current++);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        var text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, current));
    }
}
