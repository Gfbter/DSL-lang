package compile.core.Expression.arithmetic;

import compile.core.Expression.operation.Expr;
import compile.core.Expression.operation.OperationResult;
import compile.core.Expression.operation.LexemType;

public class Binary extends Expr {
    protected Expr left;
    protected Expr right;

    public Binary(Expr left, Expr right){
        this.left = left;
        this.right = right;
    }

    public Binary(){}

    @Override
    public OperationResult Eval() {return new OperationResult(LexemType.NULL); }
}
