package compile.Collections.LinkedList;

public class LinkedList<T> {
    private Wrapper<T> head;
    private Wrapper<T> end;
    private Wrapper<T> iterator;
    int size;

    public LinkedList(){
        this.head = null; // first
        this.end = null;  // last
        this.size = 0;
    }

    public int getSize() { return size; }

    public boolean isEmpty(){ return size == 0;  } // return head == null && end == null;

    public Wrapper<T> get(int index){
        int i = 0;
        this.iterator = this.head;
        while(this.iterator.getNext() != null && i != index){
            this.iterator = this.iterator.getNext();
            ++i;
        }
        return this.iterator;
    }

    public boolean isValueExist(T value){
        this.iterator = this.head;
        Wrapper<T> Node = new Wrapper<T>(value);
        while(this.iterator.getNext() != null && this.iterator.getItem() != value){
            this.iterator = this.iterator.getNext();
        }
        return this.iterator.getItem() == Node.getItem();
    }

    public void insert(int index, T item){
        this.iterator = this.head;
        Wrapper<T> Node = new Wrapper<T>(item);
        int i = 0;

        while(this.iterator.getNext() != null && i != index){
            this.iterator = this.iterator.getNext();
            ++i;
        }

        ++this.size;

        if (index >= this.size - 1 && iterator.getNext() == null){
            this.iterator.setNext(Node);
            Node.setPrev(this.iterator);
            this.end = this.iterator.getNext();
            return;
        }

        if (index == 0) {
            this.iterator.setPrev(Node);
            Node.setNext(this.iterator);
            this.head = Node;
            return;
        }

        Node.setNext(this.iterator);
        Node.setPrev(this.iterator.getPrev());
        this.iterator.getPrev().setNext(Node);
        this.iterator.setPrev(Node);
    }

    public void remove(int index){
        this.iterator = this.head;
        int i = 0;

        while(this.iterator.getNext() != null && i != index){
            this.iterator = this.iterator.getNext();
            ++i;
        }

        --this.size;

        if (index >= this.size - 1 && iterator.getNext() == null && this.size == 0){
            this.end = null;
            this.head = null;
            return;
        }

        if (index >= this.size - 1 && iterator.getNext() == null){
            this.end = this.end.getPrev();
            this.end.setNext(null);
            return;
        }

        if (index == 0) {
            this.head = this.head.getNext();
            this.head.setPrev(null);
            return;
        }

        this.iterator.getPrev().setNext(this.iterator.getNext());
        this.iterator.getNext().setPrev(this.iterator.getPrev());
    }

    public void remove(T item){
        this.iterator = this.head;
        int i = 0;
        Wrapper<T> Node = new Wrapper<T>(item);
        while(this.iterator.getNext() != null && iterator.getItem() != item){
            this.iterator = this.iterator.getNext();
            ++i;
        }

        if (this.iterator.getItem() == item)
            remove(i);

    }

    public void add(T item){
        if(size == 0) {
            head = new Wrapper<T>(item);
            end = head;
            ++this.size;
        }
        else
            insert(this.size,  item);
    }

    public String toString() {
        String result = "";
        this.iterator = this.head;
        while(this.iterator != null){
            result += this.iterator.toString();
        }
        return "LinkedList{" + result + '}';
    }
}
