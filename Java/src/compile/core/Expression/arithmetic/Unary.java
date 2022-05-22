package compile.core.Expression.arithmetic;

import compile.core.Expression.operation.Expr;
import compile.core.Expression.operation.OperationResult;
import compile.core.Lexer.RegExp.LexemType;

public class Unary extends Expr {
    protected Expr one;

    public Unary(Expr op) {this.one = op; }

    public Unary() {}

    @Override
    public OperationResult Eval() {return new OperationResult(LexemType.NULL); }
}
