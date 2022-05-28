package compile.core.StdLib;

import compile.Interpreter;
import compile.core.ICallable;
import compile.core.RuntimeError;

import java.util.LinkedList;

public class NativeListFuncAdd implements ICallable {
    public int arity() {
        return 2;
    }

    public Object call(Interpreter interpreter, LinkedList<Object> arguments) {
        if(!(arguments.get(0) instanceof LinkedList)){
            return null;
        }


        LinkedList<Object> list = (LinkedList<Object>)arguments.get(0);
        list.add(arguments.get(1));
        return null;
    }
}
