package compile.core.Expression.arithmetic;

import compile.core.Expression.operation.IAssignable;
import compile.core.Expression.operation.Expr;
import compile.core.Expression.operation.OperationResult;

import java.util.Hashtable;

public class Assign extends Binary {
    protected IAssignable left;
    protected Expr right;

    public Assign(Expr left, Expr right, Hashtable<String, OperationResult> globals) {
        if (left instanceof IAssignable)
            this.left = (IAssignable) left;
        this.right = right;
    }

    public OperationResult Eval() {
        OperationResult result = right.Eval();
        if (left.getType() == result.getResultType())
            left.setValue(result);

        return null; //result;
    }
}
