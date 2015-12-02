package vector.factory;

import vector.Vector;
import vector.impl.LinkedVector;

/**
 * Created by Dima on 30.11.2015.
 */
public class LinkedVectorFactory implements VectorFactory {
    @Override
    public Vector getNewVector() {
        return new LinkedVector(0);
    }

    @Override
    public Vector getNewVector(int size) {
        return new LinkedVector(size);
    }
}
