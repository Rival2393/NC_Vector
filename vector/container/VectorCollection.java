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
        if(a instanceof Vector[]){
            for (int i = 0; i < data.length; i++)
                a[i] = data[i];
            return a;
        }
        else{
            a = new Vector[data.length];
            for (int i = 0; i < data.length; i++)
                a[i] = data[i];
            return a;
        }
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
            if(this.contains(o)){
                Vector[] temp = new Vector[data.length-1];
                for (int i = 0; i < temp.length; i++){
                    if(o != data[i])
                        temp[i] = data[i];
                    else if (o == data[i]) {
                        for(int j = i+1; j < data.length; i++, j++)
                            temp[i] = data[j];
                        break;
                    }
                }
                data = temp.clone();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        Vector[] arr = (Vector[]) c.toArray();
        int counter = 0;
        for(int i = 0; i < arr.length; i++)
            if(this.contains(arr[i])) counter++;
        if(counter == arr.length) return true;
        else return false;
    }

    @Override
    public boolean addAll(Collection c) {
        Object[] arr = c.toArray();
        boolean res = false;
        for(Object element : arr)
            res = this.add(element);
        return res;
    }

    @Override
    public boolean removeAll(Collection c) {
        if (c == null || c instanceof VectorCollection){
            Vector[] arr = (Vector[]) c.toArray();
            VectorCollection newOne = new VectorCollection();
            for(int i = 0; i < data.length; i++)
                for(int j = 0; j < arr.length; j++){
                    if(arr[j] == null && arr[j] == data[i]) break;
                    else if (arr[j] !=null && arr[j].equals(data[i])) break;
                    else{
                        if(j == arr.length-1) newOne.add(data[i]);
                    }
                }
            data = (Vector[]) newOne.toArray();
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        if (c == null || c instanceof VectorCollection){
            Vector[] arr = (Vector[]) c.toArray();
            VectorCollection newOne = new VectorCollection();
            for(int i = 0; i < data.length; i++)
                for(int j = 0; j < arr.length; j++){
                    if(arr[j] == null && data[i] == null) newOne.add(data[i]);
                    else if(arr[j] != null && arr[j].equals(data[i])) newOne.add(data[i]);
                }
            data = (Vector[]) newOne.toArray();
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        data = new Vector[0];
    }
}
