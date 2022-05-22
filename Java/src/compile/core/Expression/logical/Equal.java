package compile.core.Expression.logical;

import compile.core.Expression.arithmetic.Binary;
import compile.core.Expression.operation.Expr;
import compile.core.Expression.operation.OperationResult;
import compile.core.Expression.operation.LexemType;

public class Equal extends Binary {
    public Equal(Expr left, Expr right){
        this.left = left;
        this.right = right;
    }

    public OperationResult Eval(){
        OperationResult left_res = left.Eval();
        OperationResult right_res = right.Eval();
        OperationResult res = new OperationResult(LexemType.INT);

        if ((left_res.getResultType() == LexemType.INT && right_res.getResultType() == LexemType.INT)) {
            if(left_res.getIntResult() == right_res.getIntResult()){
                res.setIntResult(1);
                return res;
            }
            else {
                res.setIntResult(0);
                return res;
            }
        }

        if ((left_res.getResultType() ==  LexemType.STRING && right_res.getResultType() == LexemType.STRING)) {
            if(left_res.getStringResult().equals(right_res.getStringResult())){
                res.setIntResult(1);
            }
            else {
                res.setIntResult(0);
            }
        }


        return null;
    }

}