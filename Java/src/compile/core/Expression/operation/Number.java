package compile.core.Expression.operation;

public class Number extends Expr {
    protected OperationResult value;

    public Number(OperationResult value) { this.value = new OperationResult(value);}
    public OperationResult Eval() {return value;}
}
