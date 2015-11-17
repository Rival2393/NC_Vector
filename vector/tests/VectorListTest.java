package vector.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import vector.container.VectorList;
import vector.impl.ArrayVector;

import java.util.ArrayList;
import java.util.ListIterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dima on 09.11.2015.
 */
public class VectorListTest {

    double[] input = { 1, 2, 3 };
    double[] input2 = { 4, 5, 6 };
    double[] input3 = { 7, 8, 9 };
    ArrayVector vector1 = new ArrayVector(0);
    ArrayVector vector2 = new ArrayVector(0);
    ArrayVector vector3 = new ArrayVector(0);
    VectorList list;

    public VectorListTest() {
    }

    @Before
    public void setUp(){
        vector1.fillFromMass(input);
        vector2.fillFromMass(input2);
        vector3.fillFromMass(input3);
        list = new VectorList();
    }

    public void testAdd() {
        list.add(0, vector1);
        Assert.assertEquals(1, list.size());
        list.add(0, null);
        Assert.assertEquals(2, list.size());
        list.add(0, vector2);
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(list.get(0), vector2);
        Assert.assertEquals(list.get(1), null);
        Assert.assertEquals(list.get(2), vector1);
        System.out.println("Test add: done");
        System.out.println();
    }

    @Test
    public void testAddAll() {
        ArrayList<ArrayVector> arrList = new ArrayList<>();
        arrList.add(vector1);
        arrList.add(vector2);
        list.add(0, null);
        Assert.assertEquals(list.get(0), null);
        list.addAll(arrList);
        Assert.assertTrue(list.size() == 3);
        Assert.assertEquals(list.get(1), vector1);
        Assert.assertEquals(list.get(2), vector2);
        System.out.println("Test addAll: done");
        System.out.println();
    }

    @Test
    public void testGet() {
        list.add(vector1);
        list.add(vector2);
        list.add(null);
        list.add(vector3);
        Assert.assertEquals(vector1, list.get(0));
        Assert.assertEquals(vector2, list.get(1));
        Assert.assertEquals(null, list.get(2));
        Assert.assertEquals(vector3, list.get(3));
        System.out.println("Test get: done");
        System.out.println();
    }

    @Test
    public void testIndexOf() {
        list.add(vector1);
        list.add(null);
        list.add(vector2);
        list.add(vector2);
        list.add(null);
        Assert.assertEquals(1, list.indexOf(null));
        Assert.assertEquals(2, list.indexOf(vector2));
        Assert.assertEquals(-1, list.indexOf(vector3));
        System.out.println("Test indexOf: done");
        System.out.println();
    }

    @Test
    public void testLastIndexOf() {
        list.add(null);
        list.add(vector2);
        list.add(vector1);
        list.add(vector1);
        list.add(vector2);
        list.add(vector1);
        list.add(null);
        Assert.assertEquals(6, list.lastIndexOf(null));
        Assert.assertEquals(5, list.lastIndexOf(vector1));
        Assert.assertEquals(4, list.lastIndexOf(vector2));
        System.out.println("Test lastIndexOf: done");
        System.out.println();
    }

    @Test (expected = UnsupportedOperationException.class)
    public void testListIterator_0args() {
        ListIterator result = list.listIterator();
        System.out.println("Test listIterator (No args): done");
        System.out.println();
    }

    @Test (expected = UnsupportedOperationException.class)
    public void testListIterator_int() {
        ListIterator result = list.listIterator(2);
        System.out.println("Test listIterator (With args): done");
        System.out.println();
    }

    @Test
    public void testRemove() {
        list.add(0, vector1);
        list.add(1, vector2);
        Assert.assertEquals(vector2, list.remove(1));
        Assert.assertEquals(vector1, list.remove(0));
        assertEquals(0, list.size());
        System.out.println("Test remove: done");
        System.out.println();
    }

    @Test
    public void testSet() {
        list.add(0, vector1);
        Assert.assertEquals(vector1, list.set(0, null));
        Assert.assertEquals(null, list.get(0));
        list.add(vector2);
        list.add(vector3);
        Assert.assertEquals(vector2, list.set(1, null));
        Assert.assertEquals(null, list.get(1));
        Assert.assertEquals(vector3, list.set(2, null));
        Assert.assertEquals(null, list.get(2));
        System.out.println("Test set: done");
        System.out.println();
    }

    /**
     * Test of subList method, of class VectorList.
     */
    @Test
    public void testSubList() {
        list.add(vector1);
        list.add(vector2);
        list.add(vector3);
        list.add(vector1);
        list.add(vector2);
        list.add(vector3);
        VectorList subList = (VectorList) list.subList(1, 4);
        Assert.assertTrue(subList.size() == 3);
        Assert.assertEquals(vector2, subList.get(0));
        Assert.assertEquals(vector3, subList.get(1));
        Assert.assertEquals(vector1, subList.get(2));
        System.out.println("Test subList: done");
        System.out.println();
    }
}