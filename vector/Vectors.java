package vector;

import vector.factory.ArrayVectorFactory;
import vector.factory.VectorFactory;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Dima on 07.10.2015.
 */
public class Vectors
{
    private static VectorFactory factory = new ArrayVectorFactory();

    public static Vector createInstance(int size){
        return factory.getNewVector(size);
    }

    public static Vector createInstance(){
        return factory.getNewVector();
    }

    public static void setFactory(VectorFactory incomingFactory) {
        factory = incomingFactory;
    }

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

    public static void outputVector(Vector v, OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(out));
        dos.writeInt(v.getSize());
        for (int i = 0; i < v.getSize(); i++) {
            dos.writeDouble(v.getElement(i));
        }
        dos.flush();
    }


    public static Vector inputVector(InputStream in) throws IOException {
        DataInputStream dis = new DataInputStream((in));
        int size = dis.readInt();
        double[] data = new double[size];
        for (int i = 0; i < size; i++) {
            data[i] = dis.readDouble();
        }
        Vector vector = createInstance(data.length);
        vector.fillFromMass(data);
        return vector;
    }

    public static void writeVector(Vector v, Writer out) throws IOException {
        StringBuilder sb = new StringBuilder();
        PrintWriter pw = new PrintWriter(out);
        sb.append(v.getSize());
        for (int i = 0; i < v.getSize(); i++) {
            sb.append(' ').append(v.getElement(i));
        }
        pw.println(sb.toString());
        pw.flush();
    }

    public static Vector readVector(Reader in) throws IOException {
        double[] data;
        StringBuilder sb = new StringBuilder();
        char c;
        while ((c = (char) in.read()) != '\n') {
            sb.append(c);
        }
        StringTokenizer st = new StringTokenizer(sb.toString());
        data = new double[Integer.parseInt(st.nextToken())];
        int i = 0;
        while (st.hasMoreTokens()) {
            data[i++] = Double.parseDouble(st.nextToken());
        }
        Vector vector = createInstance(data.length);
        vector.fillFromMass(data);
        return vector;
    }
}
