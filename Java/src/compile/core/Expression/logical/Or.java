package compile.core.Expression.logical;

import compile.core.Expression.arithmetic.Binary;
import compile.core.Expression.operation.Expr;
import compile.core.Expression.operation.OperationResult;
import compile.core.Expression.operation.LexemType;

public class Or extends Binary {
        public Or(Expr left, Expr right){
            this.left = left;
            this.right = right;
        }


        public OperationResult Eval(){
            OperationResult rightRes = right.Eval();
            OperationResult leftRes = left.Eval();
            OperationResult res = new OperationResult(LexemType.INT);

            if(rightRes.getIntResult() + leftRes.getIntResult() != 0){
                res.setIntResult(1);
            }
            else{
                res.setIntResult(0);
            }
            return res;
        }
}
