package compile.core.Expression.arithmetic;

import compile.Environment;
import compile.Interpreter;
import compile.core.Expression.operation.Expr;
import compile.core.Token.Token;

public class Assign extends Expr {
    protected Token name;
    protected Expr expression;

    protected Token operation;

    public Assign(Token name, Expr value) {
        this.name = name;
        this.expression = value;
    }

    public Token getName() {
        return name;
    }

    public Expr getExpression() {
        return expression;
    }

    @Override
    public Object Eval(Environment environment, Interpreter interpreter) {
        Object value = this.expression.Eval(environment, interpreter);
        environment.Assign(this.getName(), this.getExpression().Eval(environment, interpreter));
        return value;
    }
}
