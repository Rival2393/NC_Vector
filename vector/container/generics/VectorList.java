package vector.container.generics;

import vector.Vector;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Dima on 22.11.2015.
 */
public class VectorList<E extends Vector> extends VectorCollection<E> implements List<E> {

    public boolean addAll(int index, Collection<? extends E> c) {
        Vector[] arr = (Vector[]) c.toArray();
        if(arr.length == 0) return false;
        for(int i = 0; i < arr.length; i++)
            this.add(index++, (E) arr[i]);
        return true;
    }

    public E get(int index) {
        if (index < 0 || index > data.length)
            throw new IndexOutOfBoundsException();
        else return (E) data[index];
    }

    public E set(int index, E element) {
        if (index < 0 || index > data.length)
            throw new IndexOutOfBoundsException();
        else {
            Object old = data[index];
            data[index] = element;
            return (E) old;
        }
    }

    public void add(int index, E element) {
        if (index < 0 || index > data.length)
            throw new IndexOutOfBoundsException();
        Vector[] newData = new Vector[this.size() + 1];
        System.arraycopy(data, 0, newData, 0, index);
        newData[index] = element;
        System.arraycopy(data, index, newData, index + 1, this.size() - index);
        data = newData;
    }

    public E remove(int index) {
        if(index < 0 || index > data.length)
            throw new IndexOutOfBoundsException();
        Object removed = data[index];
        Vector[] newData = new Vector[data.length-1];
        System.arraycopy(data, 0, newData, 0, index);
        System.arraycopy(data, index+1, newData, index, newData.length-index);
        data = newData;
        return (E) removed;
    }

    public int indexOf(Object o) {
        if (!(o instanceof Vector || o == null))
            throw new IllegalArgumentException();
        else {
            for(int i = 0; i < data.length; i++) {
                if(o == null && data[i] == null) return i;
                else if(o != null && o.equals(data[i])) return i;
            }
            return -1;
        }
    }

    public int lastIndexOf(Object o) {
        if (!(o instanceof Vector || o == null))
            throw new IllegalArgumentException();
        else {
            int lastPos = -1;
            for(int i = 0; i < data.length; i++){
                if(o == null && data[i] == null) lastPos = i;
                else if (o != null && o.equals(data[i])) lastPos = i;
            }
            return lastPos;
        }
    }

    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    public List subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > data.length || toIndex < 0 || toIndex > data.length)
            throw new IndexOutOfBoundsException();
        List sub = new VectorList();
        for(int i = fromIndex; i < toIndex; i++)
            sub.add(data[i]);
        return sub;
    }
}
