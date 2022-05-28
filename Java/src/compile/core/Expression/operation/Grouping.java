package compile.core.Expression.operation;

import compile.Environment;
import compile.Interpreter;

public class Grouping extends Expr{
    private Expr expression;
    public Grouping(Expr expression){
        this.expression = expression;
    }

    public Expr getExpression() {
        return expression;
    }

    @Override
    public Object Eval(Environment environment, Interpreter interpreter) {
        return this.expression.Eval(environment, interpreter);
    }
}
