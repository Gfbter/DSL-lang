package compile.core.Statement;

import compile.Environment;
import compile.Interpreter;

import java.util.LinkedList;
import java.util.List;

public class Block extends Stmt{
    private List<Stmt> StatementList;

    public Block(List<Stmt> statementList){
        this.StatementList = statementList;
    }

    public List<Stmt> getStatementList() {
        return StatementList;
    }

    public void execute(Environment environment) {

    }

    @Override
    public void execute(Environment environment, Interpreter interpreter) {
        for(Stmt statement : this.StatementList){
            statement.execute(new Environment(environment), interpreter);
        }
    }
}
