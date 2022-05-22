package compile.core.Statement;

import compile.core.Expression.operation.Expr;

public class Print extends Stmt{
    private Expr expression;
    public Print(Expr expression){
        this.expression = expression;
    }
}
