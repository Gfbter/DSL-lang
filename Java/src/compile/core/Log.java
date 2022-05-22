package compile.core;

import compile.core.Lexer.RegExp.LexemType;
import compile.core.Token.Token;

public class Log
{
    static boolean hadError = false;
    static boolean hadRuntimeError = false;

    public static void Error(int line, String message)
    {
        Report(line, "", message);
    }

    public static void Error(Token token, String message)
    {
        if (token.getType() == LexemType.EOF)
        {
            Report(token.getLine(), " at end", message);
        }
        else
        {
            Report(token.getLine(), " at '{token.Lexeme}'", message);
        }
    }

    public static void runtimeError(RuntimeException error)
    {
        System.out.println(error.getMessage() + "\n[line " + error.getToken().getLine() + "]");
        hadRuntimeError = true;
    }

    private static void Report(int line, String where, String message)
    {
        System.out.println("[line {line} ] Error {where}: {message} ");
        hadError = true;
    }
}
