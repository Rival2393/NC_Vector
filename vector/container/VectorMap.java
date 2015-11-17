package vector.container;

import vector.Vector;
import vector.impl.ArrayVector;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dima on 14.11.2015.
 */
public class VectorMap implements Map {

    class Entry implements Map.Entry{
        int key;
        Vector value;

        Entry(Object key, Object value){
            this.key = (int) key;
            this.value = (Vector) value;
        }

        @Override
        public Object getKey() {
            return this.key;
        }

        @Override
        public Object getValue() {
            return this.value;
        }

        @Override
        public Object setValue(Object value) {
            Vector oldValue = this.value;
            this.value = (Vector) value;
            return oldValue;
        }
    }

    Entry[] data = new Entry[0];

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
    public boolean containsKey(Object key) {
        for(Entry element : data)
            if(key.equals(element.key)) return true;
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(Entry element : data){
            if(value == null && element.value == null) return true;
            else if(value != null && value.equals(element.value)) return true;
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        for(Entry element : data)
            if(key.equals(element.key)) return element.value;
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        for(Entry element : data)
            if(key.equals(element.key)){
                Vector oldValue = element.value;
                element.value = (Vector) value;
                return oldValue;
            }
        data = Arrays.copyOf(data, data.length + 1);
        data[data.length-1] = new Entry(key, value);
        return null;
    }

    @Override
    public Object remove(Object key) {
        if(this.containsKey(key)){
            Entry[] newData = new Entry[data.length-1];
            Vector oldValue = new ArrayVector(0);
            for(int i = 0; i < data.length; i++){
                System.arraycopy(data, 0, newData, 0, i);
                if(key.equals(data[i].key)){
                    oldValue = (Vector) data[i].value.clone();
                    System.arraycopy(data, i+1, data, i, size() - i - 1);
                }
                else newData[i] = data[i];
            }
            return oldValue;
        }
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {
        data = new Entry[0];
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
