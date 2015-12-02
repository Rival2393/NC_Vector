package vector.threads;

import vector.Vector;

/**
 * Created by Dima on 28.11.2015.
 */
public class VectorWriter<E extends Vector> extends Thread {

    protected E vector;

    public void setVector(E vector) {
        this.vector = vector;
    }

    @Override
    public void run() {
        double number;
        for(int i = 0; i < vector.getSize(); i++) {
            number = Math.random();
            vector.setElement(number, i);
            System.out.println("Write: " + number + " to position " + i + '\n');
            /*try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}
