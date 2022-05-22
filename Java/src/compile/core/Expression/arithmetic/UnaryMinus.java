package compile.core.Expression.arithmetic;

import compile.core.Expression.operation.Expr;
import compile.core.Expression.operation.OperationResult;
import compile.core.Expression.operation.LexemType;

public class UnaryMinus extends Unary {
    public UnaryMinus(Expr op){
        one = op;
    }
    public OperationResult Eval(){
        OperationResult res = one.Eval();

        if (res.getResultType() == LexemType.INT){
            res.setIntResult(-res.getIntResult());
            return res;
        }

        System.out.println("Wrong operator type in Additional.Eval()");
        return null;
    }
}
