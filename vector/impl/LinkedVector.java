package vector.impl;

import vector.Vector;
import vector.exceptions.IncompatibleVectorSizesException;
import vector.exceptions.VectorIndexOutOfBoundsException;

import java.io.Serializable;

/**
 * Created by Dima on 12.10.2015.
 */
public class LinkedVector implements Vector, Cloneable, Serializable {

    public class Node implements Serializable
    {
        public Node previous;
        public double element;
        public Node next;

        public Node(double element)
        {
            this.element = element;
        }

        public Node(){};
    }

    public LinkedVector()
    {
        head = new Node();
        head.next = head;
        head.previous = head;
        size = 0;
    }

    public LinkedVector(double element)
    {
        head = new Node(element);
        head.next = head;
        head.previous = head;
        size = 1;
    }

    protected Node head;
    protected int size;

    public Node goToElement(int index)
    {
        Node result = head;
        int i = 0;
        while(i != index)
        {
            result = result.next;
            i++;
        }
        return result;
    }

    protected void insertElementBefore(Node current, Node newNod) {
        newNod.next = current;
        newNod.previous = current.previous;
        current.previous.next = newNod;
        current.previous = newNod;
        size++;
    }

    public void setElement(double element, int index)
    {
        if(index < 0 || index > getSize()-1)
            throw new VectorIndexOutOfBoundsException();
        Node current = goToElement(index);
        current.element = element;
    }

    public double getElement(int index)
    {
        if(index < 0 || index > getSize()-1)
            throw new VectorIndexOutOfBoundsException();
        Node current = goToElement(index);
        return current.element;
    }

    public int getSize()
    {
        int count = 1;
        int i = 0;
        Node curr = head.next;
        while(curr != head)
        {
            curr = goToElement(i);
            i++;
        }
        return size;
    }

    @Override
    public void fillFromMass(double[] arr)
    {
        size = 0;
        for(double value : arr)
            addElement(value);
    }

    @Override
    public void fillFromVector(Vector arrayVector)
    {
        size = 0;
        for(int i = 0; i < arrayVector.getSize(); i++)
            addElement(arrayVector.getElement(i));
    }

    @Override
    public void mult(double num)
    {
        for(int i = 0; i < getSize(); i++)
            setElement(getElement(i) * num, i);
    }

    @Override
    public void sum(Vector arrayVector) throws IncompatibleVectorSizesException
    {
        if (getSize() == arrayVector.getSize())
        {
            for(int i = 0; i < getSize(); i++)
                setElement((getElement(i)+arrayVector.getElement(i)), i);
        }
        else throw new IncompatibleVectorSizesException();
    }

    public void addElement(double element)
    {
        if(getSize() == 0)
        {
            head.element = element;
            size++;
        }
        else
        {
            Node newOne = new Node(element);
            Node prev = goToElement(size-1);
            prev.next = newOne;
            newOne.next = head;
            newOne.previous = prev;
            size++;
        }
    }

    public void insertElement(double element, int index)
    {
        if(index < 0 || index > getSize())
            throw new VectorIndexOutOfBoundsException();

        Node newNode = new Node(element);
        Node curr = goToElement(index);
        insertElementBefore(curr, newNode);
        if (index == 0) head = newNode;
    }

    public void deleteElement(int index)
    {
        if(index < 0 || index > getSize()-1)
            throw new VectorIndexOutOfBoundsException();
        if (index == 0)
        {
            head.element = goToElement(index+1).element;
            head.next = goToElement(index+2);
            goToElement(index+2).previous = head;
        }
        else
        {
            Node prev = goToElement(index - 1);
            Node next = goToElement(index + 1);

            prev.next = next;
            next.previous = prev;
        }
        size--;
    }

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < getSize(); i++)
        {
            string.append(getElement(i));
            string.append(' ');
        }
        string.deleteCharAt(string.length()-1);
        return String.valueOf(string);
    }

    public Object clone()
    {
        LinkedVector cloned = null;
        try
        {
            cloned = (LinkedVector) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            for (int i = 0; i < size; i++)
                cloned.addElement(getElement(i));
        }
        return cloned;
    }

    public boolean equals(Object object)
    {
        if (object instanceof Vector)
        {
            if (size == ((Vector) object).getSize())
            {
                for (int i = 0; i < size; i++)
                    if(getElement(i) != ((Vector) object).getElement(i)) return false;
                return true;
            }
        }
        return false;
    }
}