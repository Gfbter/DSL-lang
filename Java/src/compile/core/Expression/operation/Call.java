package compile.core.Expression.operation;

import compile.Environment;
import compile.Interpreter;
import compile.core.ICallable;
import compile.core.NativeFunctionException;
import compile.core.ReturnException;
import compile.core.RuntimeError;
import compile.core.Token.Token;

import java.util.LinkedList;

public class Call extends Expr{
    private Token token;
    private LinkedList<Expr> arguments;
    private Expr caller;

    public Call(Expr caller, Token paren, LinkedList<Expr> arguments){
        this.token = paren;
        this.caller = caller;
        this.arguments = arguments;
    }


    public Object Eval(Environment environment, Interpreter interpreter) {
        Object caller = this.caller.Eval(environment, interpreter);
        if(!(caller instanceof ICallable)){
            throw new RuntimeError(this.token, "Can only call functions.");
        }
        ICallable function = (ICallable)caller;

        LinkedList<Object> params = new LinkedList<Object>();
        for(Expr argument : this.arguments){
            params.add(argument.Eval(environment, interpreter));
        }

        if(params.size() != function.arity()){
            throw new RuntimeError(this.token, "Expected " + function.arity() + " arguments but got " + arguments.size() + ".");
        }
        try{
            return function.call(interpreter, params);
        }
        catch(NativeFunctionException ex){
            throw new RuntimeError(this.token, ex.getMessage());
        }
    }

    public Expr getCaller() {
        return caller;
    }

    public LinkedList<Expr> getArguments() {
        return arguments;
    }

    public Token getToken() {
        return token;
    }
}
