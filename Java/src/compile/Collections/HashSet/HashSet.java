package compile.Collections.HashSet;

import compile.Collections.LinkedList.LinkedList;

import java.util.ArrayList;

public class HashSet<T> {
    private Integer size;
    private ArrayList<LinkedList<T>> buckets;

    public HashSet(){
        size = 4;
        buckets = new ArrayList<LinkedList<T>>();
        for(int i = 0; i < size; ++i)
            buckets.add(new LinkedList<T>());
    }

    public void put(T item) {
        if (buckets.get(Math.abs(item.hashCode()) % this.size).isEmpty() || !buckets.get(Math.abs(item.hashCode()) % this.size).isValueExist(item))
            buckets.get(Math.abs(item.hashCode()) % this.size).add(item);
    }

    public boolean contains(T item) {
        return !buckets.get(Math.abs(item.hashCode()) % this.size).isEmpty() && buckets.get(Math.abs(item.hashCode()) % this.size).isValueExist(item);
    }

    public void remove(T item){
        if (!buckets.get(Math.abs(item.hashCode()) % this.size).isEmpty() && buckets.get(Math.abs(item.hashCode()) % this.size).isValueExist(item))
            buckets.get(Math.abs(item.hashCode()) % this.size).remove(item);
    }

    @Override
    public String toString() {
        String result = "";
        for(LinkedList bucket : this.buckets)
            result += bucket.toString();

        return "HashSet{" + result + '}';
    }
}
