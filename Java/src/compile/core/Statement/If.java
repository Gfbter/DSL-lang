package compile.core.Statement;

import compile.core.Expression.operation.Expr;

public class If extends Stmt{
    private final Expr condition;
    private final Stmt thenBranch;
    private final Stmt elseBranch;

    public If(Expr condition, Stmt thenBranch, Stmt elseBranch){
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public Expr getCondition() {
        return condition;
    }

    public Stmt getElseBranch() {
        return elseBranch;
    }

    public Stmt getThenBranch() {
        return thenBranch;
    }
}
