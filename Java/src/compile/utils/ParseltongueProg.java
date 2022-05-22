package compile.utils;

import java.util.ArrayList;
import java.util.LinkedList;

public class ParseltongueProg {
    public ArrayList<String> sources;

    public ParseltongueProg(){
        // globals['a'] -> 1
        // 1 + 1 [=> 2]
        // int a = 1;  a + 1 [ =>2]
        // int a;
        sources = new ArrayList<String>();
        sources.add("print(1 + 1);");
        sources.add("a = 1 + 1;\nprint(a);");
        sources.add("a = 1 + 2 - (1 / 3) * 3;\nprint(a);");
        sources.add("b = 1;\nc = 2;\na = 1 + c - (b / 3) * 3;\nprint(a);");
        sources.add("b = 1;\nc = 2;\nif (b > c)\n{ a = b; }\n else\n{ a = c; }\nprint(a);");
    }
}
