package compile.core.Expression.operation;

public class Grouping extends Expr{
    private Expr expression;
    public Grouping(Expr expression){
        this.expression = expression;
    }

    public Expr getExpression() {
        return expression;
    }

    @Override
    public OperationResult Eval() {
        return null;
    }
}
