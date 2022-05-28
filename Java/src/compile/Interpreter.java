package compile;

import compile.core.Expression.arithmetic.Assign;
import compile.core.Expression.operation.Expr;
import compile.core.Log;
import compile.core.RuntimeError;
import compile.core.Statement.*;
import compile.core.StdLib.ImportStdLib;

import java.beans.Introspector;
import java.beans.Statement;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.System.in;

public class Interpreter {
    protected Environment environment = new Environment();

    public Interpreter(){
        ImportStdLib importer = new ImportStdLib();
        importer.include(environment);
    }

    public void interpret(LinkedList<Stmt> statements){
        try{
            for(Stmt statement : statements){
                statement.execute(environment, this);
            }
        }
        catch(RuntimeError error){
            Log.runtimeError(error);
        }
    }
}
