package compile.core.Statement;

import compile.core.Expression.operation.Expr;

public class Var extends Stmt{
    private String name;
    private Expr initializer;

    public Var(String name, Expr initializer){
        this.name = name;
        this.initializer = initializer;
    }

    public String getName() {
        return name;
    }

    public Expr getInitializer() {
        return initializer;
    }
}
