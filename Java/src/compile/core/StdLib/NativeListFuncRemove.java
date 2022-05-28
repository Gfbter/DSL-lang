package compile.core.StdLib;

import compile.Interpreter;
import compile.core.ICallable;

import java.util.LinkedList;

public class NativeListFuncRemove implements ICallable {
    public int arity() {
        return 2;
    }

    public Object call(Interpreter interpreter, LinkedList<Object> arguments) {
        if(!(arguments.get(0) instanceof LinkedList)){
            return null;
        }

        LinkedList<Object> list = (LinkedList<Object>)arguments.get(0);
        list.remove(arguments.get(1));
        return null;
    }
}
