package compile.core.Expression.logical;

import compile.Environment;
import compile.Interpreter;
import compile.core.Expression.arithmetic.Binary;
import compile.core.Expression.operation.Expr;
import compile.core.Token.Token;

public class notEqual extends Binary {
    public notEqual(Expr left, Token operation, Expr right){
        super(left, operation, right);
    }

    @Override
    public Object Eval(Environment environment, Interpreter interpreter) {
        Object left_res = left.Eval(environment, interpreter);
        Object right_res = right.Eval(environment, interpreter);

        return !isEqual(left_res, right_res);
    }
}
