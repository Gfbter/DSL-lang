package compile.core.Expression.arithmetic;

import compile.Environment;
import compile.Interpreter;
import compile.core.Expression.operation.Expr;
import compile.core.Lexer.RegExp.LexemType;
import compile.core.Token.Token;

public class UnaryMinus extends Unary {

    public UnaryMinus(Token operation, Expr right) {
        super(operation, right);
    }

    @Override
    public Object Eval(Environment environment, Interpreter interpreter) {
        Object right_res = right.Eval(environment, interpreter);

        checkNumberOperand(operation, right_res);
        return ((Integer) right_res);
    }
}
