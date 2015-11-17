package vector.impl;

import vector.Vector;
import vector.exceptions.IncompatibleVectorSizesException;
import vector.exceptions.VectorIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Dima on 07.10.2015.
 */
public class ArrayVector implements Vector, Cloneable, Serializable
{
    protected double[] data;

    public ArrayVector(int size)
    {
        data = new double[size];
    }

    public void setElement(double element, int index)
    {
        try
        {
            data[index] = element;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new VectorIndexOutOfBoundsException();
        }
    }

    public double getElement(int index)
    {
        try
        {
            return data[index];
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new VectorIndexOutOfBoundsException();
        }
    }

    public int getSize()
    {
        return data.length;
    }

    public void fillFromMass(double[] arr)
    {
        data = new double[arr.length];
        for(int i = 0; i < data.length; i++)
        {
            data[i] = arr[i];
        }
    }

    public void fillFromVector(Vector argVector)
    {
        data = new double[argVector.getSize()];
        for(int i = 0; i < data.length; i++)
        {
            data[i] = argVector.getElement(i);
        }
    }

    public void mult(double num)
    {
        for(int i = 0; i < data.length; i++)
        {
            data[i]*=num;
        }
    }

    public void sum(Vector argVector) throws IncompatibleVectorSizesException {
        if (data.length == argVector.getSize())
            for (int i = 0; i < data.length; i++)
            {
                data[i]+=argVector.getElement(i);
            }
        else throw new IncompatibleVectorSizesException();
    }

    public boolean equal(Vector argVector)
    {
        if(data.length!=argVector.getSize()) return false;
        for (int i = 0; i < data.length; i++)
        {
            if (data[i] == argVector.getElement(i)) continue;
            else return false;
        }
        return true;
    }

    public double[] intoArray()
    {
        double[] result = new double[data.length];
        for (int i = 0; i < result.length; i++)
        {
            result[i] = data[i];
        }
        return result;
    }

    public void addElement(double element)
    {
        data = Arrays.copyOf(data, data.length+1);
        data[data.length-1] = element;
    }

    public void insertElement(double element, int index)
    {
        if(index > data.length || index < 0)
            throw new VectorIndexOutOfBoundsException(); // 1.1, 2.2, 3.3
        double[] extended = new double[data.length+1]; // 0.0, 0.0, 0.0, 0.0
        System.arraycopy(data, 0, extended, 0, index); //1.1, 0.0, 0.0, 0.0
        extended[index] = element;  //1.1, 7.7, 0.0, 0.0
        System.arraycopy(data, index, extended, index+1, extended.length-index-1); //1.1, 7.7, 2.2, 3.3
        data = new double[extended.length];
        for(int i = 0; i < extended.length; i++)
            data[i] = extended[i];
    }

    public void deleteElement(int index)
    {
        if(index > data.length || index < 0)
            throw new VectorIndexOutOfBoundsException();
        double[] restricted = new double[data.length-1];
        System.arraycopy(data, 0, restricted, 0, index);
        System.arraycopy(data, index+1, restricted, index, restricted.length-index);
        data = new double[restricted.length];
        for(int i = 0; i < restricted.length; i++)
            data[i] = restricted[i];
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        for (double element : data)
        {
            string.append(element);
            string.append(" ");
        }
        string.deleteCharAt(string.length()-1);
        return String.valueOf(string);
    }

    public Object clone()
    {
        ArrayVector cloned = null;
        try
        {
            cloned = (ArrayVector) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            double[] clonedVector = new double[data.length];
            for(int i = 0; i < data.length; i++)
            {
                clonedVector[i] = data[i];
            }
            cloned.fillFromMass(clonedVector);
        }
        return cloned;
    }

    public boolean equals(Object object)
    {
        if (object instanceof Vector)
        {
            if (data.length == ((Vector) object).getSize())
            {
                for (int i = 0; i < data.length; i++)
                    if (data[i] != ((Vector) object).getElement(i)) return false;
                return true;
            }
        }
        return false;
    }
}