package vector.container;

import vector.Vector;

import java.util.*;

/**
 * Created by Dima on 14.11.2015.
 */
public class VectorMap implements Map {

    class Entry implements Map.Entry{
        private Object key;
        private Vector value;

        Entry(Object key, Object value){
            this.key = key;
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

    public Entry[] data = new Entry[0];

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
            if((key == null && element.getKey() == null) ||
                    (key != null && element.getKey().equals(key))) return true;
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(Entry element : data)
            if((value == null && element.getValue() == null) ||
                    (value != null && value.equals(element.value))) return true;
        return false;
    }

    @Override
    public Object get(Object key) {
        for(Entry element : data)
            if((key == null && element.getKey() == null) ||
                    (key != null && element.getKey().equals(key))) return element.getValue();
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        for(Entry element : data) {
            if ((key == null && element.getKey() == null) ||
                    (key != null && key.equals(element.key))) {
                Vector oldValue = element.value;
                element.value = (Vector) value;
                return oldValue;
            }
        }
        data = Arrays.copyOf(data, data.length + 1);
        data[data.length-1] = new Entry(key, value);
        return null;
    }

    @Override
    public Object remove(Object key) {
        Entry[] newData = new Entry[data.length-1];
        Object oldValue;
        for (int i = 0; i < size(); i++)
            if((key == null && data[i].getKey() == null) || key != null && key.equals(data[i].getKey())){
                oldValue = data[i].getValue();
                System.arraycopy(data, 0, newData, 0, i);
                System.arraycopy(data, i+1, newData, i, size()-i-1);
                data = newData;
                return oldValue;
            }
        return null;
    }

    @Override
    public void putAll(Map m) {
        Set<Entry> incoming = m.entrySet();
        for(Entry element : incoming){
            this.put(element.getKey(), element.getValue());
        }
    }

    @Override
    public void clear() {
        data = new Entry[0];
    }

    @Override
    public Set keySet() {
        Set<Object> set = new HashSet<>();
        for(Entry element : data)
            set.add(element.getKey());
        return set;
    }

    @Override
    public Collection values() {
        Collection collection = new VectorCollection();
        for(Entry element : data)
            collection.add(element.getValue());
        return collection;
    }

    @Override
    public Set<Entry> entrySet() {
        Set<Entry> set = new HashSet<Entry>();
        for(Entry element : data)
            set.add(element);
        return set;
    }
}
