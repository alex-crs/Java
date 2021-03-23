package Level2.Lesson3;

import java.util.*;

public class WordList {
    private String name = "default name";
    private List<Object> wordList = new LinkedList<>();

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

    public void printNotUniqueElementsSlow() { //метод медленный ввиду того, что размер списка не изменяется и приходится пробегать по нему несколько раз повторно
        List<Object> wordListCopy = new LinkedList<>();
        System.out.println("Повторяющиеся элементы из списка " + "\"" + name + "\"" + ":");
        for (int i = 0; i < wordList.size(); i++) {
            int count = 0;
            for (Object o : wordList) {
                if (wordList.get(i).equals(o) && !wordListCopy.contains(wordList.get(i))) {
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

    public void printNotUniqueElementsFast() { //метод на мой взгляд быстрее в разы, так как массив постепенно убывает (ну и с итератором разобрался заодно:))
        List<Object> wordListCopy = new LinkedList<>(wordList);
        System.out.println("Повторяющиеся элементы из списка " + "\"" + name + "\"" + ":");
        while (wordListCopy.size()>0) {
            int count = 1;
            Iterator<Object> iterator = wordListCopy.iterator();
            Object o1 = iterator.next();
            iterator.remove();
            while (iterator.hasNext()) {
                if (o1.equals(iterator.next())) {
                    iterator.remove();
                    count++;
                }
            }
            if (count > 1) {
                System.out.println(o1 + " повторяется " + count + " раз(а).");
            }
        }
        System.out.println("=============================");
    }
}
