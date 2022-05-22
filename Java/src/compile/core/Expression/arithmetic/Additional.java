package compile.core.Expression.arithmetic;

import compile.core.Expression.operation.Expr;
import compile.core.Expression.operation.OperationResult;
import compile.core.Lexer.RegExp.LexemType;


public class Additional extends Binary {

    public Additional(Expr left, Expr right){
        this.left = left;
        this.right = right;
    }

    public OperationResult Eval(){
        OperationResult left_res = left.Eval();
        OperationResult right_res = right.Eval();
        OperationResult res = new OperationResult(LexemType.INT);


        if (left_res.getResultType() == right_res.getResultType() && left_res.getResultType() == LexemType.INT){
            res.setIntResult(right_res.getIntResult() + left_res.getIntResult());
            return res;
        }

        if (left_res.getResultType() == right_res.getResultType() && left_res.getResultType() == LexemType.STRING) {
            res.setStringResult(right_res.getStringResult() + left_res.getStringResult());
            return res;
        }

        System.out.println("Wrong operator type in Additional.Eval()");
        return null;
    }
}
