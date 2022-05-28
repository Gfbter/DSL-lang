package compile.core.Lexer.RegExp;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class RegExps {

    //private static Map<String, Pattern> lexems = new HashMap<>();
    public static List<RegExp> lexems = new LinkedList<RegExp>();

    static {
        lexems.add(new RegExp(LexemType.LEFT_PAREN, Pattern.compile("^\\(")));
        lexems.add(new RegExp(LexemType.RIGHT_PAREN, Pattern.compile("^\\)")));
        lexems.add(new RegExp(LexemType.RIGHT_BRACE, Pattern.compile("^}")));
        lexems.add(new RegExp(LexemType.LEFT_BRACE, Pattern.compile("^\\{")));
        lexems.add(new RegExp(LexemType.COMMA, Pattern.compile("^,")));
        lexems.add(new RegExp(LexemType.SEMICOLON, Pattern.compile("^;")));
        lexems.add(new RegExp(LexemType.MINUS, Pattern.compile("^-")));
        lexems.add(new RegExp(LexemType.PLUS, Pattern.compile("^\\+")));
        lexems.add(new RegExp(LexemType.DIVISION, Pattern.compile("^/")));
        lexems.add(new RegExp(LexemType.MULT, Pattern.compile("^\\*")));
        lexems.add(new RegExp(LexemType.MOD, Pattern.compile("^%")));
        lexems.add(new RegExp(LexemType.ASSIGN, Pattern.compile("^=")));
        lexems.add(new RegExp(LexemType.EQUAL, Pattern.compile("^==")));
        lexems.add(new RegExp(LexemType.NOT, Pattern.compile("^!")));
        lexems.add(new RegExp(LexemType.NOT_EQUAL, Pattern.compile("^!=")));
        lexems.add(new RegExp(LexemType.GREATER, Pattern.compile("^>")));
        lexems.add(new RegExp(LexemType.GREATER_EQUAL, Pattern.compile("^>=")));
        lexems.add(new RegExp(LexemType.LESS, Pattern.compile("^<")));
        lexems.add(new RegExp(LexemType.LESS_EQUAL, Pattern.compile("^<=")));
        lexems.add(new RegExp(LexemType.STRING, Pattern.compile("^string")));
        lexems.add(new RegExp(LexemType.INT, Pattern.compile("^int")));
        lexems.add(new RegExp(LexemType.AND, Pattern.compile("^&")));
        lexems.add(new RegExp(LexemType.OR, Pattern.compile("^\\|")));
        lexems.add(new RegExp(LexemType.IF, Pattern.compile("^if")));
        lexems.add(new RegExp(LexemType.ELSE, Pattern.compile("^else")));
        lexems.add(new RegExp(LexemType.FOR, Pattern.compile("^for")));
        lexems.add(new RegExp(LexemType.WHILE, Pattern.compile("^while")));
        lexems.add(new RegExp(LexemType.DO, Pattern.compile("^do")));
        lexems.add(new RegExp(LexemType.COMMA, Pattern.compile("^,")));
        lexems.add(new RegExp(LexemType.RETURN, Pattern.compile("^return")));
        lexems.add(new RegExp(LexemType.PRINT, Pattern.compile("^print")));
        lexems.add(new RegExp(LexemType.VARIABLE, Pattern.compile("^_?([a-zA-Z][a-zA-Z0-9]*)")));
        lexems.add(new RegExp(LexemType.DIGIT, Pattern.compile("^(0|([1-9][0-9]*))")));
        lexems.add(new RegExp(LexemType.SEPARATOR, Pattern.compile("^([ \t\n])")));
        lexems.add(new RegExp(LexemType.TEXT, Pattern.compile("^\"[a-zA-Z0-9 .:,;@!?\n\t]*\"")));
    }
}
