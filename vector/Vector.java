package vector;

import vector.exceptions.IncompatibleVectorSizesException;

/**
 * Created by Dima on 07.10.2015.
 */
public interface Vector
{
    void setElement(double element, int index);
    double getElement(int index);
    int getSize();
    void fillFromMass(double[] arr);
    void fillFromVector(Vector arrayVector);
    void mult(double num);
    void sum(Vector arrayVector) throws IncompatibleVectorSizesException;
    boolean equals(Object object);
    void addElement(double element);
    void insertElement(double element, int index);
    void deleteElement(int index);
    String toString();
    Object clone();
}
