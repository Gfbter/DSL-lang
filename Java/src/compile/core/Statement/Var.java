package compile.core.Statement;

import compile.Environment;
import compile.Interpreter;
import compile.core.Expression.operation.Expr;
import compile.core.RuntimeError;
import compile.core.Token.Token;

public class Var extends Stmt{
    private Token token;
    private Expr initializer;

    public Var(Token token, Expr initializer){
        this.token = token;
        this.initializer = initializer;
    }


    public Token getToken() {
        return token;
    }

    public Expr getInitializer() {
        return initializer;
    }

    public void execute(Environment environment, Interpreter interpreter) {
        Object value = null;
        if(this.initializer != null){
            value = this.initializer.Eval(environment, interpreter);
        }
        if(environment.VarExists(this.token.getValue()))
            throw new RuntimeError(this.token,"Variable [" + this.token.getValue() + "] alredy defined!");
        environment.Define(this.token.getValue(), value);
    }
}
