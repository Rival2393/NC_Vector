package vector.container;

import vector.Vector;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Dima on 28.10.2015.
 */
public class VectorList extends VectorCollection implements List
{
    public boolean addAll(int index, Collection c) {
        if (c instanceof VectorList)
        {
            Vector[] arr = (Vector[]) c.toArray();
            for(int i = 0; i < arr.length; i++)
                this.add(index++, arr[i]);
            return true;
        }
        return false;
    }

    public Object get(int index) {
        if (index < 0 || index > data.length)
            throw new IndexOutOfBoundsException();
        else return data[index];
    }

    public Object set(int index, Object element) {
        if (index < 0 || index > data.length)
            throw new IndexOutOfBoundsException();
        if (!(element instanceof Vector || element == null))
            throw new IllegalArgumentException();
        else {
            Vector old = data[index];
            data[index] = (Vector) element;
            return old;
        }
    }

    public void add(int index, Object element) {
        if (!(element == null || element instanceof Vector))
            throw new IllegalArgumentException();
        if (index < 0 || index > data.length)
            throw new IndexOutOfBoundsException();
        Vector[] newData = new Vector[this.size() + 1];
        System.arraycopy(data, 0, newData, 0, index);
        newData[index] = (Vector) element;
        System.arraycopy(data, index, newData, index + 1, this.size() - index);
        data = newData;
    }

    public Object remove(int index) {
        if(index < 0 || index > data.length)
            throw new IndexOutOfBoundsException();
        Vector removed = data[index];
        Vector[] newData = new Vector[data.length-1];
        System.arraycopy(data, 0, newData, 0, index);
        System.arraycopy(data, index+1, newData, index, newData.length-index);
        data = newData;
        return removed;
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
