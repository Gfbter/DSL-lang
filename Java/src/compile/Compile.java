package compile;


import compile.core.Lexer.Lexer;
import compile.core.Token.Token;
import compile.utils.ParseltongueProg;

public class Compile {
    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        ParseltongueProg prog = new ParseltongueProg();
//        String way = "D:\\home\\Mirea\\2years\\4s\\Компилятор\\Java\\src\\compile\\console.txt";
//        File f = new File(way);
//        try(FileReader fr = new FileReader(f)){
//            BufferedReader reader = new BufferedReader(fr);
//            script = reader.readLine();
//        }
//        catch(IOException ex){
//            System.out.println(ex.getMessage());
//        }

        for (String source: prog.sources) {
            lexer.lexAnalysis(source);
            for (Token token : lexer.getTokens()) {
                System.out.println(token);
            }
            System.out.println("///////");
        }

    }
}