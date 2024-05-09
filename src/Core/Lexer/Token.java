package Core.Lexer;

public class Token {
    private String value;
    private TokenType type;

    public Token(TokenType type, String value){
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if(value == "") return type.toString();
        return type + " | " + value;
    }
}
