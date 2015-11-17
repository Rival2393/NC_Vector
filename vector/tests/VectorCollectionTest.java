package vector.tests;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import vector.container.VectorCollection;
import vector.impl.ArrayVector;

/**
 * Created by Dima on 04.11.2015.
 */
public class VectorCollectionTest {


    ArrayVector vector1;
    ArrayVector vector2;
    ArrayVector vector3;
    ArrayVector vector4;

    double[] arr1;
    double[] arr2;
    double[] arr3;
    double[] arr4;

    VectorCollection collection;


    @Before
    public void setUp(){
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

        collection = new VectorCollection();
        collection.add(vector1);
        collection.add(vector2);
        collection.add(vector3);
        collection.add(vector4);
        collection.add(null);
    }

    @Test
    public void testSize(){
        int expected = 5;
        int real = collection.size();
        Assert.assertTrue(expected == real);
        VectorCollection col = new VectorCollection();
        expected = 0;
        real = 0;
        Assert.assertTrue(expected == real);
        System.out.println("Test size: done");
        System.out.println();
    }

    @Test
    public void testIsEmpty(){
        boolean expected = false;
        boolean result = collection.isEmpty();
        Assert.assertEquals(expected, result);
        VectorCollection col = new VectorCollection();
        result = col.isEmpty();
        Assert.assertFalse(expected == result);
        System.out.println("Test isEmpty: done");
        System.out.println();
    }

    @Test
    public void testContains(){
        boolean expected = true;
        boolean result = collection.contains(vector3);
        Assert.assertEquals(expected, result);
        result = collection.contains(null);
        Assert.assertEquals(expected, result);
        System.out.println("Test contains: done");
        System.out.println();
    }

    @Test
    public void testToArray(){
        Object[] expected = new Object[collection.size()];
        expected[0] = vector1;
        expected[1] = vector2;
        expected[2] = vector3;
        expected[3] = vector4;
        expected[4] = null;
        Object[] result;
        result = collection.toArray();
        for(int i = 0; i < expected.length; i++)
            Assert.assertEquals(expected[i], result[i]);
        System.out.println("Test toArray: done");
        System.out.println();
    }


    @Test
    public void testAdd(){
        double[] arr5 = new double[]{7.7, 7.7, 7.7};
        ArrayVector vector5 = new ArrayVector(0);
        vector5.fillFromMass(arr5);
        collection.add(vector5);
        Object[] mass;
        mass = collection.toArray();
        Assert.assertEquals(mass[mass.length-1], vector5);
        ArrayVector vector6 = null;
        collection.add(vector6);
        mass = collection.toArray();
        Assert.assertEquals(mass[mass.length-1], vector6);
        System.out.println("Test add: done");
        System.out.println();
    }

    @Test
    public void testRemove(){
        collection.remove(vector2);
        int size = collection.size();
        Assert.assertEquals(size, 4);
        Object[] mass;
        mass = collection.toArray();
        for (Object o : mass)
            System.out.println(o);
        Assert.assertEquals(mass[1], vector3);
        collection.remove(null);
        size = collection.size();
        mass = collection.toArray();
        for (Object o : mass)
            System.out.println(o);
        Assert.assertEquals(size, 3);
        System.out.println("Test remove: done");
        System.out.println();
    }

    @Test
    public void testContainsAll(){
        VectorCollection some = new VectorCollection();
        some.add(vector2);
        some.add(vector4);
        boolean expected = true;
        boolean result = collection.containsAll(some);
        Assert.assertEquals(expected, result);
        some.add(null);
        result = collection.containsAll(some);
        Assert.assertEquals(expected, result);
        System.out.println("Test containsAll: done");
        System.out.println();
    }

    @Test
    public void testAddAll(){
        VectorCollection tested = new VectorCollection();
        tested.addAll(collection);
        Object[] mass = tested.toArray();
        Assert.assertEquals(true, tested.containsAll(collection));
        System.out.println("Test addAll: done");
        System.out.println();
    }


    @Test
    public void testRemoveAll(){
        VectorCollection toRemove = new VectorCollection();
        toRemove.add(vector2);
        toRemove.add(vector4);
        toRemove.add(null);
        collection.removeAll(toRemove);
        Assert.assertEquals(2, collection.size());
        Object[] mass = collection.toArray();
        Assert.assertEquals(mass[0], vector1);
        Assert.assertEquals(mass[1], vector3);
        for (Object o : mass)
            System.out.println(o);
        System.out.println("Test removeAll: done");
        System.out.println();
    }

    @Test
    public void testRetainAll(){
        VectorCollection toRetain = new VectorCollection();
        Assert.assertTrue(collection.size() == 5);
        collection.add(vector4);
        collection.add(null);
        Assert.assertTrue(collection.size() == 7);
        toRetain.add(vector1);
        toRetain.add(vector4);
        toRetain.add(null);
        collection.retainAll(toRetain);
        Object[] mass = collection.toArray();
        Assert.assertEquals(mass[0], vector1);
        Assert.assertEquals(mass[1], vector4);
        Assert.assertEquals(mass[2], null);
        for (Object o : mass)
            System.out.println(o);
    }

    @Test
    public void testClear(){
        collection.clear();
        Assert.assertTrue(collection.isEmpty());
        Assert.assertEquals(collection.size(), 0);
        System.out.println("Test clear: done");
    }
}