package vector.factory;

import vector.Vector;
import vector.impl.ArrayVector;

/**
 * Created by Dima on 30.11.2015.
 */
public class ArrayVectorFactory implements VectorFactory {
    @Override
    public Vector getNewVector() {
        return new ArrayVector(0);
    }

    @Override
    public Vector getNewVector(int size) {
        return new ArrayVector(size);
    }
}
