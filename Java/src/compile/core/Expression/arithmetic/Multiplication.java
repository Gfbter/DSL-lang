package compile.core.Expression.arithmetic;

import compile.core.Expression.operation.Expr;
import compile.core.Expression.operation.OperationResult;
import compile.core.Expression.operation.LexemType;


public class Multiplication extends Binary {
    public Multiplication(Expr left, Expr right){
        this.left = left;
        this.right = right;
    }

    public OperationResult Eval(){
        OperationResult left_res = left.Eval();
        OperationResult right_res = right.Eval();
        OperationResult res = new OperationResult(LexemType.INT);


        if (left_res.getResultType() == right_res.getResultType() && left_res.getResultType() == LexemType.INT){
            res.setIntResult(right_res.getIntResult() * left_res.getIntResult());
            return res;
        }

//        if (left_res.getResultType() == ResultType.STRING && right_res.getResultType() == ResultType.INT) {
//            res.setStringResult(String.valueOf(left_res.getStringResult()).repeat(Math.max(0, right_res.getIntResult())));
//            return res;
//        }
//
//        if(left_res.getResultType() == ResultType.INT && right_res.getResultType() == ResultType.STRING) {
//            res.setStringResult(String.valueOf(right_res.getStringResult()).repeat(Math.max(0, left_res.getIntResult())));
//            return res;
//        }

        System.out.println("Wrong operator type in Additional.Eval()");
        return null;
    }
}
