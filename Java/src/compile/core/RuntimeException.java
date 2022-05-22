package compile.core;

import compile.core.Token.Token;

public class RuntimeException {
    private Token token;
    private String message;

    public RuntimeException(Token token, String message){
        this.token = token;
        this.message = message;
    }

    public Token getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
