package compile.core.StdLib;

import compile.Environment;

public class ImportStdLib {
    public void include(Environment environment){
        environment.Define("_print", new NativePrintFunc());
        environment.Define("_list", new NativeListFunc());
        environment.Define("_add", new NativeListFuncAdd());
        environment.Define("_size", new NativeListFuncGetSize());
        environment.Define("_get", new NativeListFuncGet());
        environment.Define("_empty", new NativeListFuncIsEmpty());
        environment.Define("_remove", new NativeListFuncRemove());
    }
}
