package compile.core.Expression.arithmetic;

import compile.Environment;
import compile.Interpreter;
import compile.core.Expression.operation.Expr;
import compile.core.Token.Token;


public class Additional extends Binary {

    public Additional(Expr left, Token operation, Expr right) {
        super(left, operation, right);
    }

    @Override
    public Object Eval(Environment environment, Interpreter interpreter) {
        Object left_res = left.Eval(environment, interpreter);
        Object right_res = right.Eval(environment, interpreter);

        checkNumberOperands(left_res, right_res);

        return (int)left_res + (int)right_res;
    }
}
