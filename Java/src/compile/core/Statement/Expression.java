package compile.core.Statement;

import compile.Environment;
import compile.Interpreter;
import compile.core.Expression.operation.Expr;

public class Expression extends Stmt{
    private Expr value;
    public Expression(Expr value){
        this.value = value;
    }

    public Expr getValue() {
        return value;
    }

    @Override
    public void execute(Environment environment, Interpreter interpreter) {
        value.Eval(environment, interpreter);
    }
}
