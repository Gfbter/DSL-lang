package compile.core.Expression.operation;

import compile.core.Lexer.RegExp.LexemType;

public interface IAssignable {
    public void setValue(OperationResult value);
    public LexemType getType();
}
