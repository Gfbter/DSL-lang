package compile.core.Expression.logical;

import compile.core.Expression.arithmetic.Binary;
import compile.core.Expression.operation.Expr;
import compile.core.Expression.operation.OperationResult;
import compile.core.Expression.operation.LexemType;

import static compile.core.Expression.operation.LexemType.INT;

public class GreaterEqual extends Binary {
    public GreaterEqual(Expr left, Expr right){
        this.left = left;
        this.right = right;
    }

    public OperationResult Eval(){
        OperationResult left_res = left.Eval();
        OperationResult right_res = right.Eval();
        OperationResult res = new OperationResult(INT);

        if ((left_res.getResultType() == LexemType.INT && right_res.getResultType() == LexemType.INT)) {
            if (left_res.getIntResult() >= right_res.getIntResult()){
                res.setIntResult(1);
                return res;
            }
            else {
                res.setIntResult(0);
                return res;
            }
        }

        if ((left_res.getResultType() ==  LexemType.STRING && right_res.getResultType() ==  LexemType.STRING)){
            if (left_res.getStringResult().compareTo(right_res.getStringResult()) >= 0){
                res.setIntResult(1);
                return res;
            }
            else{
                res.setIntResult(0);
                return res;
            }
        }

        return null;
    }
}
