package vector.factory;

import vector.Vector;

/**
 * Created by Dima on 30.11.2015.
 */
public interface VectorFactory {
    Vector getNewVector();
    Vector getNewVector(int size);
}
