package compile.core.StdLib;

import compile.Interpreter;
import compile.core.ICallable;

import java.util.LinkedList;

public class NativeListFuncGetSize implements ICallable {

    public int arity() {
        return 1;
    }

    public Object call(Interpreter interpreter, LinkedList<Object> arguments) {
        if(!(arguments.get(0) instanceof LinkedList)){
            return null;
        }

        return ((LinkedList<?>) arguments.get(0)).size();
    }
}
