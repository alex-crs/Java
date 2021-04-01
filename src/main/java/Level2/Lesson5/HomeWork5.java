package Level2.Lesson5;

import java.util.Arrays;

public class HomeWork5 {

    static final int size = 10000000;

    public static void main(String[] args) {
        float[] arr = new float[size];
        long time = System.currentTimeMillis();
        Arrays.fill(arr, 1f);
        arrayDividerCalc(arr, 80);
        System.out.println(System.currentTimeMillis() - time);
    }

    public static void arrayCalculate(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public static void arrayDividerCalc(float[] array, int threads) {
        int threadAmount = threads - 1;
        int residue = array.length % threadAmount;
        int segmentLength = (array.length - residue) / threadAmount;
        for (int i = 0; i <= threadAmount; i++) {
            int position = segmentLength * i;
            int segmentNumber = i;
            Thread thread = new Thread(() -> {
                int currentSegmentLength = segmentNumber != threadAmount ? segmentLength : residue;
                float[] arrayCopy = new float[currentSegmentLength];
                System.arraycopy(array, position, arrayCopy, 0, currentSegmentLength);
                arrayCalculate(arrayCopy);
                synchronized (array) {
                    System.arraycopy(arrayCopy, 0, array, position, currentSegmentLength);
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
