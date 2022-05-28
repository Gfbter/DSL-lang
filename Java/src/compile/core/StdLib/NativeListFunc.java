package compile.core.StdLib;

import compile.Interpreter;
import compile.core.ICallable;

import java.util.LinkedList;


public class NativeListFunc implements ICallable {

    public int arity() {
        return 0;
    }

    public Object call(Interpreter interpreter, LinkedList<Object> arguments) {
        return new LinkedList<Object>();
    }
}
