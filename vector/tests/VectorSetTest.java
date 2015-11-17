package vector.tests;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import vector.container.VectorCollection;
import vector.container.VectorSet;
import vector.impl.ArrayVector;

/**
 * Created by Dima on 14.11.2015.
 */
public class VectorSetTest {

    ArrayVector vector1;
    ArrayVector vector2;
    ArrayVector vector3;
    ArrayVector vector4;

    double[] arr1;
    double[] arr2;
    double[] arr3;
    double[] arr4;

    VectorSet collection;

    @Before
    public void setUp() throws Exception {
        vector1 = new ArrayVector(0);
        vector2 = new ArrayVector(0);
        vector3 = new ArrayVector(0);
        vector4 = new ArrayVector(0);

        arr1 = new double[]{1.1, 2.2, 3.3, 4.4, 5.5};
        arr2 = new double[]{6.6, 7.7, 8.8, 9.9, 10.0};
        arr3 = new double[]{11.1, 12.2, 13.3, 14.4, 15.5};
        arr4 = new double[]{16.6, 17.7, 18.8, 19.9, 20.0};

        vector1.fillFromMass(arr1);
        vector2.fillFromMass(arr2);
        vector3.fillFromMass(arr3);
        vector4.fillFromMass(arr4);
    }

    @Test
    public void testAdd() throws Exception {
        collection = new VectorSet();
        Assert.assertTrue(collection.add(vector1));
        Assert.assertTrue(collection.add(vector2));
        Assert.assertTrue(collection.add(null));
        Assert.assertTrue(collection.contains(vector1));
        Assert.assertTrue(collection.contains(vector2));
        Assert.assertTrue(collection.contains(null));
        Assert.assertTrue(!collection.add(vector1));
        Assert.assertTrue(!collection.add(vector2));
        Assert.assertTrue(!collection.add(null));
        System.out.println("Test add: done");
        System.out.println();
    }

    @Test
    public void testAddAll() throws Exception {
        collection = new VectorSet();
        VectorCollection toAdd = new VectorCollection();
        toAdd.add(vector1);
        toAdd.add(vector2);
        toAdd.add(vector3);
        toAdd.add(null);
        Assert.assertTrue(collection.addAll(toAdd));
        Assert.assertTrue(collection.containsAll(toAdd));
        Assert.assertTrue(collection.size() == 4);
        toAdd.clear();
        toAdd.add(vector4);
        toAdd.add(vector2);
        Assert.assertTrue(collection.addAll(toAdd));
        Assert.assertTrue(collection.size() == 5);
        System.out.println("Test addAll: done");
        System.out.println();
    }
}