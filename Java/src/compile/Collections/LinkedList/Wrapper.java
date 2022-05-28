package compile.Collections.LinkedList;

public class Wrapper<T> {
    private T item;
    private Wrapper<T> prev;
    private Wrapper<T> next;

    public Wrapper(T item){
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public Wrapper<T> getNext() {
        return next;
    }

    public Wrapper<T> getPrev() {
        return prev;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public void setNext(Wrapper<T> next) {
        this.next = next;
    }

    public void setPrev(Wrapper<T> prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "Wrapper{" +
                "value=" + item +
                '}';
    }
}
