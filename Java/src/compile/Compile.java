package compile;


import compile.core.Lexer.Lexer;
import compile.core.Parser.Parser;
import compile.core.Token.Token;
import compile.utils.ParseltongueProg;

import java.util.Scanner;

public class Compile {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        ParseltongueProg prog = new ParseltongueProg();
        Scanner console = new Scanner(System.in);
        String code = console.nextLine();
//        String way = "D:\\home\\Mirea\\2years\\4s\\Компилятор\\Java\\src\\compile\\console.txt";
//        File f = new File(way);
//        try(FileReader fr = new FileReader(f)){
//            BufferedReader reader = new BufferedReader(fr);
//            script = reader.readLine();
//        }
//        catch(IOException ex){
//            System.out.println(ex.getMessage());
//        }
        lexer.lexAnalysis(code);
        for (Token token : lexer.getTokens()){
            System.out.println(token);
        }

        Parser parser = new Parser(lexer.getTokens());
        Interpreter interpreter = new Interpreter();
        interpreter.interpret(parser.parse());
        System.out.println("///////");
    }

}