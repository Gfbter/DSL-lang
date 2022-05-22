package compile.core.Token;

import compile.core.Lexer.RegExp.LexemType;

public class Token {

    private final LexemType type;
    private final String value;
    private final int line;

    public Token(LexemType type, String value, int line){
        this.type = type;
        this.value = value;
        this.line = line;
    }

    public LexemType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString(){
        return "TOKEN[type=\"" + this.type + "\", value=\"" + this.value + "\"]";
    }

}