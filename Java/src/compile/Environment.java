package compile;

import compile.core.RuntimeError;
import compile.core.Token.Token;

import java.util.Hashtable;

public class Environment {
    Environment enclosing;
    private Hashtable<String, Object> values = new Hashtable<String, Object>();

    public Environment(){
        this.enclosing = null;
    }

    public Environment(Environment enclosing){
        this.enclosing = enclosing;
    }

    public Object Get(Token token){
        if(this.values.containsKey(token.getValue())){
            return this.values.get(token.getValue());
        }

        if(this.enclosing != null)
            return this.enclosing.Get(token);

        throw new RuntimeError(token, "Undefined variable '" + token.getType() + "'.");
    }

    public void Assign(Token token, Object value){
        if(this.values.containsKey(token.getValue())){
            this.values.put(token.getValue(), value);
            return;
        }

        if(this.enclosing != null){
            this.enclosing.Assign(token, value);
            return;
        }

        throw new RuntimeError(token, "Undefined variable '" + token.getType() + "'.");

    }

    public boolean VarExists(String key){
        return this.values.containsKey(key);
    }

    public void Define(String key, Object value){
        this.values.put(key, value);
    }

    public Environment Ancestor(int level){
        Environment environment = this;
        for(int i = 0; i < level; i++){
            environment = environment.enclosing;
        }

        return environment;
    }

    public Object GetAt(int level, String name){
        return Ancestor(level).values.get(name);
    }

    public String toString() {
        String result = this.values.toString();
        if(this.enclosing != null){
            result += " -> " + this.enclosing.toString();
        }
        return result;
    }
}
