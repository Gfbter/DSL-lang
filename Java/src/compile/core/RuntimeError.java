package compile.core;

import compile.core.Token.Token;

public class RuntimeError extends RuntimeException {
    final Token token;
    public RuntimeError(Token token, String message) {
        super(message);
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
