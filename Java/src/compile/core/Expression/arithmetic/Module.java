package compile.core.Expression.arithmetic;

import compile.Environment;
import compile.Interpreter;
import compile.core.Expression.operation.Expr;
import compile.core.Lexer.RegExp.LexemType;
import compile.core.Token.Token;

public class Module extends Binary {

    public Module(Expr left, Token operation, Expr right){
        super(left, operation, right);
    }

    @Override
    public Object Eval(Environment environment, Interpreter interpreter) {
        Object left_res = left.Eval(environment, interpreter);
        Object right_res = right.Eval(environment, interpreter);

        checkNumberOperands(left_res, right_res);

        return (int)left_res % (int)right_res;
    }
}
