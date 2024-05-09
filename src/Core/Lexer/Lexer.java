package Core.Lexer;

import Core.Error;

import java.util.*;

public class Lexer {
    private final String input;
    private final int length;
    private int pos = 0;
    private char current;
    private static List<Token> result = new ArrayList<>();

    public Lexer(String input) {
        this.input = input;
        this.length = input.length();
    }

    public List<Token> lex(){
        if(pos >= length){ addToken(TokenType.EOF); return result; }
        current = input.charAt(pos);
        while(pos < length){
            if(Character.isLetter(current)){
                parseWord();
            } else if (Character.isDigit(current)){
                parseNumber();
            } else if (current == '+'){
                addToken(TokenType.PLUS);
            } else if (current == '-'){
                addToken(TokenType.MINUS);
            } else if (current == '*'){
                addToken(TokenType.MULTIPLY);
            } else if (current == '/'){
                addToken(TokenType.DIVIDE);
            } else if (peek(1) == '=' && current == '='){
                addToken(TokenType.LEQUALS);
                current = next();
            } else if (current == '='){
                addToken(TokenType.EQUALS);
            } else if (current == '"'){
                parseText();
            } else if (current == '('){
                addToken(TokenType.LPAREN);
            } else if (current == '#'){
                parseHexNumber();
            } else if (current == ')'){
                addToken(TokenType.RPAREN);
            } else if (current == '{'){
                addToken(TokenType.LBRACK);
            } else if (current == '}'){
                addToken(TokenType.RBRACK);
            } else if (current == '\\' && peek(1) == 'n'){
                current = next();
                continue;
            } else if (current == ' '){
                current = next();
                continue;
            } else {
                Error.ThrowError("Unexpected token: " + current);
            }
            current = next();
        }
        addToken(TokenType.EOF);
        return result;
    }

    private void parseHexNumber() {
        StringBuilder buffer = new StringBuilder();
        String[] RegEx = {"A", "B", "C", "D", "E", "F", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        while (pos < length) {
            current = next();
            if (Arrays.asList(RegEx).contains(String.valueOf(current))) {
                buffer.append(current);
            } else {
                addToken(TokenType.HEXNUMBER , buffer.toString());
                current = previous();
                return;
            }
        }
        addToken(TokenType.HEXNUMBER, buffer.toString());
    }

    private void parseText() {
        StringBuilder buffer = new StringBuilder();
        while (pos < length){
            current = next();
            if(current == '"'){
                addToken(TokenType.TEXT, buffer.toString());
                return;
            }
            buffer.append(current);
        }
        Error.ThrowError("Quotes must be closed");
    }

    private void parseNumber() {
        StringBuilder buffer = new StringBuilder();
        boolean hasErrorNumber = false;
        while(Character.isDigit(current) || current == '.'){
            if(current == '.' && buffer.indexOf(".") != -1){
                hasErrorNumber = true;
            }
            buffer.append(current);
            current = next();
        }
        if(hasErrorNumber){
            Error.ThrowError("Illegal floating point number. At number: ' " + buffer.toString() + " '");
        }
        addToken(TokenType.NUMBER, buffer.toString());
    }

    private void parseWord() {
        StringBuilder buffer = new StringBuilder();
        while(Character.isLetterOrDigit(current) || current == '_'){
            buffer.append(current);
            current = next();
        }
        switch (buffer.toString()){
            case "write":
                addToken(TokenType.WRITE);
                current = previous();
                return;
            case "writeln":
                addToken(TokenType.WRITELN);
                current = previous();
                return;
            case "if":
                addToken(TokenType.IF);
                current = previous();
                return;
            case "else":
                addToken(TokenType.ELSE);
                current = previous();
                return;
            case "function":
                addToken(TokenType.FUNCTION);
                current = previous();
                return;
            default:
                current = previous();
                if (result.isEmpty()) addToken(TokenType.WORD, buffer.toString());
                if(!(result.isEmpty()) && result.get(result.size() - 1).getType() == TokenType.FUNCTION){
                    result.remove(result.size() - 1);
                    addToken(TokenType.FUNCTION, buffer.toString());
                    return;
                }
                addToken(TokenType.WORD, buffer.toString());
                break;
        }
    }

    private char next() {
        pos++;
        return peek(0);
    }

    private char previous(){
        pos--;
        return peek(0);
    }

    private char peek(int relativePosition) {
        final int position = pos + relativePosition;
        if (position >= length) return '\0';
        return input.charAt(position);
    }

    private void addToken(TokenType type){
        result.add(new Token(type,""));
    }
    private void addToken(TokenType type, String value){
        result.add(new Token(type,value));
    }
}
