package compile.core.Statement;

import compile.Environment;
import compile.Interpreter;

public abstract class Stmt {

    public abstract void execute(Environment environment, Interpreter interpreter);

    protected boolean isTrue(Object obj){
        if(obj == null)
            return false;
        if(obj instanceof Integer)
            return !obj.equals(0);
        if(obj instanceof Boolean)
            return (boolean)obj;

        return true;
    }

    protected String toString(Object obj)
    {
        if (obj == null) return "null";

        if (obj instanceof Double)
        {
            String text = obj.toString();
            return text;
        }

        return obj.toString();
    }
}
