package Level3.Lesson1;

import java.util.*;

public class Task1_2 { //первое и второе задание
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("Слово1");
        list1.add("Слово2");
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        arrayElementChange(list1, 0, 1);
        System.out.println(list1.get(0));
        System.out.println(list1.get(1));
        arrayElementChange(list2, 0, 1);
        System.out.println(list2.get(0));
        System.out.println(list2.get(1));
        String[] array1 = {"Name1", "Name2", "Name3"};
        ArrayList<String> transList = transformToArrayListAlt(array1);
        System.out.println(transList.get(1));
    }

    //Задание №1: метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа)
    public static <E> void arrayElementChange(AbstractList<E> array, int element1index, int element2index) {
        E elementCash = array.get(element1index);
        array.set(element1index, array.get(element2index));
        array.set(element2index, elementCash);
    }

    //Задание №2: метод, который преобразует массив в ArrayList; (но здесь у меня возник вопрос "А причем тут дженерики поэтому написал еще один метод"
    public static <E> ArrayList<E> transformToArrayList(E[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    //Задание №2(альтернативный вариант): метод, который преобразует массив в ArrayList,
    public static <E> ArrayList<E> transformToArrayListAlt(E[] array) {
        ArrayList<E> arrayList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            arrayList.add(array[i]);
        }
        return arrayList;
    }
}
