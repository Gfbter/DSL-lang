package compile.core.Expression.logical;

import compile.core.Expression.arithmetic.Binary;
import compile.core.Expression.operation.Expr;
import compile.core.Expression.operation.OperationResult;
import compile.core.Lexer.RegExp.LexemType;

public class And extends Binary {
    public And(Expr left, Expr right) {
        this.right = right;
        this.left = left;
    }

    public OperationResult Eval() {
        OperationResult rightRes = right.Eval();
        OperationResult leftRes = left.Eval();
        OperationResult res = new OperationResult(LexemType.INT);

        if (rightRes.getIntResult() + leftRes.getIntResult() != 2) {
            res.setIntResult(0);
        } else {
            res.setIntResult(1);
        }
        return res;
    }
}
