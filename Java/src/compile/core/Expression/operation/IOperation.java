package compile.core.Expression.operation;

import compile.Environment;
import compile.Interpreter;

public interface IOperation {
        Object Eval(Environment environment, Interpreter interpreter);
    }