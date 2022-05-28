package compile.core.Statement;

import compile.Environment;
import compile.Interpreter;
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

    @Override
    public void execute(Environment environment, Interpreter interpreter) {
        while(isTrue(this.condition.Eval(environment, interpreter))){
            this.body.execute(environment, interpreter);
        }
    }
}
