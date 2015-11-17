package vector.container;

import vector.Vector;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Dima on 14.11.2015.
 */
public class VectorSet extends VectorCollection implements Set {

    public boolean add(Object o) {
        if (!this.contains(o)){
            return super.add(o);
        }
        return false;
    }

    public boolean addAll(Collection c){
        Vector[] arr = (Vector[]) c.toArray();
        int counter = 0;
        for(Vector element : arr){
            this.add(element);
            counter++;
        }
        if(counter > 0)return true;
        else return false;
    }
}
