package vector.container;

import vector.Vector;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Dima on 28.10.2015.
 */
public class VectorCollection implements Collection
{
    Vector[] data = new Vector[0];

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public boolean isEmpty() {
        if(data.length > 0) return false;
        else return true;
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Vector || o == null))
            throw new IllegalArgumentException();
        for(Vector element : data) {
            if(o == null && element == null)
                return true;
            else if (o != null && o.equals(element))
                return true;
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
        //return null;
    }

    @Override
    public Object[] toArray() {
        Vector[] arr = new Vector[data.length];
        for(int i = 0; i < data.length; i++){
            arr[i] = data[i];
        }
        return arr;
    }

    @Override
    public Object[] toArray(Object[] a) {
        if(a instanceof Vector[] && a.length >= data.length)
            System.arraycopy(data, 0, a, 0, data.length);
        else
            a = data.clone();
        return a;
    }

    @Override
    public boolean add(Object o) {
        if (o instanceof Vector || o == null) {
            data = Arrays.copyOf(data, data.length + 1);
            data[data.length - 1] = (Vector) o;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Vector || o == null){
            for (int i = 0; i < size(); i++)
                if((o == null && data[i] == null) || (o != null && o.equals(data[i]))){
                    Vector[] newData = new Vector[size()-1];
                    System.arraycopy(data, 0, newData, 0, i);
                    System.arraycopy(data, i+1, newData, i, size()-i-1);
                    data = newData;
                    return true;
                }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        Object[] arr = c.toArray();
        int counter = 0;
        for(int i = 0; i < arr.length; i++)
            if(this.contains(arr[i])) counter++;
        if(counter == arr.length) return true;
        else return false;
    }

    @Override
    public boolean addAll(Collection c) {
        Object[] arr = c.toArray();
        int counter = 0;
        for(Object element : arr){
            this.add(element);
            counter++;
        }
        if(counter > 0)return true;
        else return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        Vector[] arr = (Vector[]) c.toArray();
        boolean result = false;
        for(Vector element : arr)
            if(remove(element)) result = true;
        return result;
    }

    @Override
    public boolean retainAll(Collection c) {
        int counter = 0;
        for(int i = 0; i < data.length; i++){
            if(!c.contains(data[i]))
                if(remove(data[i])) counter++;
        }
        return (counter>0);
    }

    @Override
    public void clear() {
        data = new Vector[0];
    }
}
