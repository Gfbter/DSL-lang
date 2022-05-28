package compile.core;

public class ReturnException extends RuntimeException{
    public Object value;

    public ReturnException(Object value)
    {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
