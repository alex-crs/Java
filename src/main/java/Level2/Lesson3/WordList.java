package Level2.Lesson3;

import java.util.*;

public class WordList {
    private String name = "default name";
    List<Object> wordList = new LinkedList<>();

    public Object find(int index) {
        return wordList.get(index);
    }

    public Object find(Object o) {
        return !wordList.contains(o) ? "Нет такого элемента в списке" + " \"" + name + "\"" : wordList.indexOf(o);
    }

    public void add(Object o) {
        wordList.add(o);
    }

    public void add(int index, Object o) {
        wordList.add(index, o);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printList() {
        int index = 0;
        System.out.println("Список " + "\"" + name + "\"" + " состоит из " + wordList.size() + " элементов:");
        for (Object o : wordList) {
            System.out.println("Element №" + index + " " + o);
            index++;
        }
        System.out.println("=============================");
    }

    public void printUniqueElements() {
        System.out.println("Уникальные элементы из списка " + "\"" + name + "\"" + ":");
        for (int i = 0; i < wordList.size(); i++) {
            int count = 0;
            for (int j = 0; j < wordList.size(); j++) {
                if (wordList.get(i).equals(wordList.get(j))) {
                    count++;
                }
            }
            if (count == 1) {
                System.out.println(wordList.get(i));
            }
        }
        System.out.println("=============================");
    }

    public void printNotUniqueElements() {
        List<Object> wordListCopy = new LinkedList<>();
        System.out.println("Повторяющиеся элементы из списка " + "\"" + name + "\"" + ":");
        for (int i = 0; i < wordList.size(); i++) {
            int count = 0;
            for (int j = 0; j < wordList.size(); j++) {
                if (wordList.get(i).equals(wordList.get(j)) && !wordListCopy.contains(wordList.get(i))) {
                    count++;
                }
            }
            if (count > 1) {
                System.out.println(wordList.get(i) + " повторяется " + count + " раз(а).");
                wordListCopy.add(wordList.get(i));
            }
        }
        wordListCopy = null;
        System.out.println("=============================");
    }
}
