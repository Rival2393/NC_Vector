package vector.tests;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import vector.container.VectorMap;
import vector.impl.ArrayVector;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dima on 18.11.2015.
 */
public class VectorMapTest {

    ArrayVector vector1;
    ArrayVector vector2;
    ArrayVector vector3;
    ArrayVector vector4;

    double[] arr1;
    double[] arr2;
    double[] arr3;
    double[] arr4;

    VectorMap map = new VectorMap();

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

        map.put(1, vector1);
        map.put(2, vector2);
        map.put(3, vector3);
        map.put(4, vector4);
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(4, map.size());
        map.put(5, null);
        Assert.assertEquals(5, map.size());
        map.put(5, vector2);
        Assert.assertEquals(5, map.size());
    }

    @Test
    public void testIsEmpty() throws Exception {
        Assert.assertTrue(!map.isEmpty());
        map.clear();
        Assert.assertTrue(map.isEmpty());
    }

    @Test
    public void testContainsKey() throws Exception {
        Assert.assertTrue(map.containsKey(1));
        Assert.assertTrue(map.containsKey(2));
        Assert.assertTrue(map.containsKey(3));
        Assert.assertTrue(map.containsKey(4));
        Assert.assertTrue(!map.containsKey(5));
        map.put(null, null);
        Assert.assertTrue(map.containsKey(null));
    }

    @Test
    public void testContainsValue() throws Exception {
        Assert.assertTrue(map.containsValue(vector1));
        Assert.assertTrue(map.containsValue(vector2));
        Assert.assertTrue(map.containsValue(vector3));
        Assert.assertTrue(map.containsValue(vector4));
        Assert.assertTrue(!map.containsValue(new ArrayVector(0)));
        map.put(null, null);
        Assert.assertTrue(map.containsValue(null));
    }

    @Test
    public void testGet() throws Exception {
        Assert.assertEquals(vector1, map.get(1));
        Assert.assertEquals(vector2, map.get(2));
        Assert.assertEquals(vector3, map.get(3));
        Assert.assertEquals(vector4, map.get(4));
        Assert.assertNotSame(vector1, map.get(4));
        map.put(null, null);
        Assert.assertEquals(null, map.get(null));
    }

    @Test
    public void testPut() throws Exception {
        VectorMap map = new VectorMap();
        Assert.assertEquals(map.put(null, null), null);
        Assert.assertEquals(map.put(1, vector1), null);
        Assert.assertEquals(map.put(2, vector2), null);
        Assert.assertEquals(map.put(2, vector3), vector2);
        Assert.assertEquals(map.put(1, vector4), vector1);
    }

    @Test
    public void testRemove() throws Exception {
        Assert.assertEquals(map.remove(1), vector1);
        Assert.assertEquals(map.remove(2), vector2);
        Assert.assertEquals(map.remove(2), null);
    }

    @Test
    public void testPutAll() throws Exception {
        VectorMap toPut = new VectorMap();
        toPut.put(5, vector1);
        toPut.put(6, vector2);
        toPut.put(7, vector3);
        Assert.assertEquals(map.size(), 4);
        map.putAll(toPut);
        Assert.assertEquals(map.size(), 7);
    }

    @Test
    public void testClear() throws Exception {
        Assert.assertEquals(map.size(), 4);
        map.clear();
        Assert.assertEquals(map.size(), 0);
    }

    @Test
    public void testKeySet() throws Exception {
        Set keys = new HashSet<>();
        keys = map.keySet();
        Assert.assertEquals(keys.size(), 4);
        Assert.assertEquals(map.size(), 4);
        Object[] mass = keys.toArray();
        Assert.assertEquals(1, mass[0]);
        Assert.assertEquals(2, mass[1]);
        Assert.assertEquals(3, mass[2]);
        Assert.assertEquals(4, mass[3]);
    }

    @Test
    public void testValues() throws Exception {
        Collection values;
        values = map.values();
        Object[] mass = values.toArray();
        Assert.assertEquals(vector1, mass[0]);
        Assert.assertEquals(vector2, mass[1]);
    }

    @Test
    public void testEntrySet() throws Exception {
        /*Set entries;
        entries = map.entrySet();
        Object[] mass = entries.toArray();
        Assert.assertEquals(map.);*/
    }
}