package compile.core.Expression.operation;

import compile.Environment;
import compile.Interpreter;

public abstract class Expr implements IOperation {
    public abstract Object Eval(Environment environment, Interpreter interpreter);
}

