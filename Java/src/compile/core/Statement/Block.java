package compile.core.Statement;

import java.util.LinkedList;
import java.util.List;

public class Block extends Stmt{
    public List<Stmt> StatementLis;
    public Block(List<Stmt> statementList){
        statementList = statementList;
    }
}
