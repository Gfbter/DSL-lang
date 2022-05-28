package compile.core.Expression.operation;

import compile.Environment;
import compile.Interpreter;
import compile.core.Expression.arithmetic.Unary;
import compile.core.Lexer.RegExp.LexemType;
import compile.core.Token.Token;

public class Variable extends Expr { //implements IAssignable {
    protected Token name;

    public Variable(Token name){
        this.name = name;
    }

    public Token getName() {
        return name;
    }

    public Object Eval(Environment environment) {
        return environment.Get(this.name);
    }

    public Object Eval(Environment environment, Interpreter interpreter) {
        return environment.Get(this.name);
    }
}
