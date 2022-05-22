package compile.core.Expression.operation;

import compile.core.Expression.arithmetic.Unary;

public class Str extends Unary {
    protected OperationResult value;

    public Str(OperationResult value) { this.value = new OperationResult(value);}
    public OperationResult Eval() {return value;}
}
