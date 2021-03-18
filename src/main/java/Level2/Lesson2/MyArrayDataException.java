package Level2.Lesson2;

public class MyArrayDataException extends Exception {

    public MyArrayDataException(int x, int y, int sum) {
        System.err.println("В ячейке " + x + ":" + y + " содержатся неверные данные. Сумма элементов до ошибочной ячейки - " + sum);
    }
}
