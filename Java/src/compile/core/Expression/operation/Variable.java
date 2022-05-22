package compile.core.Expression.operation;

import compile.core.Expression.arithmetic.Unary;
import compile.core.Lexer.RegExp.LexemType;

public class Variable extends Unary implements IAssignable {
    protected String name;
    protected LexemType type;

    public Variable(String name, LexemType type){
        this.name = name;
        this.type = type;
    }

    @Override
    public void setValue(OperationResult value) {

    }

    public LexemType getType() {
        return type;
    }
}
