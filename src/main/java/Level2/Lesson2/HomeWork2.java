package Level2.Lesson2;

import java.util.Arrays;

public class HomeWork2 {
    public static void main(String[] args) {
        String[][] array = new String[4][4];
        try {
            arrayInit(array);
            arrayChecker(array, 4);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static void arrayChecker(String[][] array, int size) throws MyArraySizeException, MyArrayDataException {
        if (array.length != size) {
            throw new MyArraySizeException(array.length > size ? "Размер массива больше " + size : "Размер массива меньше " + size);
        }
        for (String[] strings : array) {
            if (strings.length != size) {
                throw new MyArraySizeException(strings.length > size ? "Размер вложенного массива больше " + size : "Размер вложенного массива меньше " + size);
            }
        }
        for (int i = 0, sum = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
    }

    public static void arrayInit(String[][] array) {
        for (String[] a : array) {
            Arrays.fill(a, "1");
        }
        array[1][2] = "Word";
        array[2][2] = "@";
    }
}
