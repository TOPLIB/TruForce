import Core.Lexer.Lexer;
import Core.Lexer.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("test.tfr"));
        String code;
        StringBuilder buffer = new StringBuilder();
        for(int i = 0; i < lines.size(); i++){
            buffer.append(lines.get(i) + " ");
        }
        code = buffer.toString();
        List<Token> tokens = new Lexer(code).lex();
        for (Token token : tokens){
            System.out.println(token);
        }
    }
}
