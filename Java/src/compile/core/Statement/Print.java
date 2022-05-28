package compile.core.Statement;

import compile.Environment;
import compile.Interpreter;
import compile.core.Expression.operation.Expr;

public class Print extends Stmt{
    private Expr expression;
    public Print(Expr expression){
        this.expression = expression;
    }
    @Override
    public void execute(Environment environment, Interpreter interpreter) {
        Object value = this.expression.Eval(environment, interpreter);
        System.out.println(toString(value));
    }
}
