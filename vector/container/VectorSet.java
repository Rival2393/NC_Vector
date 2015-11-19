package vector.container;

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
}
