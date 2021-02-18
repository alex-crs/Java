package Lesson3;

import java.util.Random;
import java.util.Scanner;

public class HomeWork3_2 {
    public static Random random = new Random();
    public static Scanner consoleReader = new Scanner(System.in);
    public static void main(String[] args) {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango",
                "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String hiddenWord = words[random.nextInt(words.length)], userWord;
        System.out.println("Я загадал слово! Попробуйте угадать!");
        do {
            userWord = consoleReader.nextLine().toLowerCase();
            if (!userWord.equals(hiddenWord)) {
                System.out.println("Не угадали! Попробуйте еще раз. Подсказка: " + hider(hiddenWord));
            } else {
                System.out.println("Вы угадали, это действительно " + hiddenWord + "!");
                consoleReader.close();
                break;
            }
        } while (true);
    }

    public static String hider(String word) {
        char[] mask = "###############".toCharArray();
        for (int i = 0; i < 2; i++) {
            int a = random.nextInt(word.length());
            mask[a] = word.charAt(a);
        }
        return String.valueOf(mask);
    }
}
