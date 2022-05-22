package compile.core.Statement;

import compile.core.Expression.operation.Expr;

public class Expression extends Stmt{
    private Expr value;
    public Expression(Expr value){
        this.value = value;
    }

    public Expr getValue() {
        return value;
    }
}
