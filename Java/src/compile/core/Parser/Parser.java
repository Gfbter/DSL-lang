package compile.core.Parser;

import compile.core.Expression.arithmetic.*;
import compile.core.Expression.arithmetic.Module;
import compile.core.Expression.logical.*;
import compile.core.Lexer.RegExp.LexemType;
import compile.core.Log;
import compile.core.ParseException;
import compile.core.Statement.*;
import compile.core.Token.Token;
import compile.core.Expression.operation.*;
import compile.core.Expression.operation.Number;

import java.util.*;

public class Parser {
    private List<Token> tokens = new LinkedList<Token>();
    private Integer iterator = 0;
    Hashtable<String, OperationResult> globals;

    private static class ParseError extends RuntimeException {}

    Parser(List<Token> tokens){
        this.tokens = tokens;
        globals = new Hashtable<String, OperationResult>();
    }

    private LinkedList<Stmt> parse(){
        LinkedList<Stmt> statements = new LinkedList();
        while(!isEnd()){
            statements.add(declaration());
        }
        return statements;
    }

    private Stmt declaration(){
        try {
            if(match(compile.core.Lexer.RegExp.LexemType.INT) | match(compile.core.Lexer.RegExp.LexemType.STRING))
                return varDeclaration();
            return statement();
        }
        catch(ParseError error){
            synchronize();
            return null;
        }
    }

    private Stmt varDeclaration(){
        Token name = consume(compile.core.Lexer.RegExp.LexemType.VARIABLE, "Expect variable name.");
        Expr initializer = null;
        if(match(compile.core.Lexer.RegExp.LexemType.ASSIGN)){
            initializer = expression();
        }
        consume(compile.core.Lexer.RegExp.LexemType.SEMICOLON, "Expect ';' after variable declaration.");
        return new Var(name.getValue(), initializer);
    }

    private Stmt statement(){
        if(match(compile.core.Lexer.RegExp.LexemType.FOR))
            return forStatement();
        if(match(compile.core.Lexer.RegExp.LexemType.IF))
            return ifStatement();
        if(match(compile.core.Lexer.RegExp.LexemType.PRINT))
            return printStatement();
        if(match(compile.core.Lexer.RegExp.LexemType.WHILE))
            return whileStatement();
        if(match(compile.core.Lexer.RegExp.LexemType.LEFT_BRACE))
            return new Block(block());

        return expressionStatement();
    }

    private Stmt forStatement()
    {
        consume(compile.core.Lexer.RegExp.LexemType.LEFT_PAREN, "Expect '(' after 'for'.");

        Stmt initializer;
        if (match(compile.core.Lexer.RegExp.LexemType.SEMICOLON))
        {
            initializer = null;
        }
        else if (match(compile.core.Lexer.RegExp.LexemType.VARIABLE
        ))
        {
            initializer = varDeclaration();
        }
        else
        {
            initializer = expressionStatement();
        }

        Expr condition = null;
        if (!check(compile.core.Lexer.RegExp.LexemType.SEMICOLON))
        {
            condition = expression();
        }
        consume(compile.core.Lexer.RegExp.LexemType.SEMICOLON, "Expect ';' after loop condition.");

        Expr increment = null;
        if (!check(compile.core.Lexer.RegExp.LexemType.RIGHT_PAREN))
        {
            increment = expression();
        }
        consume(compile.core.Lexer.RegExp.LexemType.RIGHT_PAREN, "Expect ')' after for clauses.");
        Stmt body = statement();

        if (increment != null)
        {
            body = new Block(Arrays.asList(body, new Expression(increment)));
        }

        if (condition == null) condition = new Number(new OperationResult(LexemType.INT, 1));
        body = new While(condition, body);

        if (initializer != null)
        {
            body = new Block(Arrays.asList(body, initializer));
        }

        return body;
    }

    private Stmt ifStatement()
    {
        consume(compile.core.Lexer.RegExp.LexemType.LEFT_PAREN, "Expect '(' after 'if'.");
        Expr condition = expression();
        consume(compile.core.Lexer.RegExp.LexemType.RIGHT_PAREN, "Expect ')' after if condition.");

        Stmt thenBranch = statement();
        Stmt elseBranch = null;
        if (match(compile.core.Lexer.RegExp.LexemType.ELSE))
        {
            elseBranch = statement();
        }

        return new If(condition, thenBranch, elseBranch);
    }

    private Stmt printStatement()
    {
        Expr value = expression();
        consume(compile.core.Lexer.RegExp.LexemType.SEMICOLON, "Expect ';' after value.");
        return new Print(value);
    }

    private Stmt whileStatement()
    {
        consume(compile.core.Lexer.RegExp.LexemType.LEFT_PAREN, "Expect '(' after 'while'.");
        Expr condition = expression();
        consume(compile.core.Lexer.RegExp.LexemType.RIGHT_PAREN, "Expect ')' after condition.");
        Stmt body = statement();

        return new While(condition, body);
    }
    //< Control Flow while-statement
    //> Statements and State parse-expression-statement
    private Stmt expressionStatement()
    {
        Expr expr = expression();
        consume(compile.core.Lexer.RegExp.LexemType.SEMICOLON, "Expect ';' after expression.");
        return new Expression(expr);
    }

    private LinkedList<Stmt> block()
    {
        LinkedList<Stmt> statements = new LinkedList();

        while (!check(compile.core.Lexer.RegExp.LexemType.RIGHT_BRACE) && !isEnd())
        {
            statements.add(declaration());
        }

        consume(compile.core.Lexer.RegExp.LexemType.RIGHT_BRACE, "Expect '}' after block.");
        return statements;
    }

    private Expr expression() {
        return assignment();
    }

    private Expr assignment(){
        Expr expr = or();
        Expr result = null;

        if(match(compile.core.Lexer.RegExp.LexemType.ASSIGN)){
            result = new Assign(expr, and(), globals);
        }
        return null;
    }

    private Expr or(){
        Expr expr = and();
        Expr result = null;

        while(match(compile.core.Lexer.RegExp.LexemType.OR)){
            result = new Or(expr, and());
        }
        return null;
    }

    private Expr and(){
        Expr expr = equality();
        Expr result = null;

        while(match(compile.core.Lexer.RegExp.LexemType.AND)){
            result = new And(expr, equality());
        }

        return result;
    }

    private Expr equality(){
        Expr expr = comparison();
        Expr result = null;

        while(match(new compile.core.Lexer.RegExp.LexemType[] { compile.core.Lexer.RegExp.LexemType.NOT_EQUAL, compile.core.Lexer.RegExp.LexemType.EQUAL})){
            switch(previous().getType()){
                case NOT_EQUAL -> result = new notEqual(expr, comparison());
                case EQUAL -> result = new Equal(expr, comparison());
            }
        }

        return result;
    }

    private Expr comparison(){
        Expr expr = term();
        Expr result = null;

        while(match(new compile.core.Lexer.RegExp.LexemType[]{ compile.core.Lexer.RegExp.LexemType.GREATER, compile.core.Lexer.RegExp.LexemType.GREATER_EQUAL, compile.core.Lexer.RegExp.LexemType.LESS, compile.core.Lexer.RegExp.LexemType.LESS_EQUAL})){
            switch(previous().getType()){
                case GREATER -> result = new Greater(expr, term());
                case GREATER_EQUAL -> result = new GreaterEqual(expr, term());
                case LESS -> result = new Less(expr, term());
                case LESS_EQUAL -> result = new LessEqual(expr, term());
            }
        }

        return result;
    }

    private Expr term(){
        Expr expr = factor();
        Expr result = null;

        while(match(new compile.core.Lexer.RegExp.LexemType[] {compile.core.Lexer.RegExp.LexemType.MINUS, compile.core.Lexer.RegExp.LexemType.PLUS})){
            switch(previous().getType()){
                case MINUS -> result = new Subtraction(expr, unary());//unary?? mb term()
                case PLUS -> result = new Additional(expr, unary());
            }
        }

        return result;
    }

    private Expr factor(){
        Expr expr = unary();
        Expr result = null;

        while(match(new compile.core.Lexer.RegExp.LexemType[]{ compile.core.Lexer.RegExp.LexemType.DIVISION, compile.core.Lexer.RegExp.LexemType.MOD, compile.core.Lexer.RegExp.LexemType.MULT})){
            switch(previous().getType()){
                case DIVISION -> result = new Division(expr, unary());
                case MOD -> result = new Module(expr, unary());
                case MULT -> result = new Multiplication(expr, unary());
            }
        }

        return result;
    }

    private Expr unary(){
        if(match(new compile.core.Lexer.RegExp.LexemType[] {compile.core.Lexer.RegExp.LexemType.NOT, compile.core.Lexer.RegExp.LexemType.MINUS})){
            Expr result = null;
            switch(previous().getType()){
                case NOT -> result = new Inversion(unary());
                case MINUS -> result = new UnaryMinus(unary());
            }
            return result;
        }

        return primary();
    }

    private Expr primary()
    {
        if(match(compile.core.Lexer.RegExp.LexemType.STRING)){
            OperationResult result = new OperationResult(LexemType.STRING);
            result.setStringResult(previous().getValue());
            return new Str(result);
        }

        if(match(compile.core.Lexer.RegExp.LexemType.INT)){
            OperationResult result = new OperationResult(LexemType.STRING);
            result.setIntResult(Integer.parseInt(previous().getValue()));
            return new Number(result);
        }

        if(match(compile.core.Lexer.RegExp.LexemType.VARIABLE)){
            return new Variable(previous().getValue(), LexemType.STRING);
            return new Variable(previous().getValue(), LexemType.INT);
        }

        if(match(compile.core.Lexer.RegExp.LexemType.LEFT_PAREN))
        {
            Expr expr = expression();
            consume(compile.core.Lexer.RegExp.LexemType.RIGHT_PAREN, "Expect ')' after expression.");
            return new Grouping(expr);
        }

        try {
            throw error(peek(), "Expect expression.");
        } catch (ParseError e) {
            e.printStackTrace();
        }
        return null;
    }

    private void synchronize(){
        nextToken();
        while(!isEnd()){
            if(previous().getType() == compile.core.Lexer.RegExp.LexemType.SEMICOLON) return;

            switch (peek().getType()){
                case INT:
                case STRING:
                case FOR:
                case IF:
                case WHILE:
                case PRINT:
                    return;
            }

            nextToken();
        }
    }

    private Token consume(compile.core.Lexer.RegExp.LexemType type, String message) {
        if(check(type))
            return nextToken();
        try {
            throw new ParseException(peek(), message);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ParseError error(Token token, String message)
    {
        Log.Error(token, message);
        return new ParseError();
    }

    private boolean isEnd(){
        return peek().getType() == compile.core.Lexer.RegExp.LexemType.EOF;
    }

    private Token peek(){
        return this.tokens.get(iterator);
    }

    private Token previous(){
        return this.tokens.get(iterator - 1);
    }

    private boolean match(compile.core.Lexer.RegExp.LexemType[] types){
        for(compile.core.Lexer.RegExp.LexemType lexemType : types){
            if(check(lexemType)){
                nextToken();
                return true;
            }
        }
        return false;
    }

    private boolean match(compile.core.Lexer.RegExp.LexemType type){
        if(check(type)){
            nextToken();
            return true;
        }
        return false;
    }

    private Token nextToken(){
        if(!isEnd())
            ++this.iterator;
        return previous();
    }

    private boolean check(compile.core.Lexer.RegExp.LexemType tokenType){
        if(isEnd()) return false;
        return peek().getType() == tokenType;
    }


}
