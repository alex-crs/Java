package Level1.Lesson3;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class HomeWork3_1 {
    public static Scanner consoleReader = new Scanner(System.in);

    public static void main(String[] args) {
        Random random = new Random();
        int tryCount = 3, hiddenNumber, userNumber;
        while (true) {
            hiddenNumber = random.nextInt(9);
            if (tryCount != -1) { //проверка на заполнение счетчика попыток
                do {
                    System.out.println("Введите целое число от 0 до 9:");
                    tryCount--;
                    userNumber = numberCheck();
                    if (userNumber == hiddenNumber) {
                        System.out.println("Вы выиграли, загаданное число действительно " + hiddenNumber);
                        tryCount = -1;
                        break;
                    } else if (tryCount == 0) {
                        System.out.println("Вы проиграли, загаданное число " + hiddenNumber);
                        tryCount--;
                        break;
                    } else if (userNumber > hiddenNumber) {
                        System.out.println("Введенное значение меньше, осталось попыток - " + tryCount);
                    } else if (userNumber < hiddenNumber) {
                        System.out.println("Введенное значение больше, осталось попыток - " + tryCount);
                    }
                } while (tryCount != 0);
            }
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            userNumber = numberCheck();
            if (userNumber == 1) { //без ввода 1 счетчик не заполняется и программа постоянно спрашивает дальнейшие действия
                tryCount = 3;
            } else if (userNumber == 0) {
                consoleReader.close();
                break;
            }
        }
    }

    public static int numberCheck() { //проверяю является ли числом введенное значение (не нашел для Scanner метода isNaN) :(
        int number;
        do {
            consoleReader = new Scanner(System.in);
            try {
                number = consoleReader.nextInt();
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод!");
            }
        } while (true);
    }
}
