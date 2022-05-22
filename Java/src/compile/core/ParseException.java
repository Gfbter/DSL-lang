package compile.core;

import compile.core.Token.Token;

public class ParseException extends Throwable {
    protected Token token;
    private String message;
    public ParseException(Token token, String message){
        this.token = token;
        this.message = message;
    }
}
