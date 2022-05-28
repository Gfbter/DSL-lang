package compile.core.Expression.logical;

import compile.Environment;
import compile.Interpreter;
import compile.core.Expression.arithmetic.Binary;
import compile.core.Expression.operation.Expr;
import compile.core.Lexer.RegExp.LexemType;
import compile.core.Token.Token;

public class Or extends Binary {
    public Or(Expr left, Token operation, Expr right){
            super(left, operation, right);
        }


    @Override
    public Object Eval(Environment environment, Interpreter interpreter) {
        Object left_res = left.Eval(environment, interpreter);
        Object right_res = right.Eval(environment, interpreter);

        checkNumberOperands(left_res, right_res);
        return ((Integer)left_res != 0)  || ((Integer) right_res != 0);
    }
}
