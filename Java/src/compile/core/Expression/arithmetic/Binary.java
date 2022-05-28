package compile.core.Expression.arithmetic;

import compile.core.Expression.operation.Expr;
import compile.core.Lexer.RegExp.LexemType;
import compile.core.RuntimeError;
import compile.core.Token.Token;

public abstract class Binary extends Expr {
    protected Expr left;
    protected Expr right;
    protected Token operation;

    public Binary(Expr left, Token operation,  Expr right) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    protected boolean checkNumberOperands(Object left, Object right)
    {
        if (left instanceof Integer && right instanceof Integer)
            return true;

        throw new RuntimeError(operation, "Operand must be a number.");
    }

    protected boolean isEqual(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null) return false;

        return a.equals(b);
    }
}
