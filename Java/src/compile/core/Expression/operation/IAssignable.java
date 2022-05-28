package compile.core.Expression.operation;

import compile.core.Lexer.RegExp.LexemType;

public interface IAssignable {
    public void setValue(Object value);
    public LexemType getType();
}
