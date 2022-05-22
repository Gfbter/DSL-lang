package compile.core.Expression.logical;

import compile.core.Expression.operation.Expr;
import compile.core.Expression.operation.OperationResult;
import compile.core.Expression.operation.LexemType;
import compile.core.Expression.arithmetic.Unary;

public class Inversion extends Unary {
    public Inversion(Expr op){
        this.one = op;
    }

    public OperationResult Eval(){
        OperationResult res = new OperationResult(LexemType.INT);
        if (one.Eval().getResultType() == LexemType.INT){
            if (one.Eval().getIntResult() == 0)
                res.setIntResult(1);
            else
                res.setIntResult(0);
        }

        return res;
    }
}
