//package compile.core.Statement;
//
//import compile.Environment;
//import compile.Interpreter;
//import compile.core.Expression.operation.Expr;
//import compile.core.ReturnException;
//import compile.core.Token.Token;
//
//public class Return extends Stmt{
//    public Token keyword;
//    public Expr value;
//    public Return(Token keyword, Expr value){
//        this.keyword = keyword;
//        this.value = value;
//    }
//
//    public Expr getValue() {
//        return value;
//    }
//
//    public Token getKeyword() {
//        return keyword;
//    }
//
//    @Override
//    public void execute(Environment environment, Interpreter interpreter) {
//        if(value != null)
//            this.value.Eval(environment, interpreter);
//        throw new ReturnException(value);
//    }
//}
