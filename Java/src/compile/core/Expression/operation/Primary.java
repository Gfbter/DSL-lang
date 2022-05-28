package compile.core.Expression.operation;

import compile.Environment;
import compile.Interpreter;

public class Primary extends Expr{
    private Object value;

    public Primary(Object value){
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public Object Eval(Environment environment, Interpreter interpreter) {
        return this.value;
    }
}
