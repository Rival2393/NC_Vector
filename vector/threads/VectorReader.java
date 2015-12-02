package vector.threads;

import vector.Vector;

/**
 * Created by Dima on 28.11.2015.
 */
public class VectorReader<E extends Vector> extends Thread {

    protected E vector;

    public void setVector(E vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        for(int i = 0; i < vector.getSize(); i++){
            System.out.println("Read: " + vector.getElement(i) + " from position " + i + '\n');
            /*try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}
