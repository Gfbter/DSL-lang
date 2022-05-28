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

import java.util.*;

public class Parser {
    private List<Token> tokens = new LinkedList<Token>();
    private Integer iterator = 0;

    private static class ParseError extends RuntimeException {}

    public Parser(List<Token> tokens){
        this.tokens = tokens;
    }

    public LinkedList<Stmt> parse(){
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
        if(match(LexemType.ASSIGN)){
            initializer = expression();
        }
        consume(compile.core.Lexer.RegExp.LexemType.SEMICOLON, "Expect ';' after variable declaration.");
        return new Var(name, initializer);
    }

    private Stmt statement(){
        if(match(LexemType.FOR))
            return forStatement();
        if(match(LexemType.IF))
            return ifStatement();
        if(match(LexemType.PRINT))
            return printStatement();
//        if(match(LexemType.RETURN))
//            return returnStatement();
        if(match(LexemType.WHILE))
            return whileStatement();
        if(match(LexemType.LEFT_BRACE))
            return new Block(block());

        return expressionStatement();
    }

    private Stmt forStatement()
    {
        consume(compile.core.Lexer.RegExp.LexemType.LEFT_PAREN, "Expect '(' after 'for'.");

        Stmt initializer;
        if (match(LexemType.SEMICOLON)){
            initializer = null;
        }
        else if(match(new LexemType[] {LexemType.INT, LexemType.STRING})){
            initializer = varDeclaration();
        }
        else{
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

        if (condition == null)
            condition = new Primary(1);
        body = new While(condition, body);

        if (initializer != null)
        {
            body = new Block(Arrays.asList(initializer, body));
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

//    private Stmt returnStatement(){
//        Token keyword = previous();
//        Expr value = null;
//        if(!check(LexemType.SEMICOLON)){
//            value = expression();
//        }
//        consume(LexemType.SEMICOLON, "Expect ';' after return value.");
//        return new Return(keyword, value);
//    }

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

//    private Function function(){
//        Token name = consume(LexemType.VARIABLE, "Expect function name.");
//        consume(LexemType.VARIABLE, "Expect '(' after function name.");
//        LinkedList<Token> parameters = new LinkedList<Token>();
//        if(!check(LexemType.RIGHT_PAREN)){
//            do {
//                parameters.add(consume(LexemType.VARIABLE, "Expect parameter name."));
//            } while(match(LexemType.COMMA));
//        }
//        consume(LexemType.RIGHT_PAREN, "Expect ')' after parameters.");
//        consume(LexemType.LEFT_BRACE, "Expect '{' before function body.");
//        LinkedList<Stmt> body = block();
//        return new Function(name, parameters, body);
//    }

    private Expr expression() {
        return assignment();
    }

    private Expr assignment(){
        Expr expr = or();

            if (match(LexemType.ASSIGN))
            {
                Token equals = previous();
                Expr value = assignment();

                if (expr instanceof Variable) {
                Token name = ((Variable)expr).getName();
                return new Assign(name, value);
            }

            error(equals, "Invalid assignment target.");
        }

        return expr;
    }

    private Expr or(){
        Expr expr = and();

        while(match(compile.core.Lexer.RegExp.LexemType.OR)){
            expr = new Or(expr, previous(), and());
        }
        return expr;
    }

    private Expr and(){
        Expr expr = equality();

        while(match(LexemType.AND)){
            expr = new And(expr, previous(), equality());
        }

        return expr;
    }

    private Expr equality(){
        Expr expr = comparison();

        while(match(new LexemType[] { LexemType.NOT_EQUAL, LexemType.EQUAL})){
            Token operation = previous();
            Expr right = comparison();
            switch(previous().getType()){
                case NOT_EQUAL: expr = new notEqual(expr, operation, right);
                case EQUAL: expr = new Equal(expr, operation, right);
            }
        }
        return expr;
    }

    private Expr comparison(){
        Expr expr = term();

        while(match(new compile.core.Lexer.RegExp.LexemType[]{ compile.core.Lexer.RegExp.LexemType.GREATER, compile.core.Lexer.RegExp.LexemType.GREATER_EQUAL, compile.core.Lexer.RegExp.LexemType.LESS, compile.core.Lexer.RegExp.LexemType.LESS_EQUAL})){
            switch(previous().getType()){
                case GREATER -> expr = new Greater(expr, previous(), term());
                case GREATER_EQUAL -> expr = new GreaterEqual(expr, previous(), term());
                case LESS -> expr = new Less(expr, previous(), term());
                case LESS_EQUAL -> expr = new LessEqual(expr, previous(), term());
            }
        }
        return expr;
    }

    private Expr term(){
        Expr expr = factor();

        while(match(new compile.core.Lexer.RegExp.LexemType[] {compile.core.Lexer.RegExp.LexemType.MINUS, compile.core.Lexer.RegExp.LexemType.PLUS})){
            switch(previous().getType()){
                case MINUS -> expr = new Subtraction(expr, previous(), unary());//unary?? mb term()
                case PLUS -> expr = new Additional(expr, previous(), unary());
            }
        }

        return expr;
    }

    private Expr factor(){
        Expr expr = unary();

        while(match(new LexemType[]{ LexemType.DIVISION, LexemType.MOD, LexemType.MULT})){
            switch(previous().getType()) {
                case DIVISION -> expr = new Division(expr, previous(), unary());
                case MOD -> expr = new Module(expr, previous(), unary());
                case MULT -> expr = new Multiplication(expr, previous(), unary());
            }
        }

        return expr;
    }

    private Expr unary(){
        if(match(new compile.core.Lexer.RegExp.LexemType[] {compile.core.Lexer.RegExp.LexemType.NOT, compile.core.Lexer.RegExp.LexemType.MINUS})){
            Expr result = null;
            switch(previous().getType()){
                case NOT -> result = new Inversion(previous(), unary());
                case MINUS -> result = new UnaryMinus(previous(), unary());
            }
            return result;
        }

        return call();
    }

    private Expr call(){
        Expr expr = primary();
        while(match(LexemType.LEFT_PAREN)){
            expr = finishCall(expr);
        }

        return expr;
    }

    private Expr finishCall(Expr caller){
        LinkedList<Expr> arguments = new LinkedList<Expr>();
        if(!check(LexemType.RIGHT_PAREN)){
            do{
                arguments.add(expression());
            }while (match(LexemType.COMMA));
        }
        Token paren = consume(LexemType.RIGHT_PAREN, "Expect ')' after arguments.");

        return new Call(caller, paren, arguments);
    }

    private Expr primary()
    {
        if(match(LexemType.DIGIT)){
            return new Primary(Integer.parseInt(previous().getValue()));
        }

        if(match(LexemType.VARIABLE)){
            return new Variable(previous());
        }

        if(match(LexemType.TEXT)){
            return new Primary(previous().getValue());
        }

        if(match(LexemType.LEFT_PAREN))
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
