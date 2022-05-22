package compile.core.Lexer;

import compile.core.Lexer.RegExp.LexemType;
import compile.core.Lexer.RegExp.RegExp;
import compile.core.Lexer.RegExp.RegExps;
import compile.core.Token.Token;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;


public class Lexer {
    private int iterator = 0;
    private final List<Token> tokens = new LinkedList<Token>();

    public void lexAnalysis(String script) {
        int size = script.length();
        int line = 0;
        while (iterator != size) {
            String temp = script.substring(iterator);
            for(RegExp i : RegExps.lexems) {
                Matcher m = i.getReg().matcher(temp);
                if(m.find()) {
                    String word = temp.substring(m.start(), m.end());
                    iterator += word.length();
                    if(word.equals("\n"))
                        ++line;
                    if(LexemType.SEPARATOR == i.getName())
                        tokens.add(new Token(i.getName(), word, line));
                    break;
                }
            }
        }
        tokens.add(new Token(LexemType.EOF, "", line));
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
