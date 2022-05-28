package compile.core.StdLib;

import compile.Interpreter;
import compile.core.ICallable;

import java.util.LinkedList;

public class NativePrintFunc implements ICallable {

    public int arity() {
        return 1;
    }

    public Object call(Interpreter interpreter, LinkedList<Object> arguments) {
        System.out.println(arguments.get(0));
        return null;
    }
}
