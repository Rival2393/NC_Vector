package vector;

import vector.impl.ArrayVector;

import java.io.*;

/**
 * Created by Dima on 07.10.2015.
 */
public class Vectors
{
    public static void bubbleSort(Vector vector)
    {
        for(int i=0; i<vector.getSize()-1; i++)
            for(int j=0; j<vector.getSize()-i-1; j++)
            {
                if(vector.getElement(j)>vector.getElement(j+1))
                {
                    double temp = vector.getElement(j);
                    vector.setElement(vector.getElement(j + 1), j);
                    vector.setElement(temp, j + 1);
                }
            }
    }

    public static void outputVector(Vector v, OutputStream out) throws IOException
    {
        DataOutputStream dataOut = new DataOutputStream(out);
        dataOut.writeInt(v.getSize());
        for(int i = 0; i < v.getSize(); i++)
        {
            dataOut.writeDouble(v.getElement(i));
        }
    }

    public static Vector inputVector(InputStream in) throws IOException {
        DataInputStream dataIn = new DataInputStream(in);
        Vector vector = new ArrayVector(dataIn.readInt());
        for (int i = 0; i < vector.getSize(); i++)
        {
            vector.setElement(dataIn.readDouble(), i);
        }
        return vector;
    }

    public static void writeVector(Vector v, Writer out) throws IOException {
        PrintWriter outWriter = new PrintWriter(out);
        outWriter.print(v.getSize());

        for(int i = 0; i < v.getSize(); i++)
        {
            outWriter.append(' ');
            outWriter.print(v.getElement(i));
        }
        outWriter.println();

    }

    public static Vector readVector(Reader in) throws IOException {
        StreamTokenizer tokenizer = new StreamTokenizer(in);
        Vector vector = new ArrayVector(((int)tokenizer.nval));
        tokenizer.nextToken();
        while (tokenizer.nextToken()!= StreamTokenizer.TT_EOF)
            vector.addElement(tokenizer.nval);
        return vector;
    }
}
