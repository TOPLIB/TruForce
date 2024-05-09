package Core.Parser;

import Core.Lexer.Token;

import java.util.List;

public class Parser {

    private final List<Token> input;

    public Parser(List<Token> input) {
        this.input = input;
    }
}
