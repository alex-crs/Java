package Level2.Lesson2;

public class MyArrayDataException extends Exception {

    public MyArrayDataException(int x, int y) {
        System.err.println("В ячейке " + x + ":" + y + " содержатся неверные данные");
    }
}
