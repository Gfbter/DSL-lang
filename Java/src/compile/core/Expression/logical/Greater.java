package compile.core.Expression.logical;

import compile.Environment;
import compile.Interpreter;
import compile.core.Expression.arithmetic.Binary;
import compile.core.Expression.operation.Expr;
import compile.core.Token.Token;

import javax.swing.*;

public class Greater extends Binary {
    public Greater(Expr left, Token operation, Expr right){
        super(left, operation, right);
    }

    @Override
    public Object Eval(Environment environment, Interpreter interpreter) {
        Object left_res = left.Eval(environment, interpreter);
        Object right_res = right.Eval(environment, interpreter);

        checkNumberOperands(left_res, right_res);
        return (Integer)left_res > (Integer) right_res;
    }
}
