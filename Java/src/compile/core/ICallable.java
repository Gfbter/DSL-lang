package compile.core;

import compile.Environment;
import compile.Interpreter;

import java.util.LinkedList;

public interface ICallable {
    int arity();
    Object call(Interpreter interpreter, LinkedList<Object> arguments);
}
