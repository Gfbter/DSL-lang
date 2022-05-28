package compile.core.Expression.arithmetic;

import compile.core.Expression.operation.Expr;
import compile.core.Lexer.RegExp.LexemType;
import compile.core.RuntimeError;
import compile.core.Token.Token;

public abstract class Unary extends Expr {
    protected Token operation;
    protected Expr right;

    public Unary(Token operation, Expr right) {
        this.operation  = operation;
        this.right = right;
    }

    protected void checkNumberOperand(Token operation, Object operand)
    {
        if (operand instanceof Integer)
            return;

        throw new RuntimeError(operation, "Operand must be a number.");
    }
}
