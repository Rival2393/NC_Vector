package vector.container.generics;

import vector.Vector;

import java.util.*;

/**
 * Created by Dima on 22.11.2015.
 */
public class VectorMap<K, V extends Vector> implements Map<K, V>{
    class Entry<K, V> implements Map.Entry<K, V>{
        private K key;
        private V value;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
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
        for(Entry<K, V> element : data)
            if((key == null && element.getKey() == null) ||
                    (key != null && element.getKey().equals(key))) return true;
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(Entry<K, V> element : data)
            if((value == null && element.getValue() == null) ||
                    (value != null && value.equals(element.value))) return true;
        return false;
    }

    @Override
    public V get(Object key) {
        for(Entry<K, V> element : data)
            if((key == null && element.getKey() == null) ||
                    (key != null && element.getKey().equals(key))) return element.getValue();
        return null;
    }

    @Override
    public V put(K key, V value) {
        for(Entry<K, V> element : data) {
            if ((key == null && element.getKey() == null) ||
                    (key != null && key.equals(element.key))) {
                V oldValue = element.value;
                element.value = value;
                return oldValue;
            }
        }
        data = Arrays.copyOf(data, data.length + 1);
        data[data.length-1] = new Entry(key, value);
        return null;
    }

    @Override
    public V remove(Object key) {
        Entry[] newData = new Entry[data.length-1];
        Object oldValue;
        for (int i = 0; i < size(); i++)
            if((key == null && data[i].getKey() == null) || key != null && key.equals(data[i].getKey())){
                oldValue = data[i].getValue();
                System.arraycopy(data, 0, newData, 0, i);
                System.arraycopy(data, i+1, newData, i, size()-i-1);
                data = newData;
                return (V) oldValue;
            }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> element : m.entrySet()) {
            put(element.getKey(), element.getValue());
        }
    }

    @Override
    public void clear() {
        data = new Entry[0];
    }

    @Override
    public Set keySet() {
        Set<K> set = new HashSet<>();
        for(Entry<K, V> element : data)
            set.add(element.getKey());
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection collection = new VectorCollection();
        for(Entry<K, V> element : data)
            collection.add(element.getValue());
        return collection;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for(Entry<K, V> element : data)
            set.add(element);
        return set;
    }
}
