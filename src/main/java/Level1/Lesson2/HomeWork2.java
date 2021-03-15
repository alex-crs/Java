package Level1.Lesson2;

public class HomeWork2 {
    public static void main(String[] args) {
        // задание №1
        int[] task1array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < task1array.length; i++) {
            if (task1array[i] == 1) {
                task1array[i] = 0;
            } else {
                task1array[i] = 1;
            }
        }
        //--------------------------------------------

        // задание №2
        int[] task2array = new int[8];
        for (int i = 1; i < task2array.length; i++) {
            task2array[i] = task2array[i - 1] + 3;
        }
        //--------------------------------------------

        // задание №3
        int[] task3array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < task3array.length; i++) {
            if (task3array[i] < 6) {
                task3array[i] *= 2;
            }
        }
        //--------------------------------------------

        // задание №4
        int[][] task4array = new int[6][6];
        for (int i = 0, j = 0; i < task4array.length; i++, j++) {
            task4array[i][j] = 1;
            task4array[i][(task4array[i].length - 1) - i] = 1;
        }
        //--------------------------------------------

        // задание №5
        int[] task5array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < task5array.length; i++) {
            if (task5array[i] > maxValue) {
                maxValue = task5array[i];
            }
            if (task5array[i] < minValue) {
                minValue = task5array[i];
            }
        }
    }

    // задание №6
    public static boolean isArraySumEqual(int[] array) {
        for (int checkLeftPosition = 0, sumLeft = 0; checkLeftPosition < array.length; checkLeftPosition++) {
            int sumRight = 0;
            for (int checkRightPosition = checkLeftPosition + 1; checkRightPosition < array.length; checkRightPosition++) {
                sumRight += array[checkRightPosition];
            }
            sumLeft += array[checkLeftPosition];
            if ((sumLeft == sumRight) && (checkLeftPosition != 0) && (checkLeftPosition != array.length - 2)) {
                //проверка равенства и исключение равенства суммы крайним значениям массива (так как это не соответствует условию задачи)
                return true;
            }
        }
        return false;
    }

    // задание №7
    public static void arrayShift(int[] array, int step) {
        while (step != 0) {
            if (step < 0) {
                for (int i = 0; i < array.length - 1; i++) { //двигаем влево
                    int value1 = array[i];
                    int value2 = array[i + 1];
                    array[i] = value2;
                    array[i + 1] = value1;
                }
                step++;
            }
            if (step > 0) {
                for (int i = array.length - 1; i > 0; i--) { //двигаем вправо
                    int value1 = array[i];
                    int value2 = array[i - 1];
                    array[i - 1] = value1;
                    array[i] = value2;
                }
                step--;
            }
        }

    }
}
