package compile.core.Statement;

import compile.core.Expression.operation.Expr;

public class While extends Stmt{
    private Expr condition;
    private Stmt body;
    public While(Expr condition, Stmt body){
        this.condition = condition;
        this.body = body;
    }

    public Expr getCondition() {
        return condition;
    }

    public Stmt getBody() {
        return body;
    }
}
