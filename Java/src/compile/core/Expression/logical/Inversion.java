package compile.core.Expression.logical;

import compile.Environment;
import compile.Interpreter;
import compile.core.Expression.operation.Expr;
import compile.core.Expression.arithmetic.Unary;
import compile.core.Token.Token;

public class Inversion extends Unary {
    public Inversion(Token operation, Expr right) {
        super(operation, right);
    }

    @Override
    public Object Eval(Environment environment, Interpreter interpreter) {
        Object right_res = right.Eval(environment, interpreter);

        checkNumberOperand(operation, right_res);
        return ((Integer) right_res == 0 ? 1 : 0);
    }
}
