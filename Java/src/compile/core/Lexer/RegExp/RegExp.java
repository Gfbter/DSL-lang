package compile.core.Lexer.RegExp;

import java.util.regex.Pattern;

public class RegExp {
    private final LexemType name;
    private final Pattern reg;

    public RegExp(LexemType name, Pattern reg) {
        this.name = name;
        this.reg = reg;
    }

    public LexemType getName() {
        return name;
    }

    public Pattern getReg() {
        return reg;
    }
}
