package compile.Collections.LinkedList;

public class Wrapper<T> {
    T value;
    Wrapper prev;
    Wrapper next;

    public Wrapper(T item){
        this.value =item;
        this.prev = null;
        this.next = null;
    }

    public Wrapper(Wrapper<T> item){
        this.value = item.value;
        this.next = item.next;
        this.prev = item.prev;
    }

    public Wrapper(){
        this.value = null;
        this.next = null;
        this.prev = null;
    }

    public T getItem() {
        return value;
    }

    public Wrapper getNext() {
        return next;
    }

    public Wrapper getPrev() {
        return prev;
    }

    public void setItem(T item) {
        this.value = item;
    }

    public void setNext(Wrapper next) {
        this.next = next;
    }

    public void setPrev(Wrapper prev) {
        this.prev = prev;
    }
}
