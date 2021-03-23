package Level2.Lesson3;

public class HomeWork3_1 {
    public static void main(String[] args) {
        WordList list = new WordList();
        list.setName("Кто в моем доме живет");
        list.add("Алексей");
        list.add("Кот Нэо");
        list.add("Домовой");
        list.add("Домовой");
        list.add("Якубович");
        list.add("Якубович");
        list.add("Якубович");
        list.add("Язь");
        list.add("Язь");
        list.add("Язь");
        list.add("Язь");
        list.add("Язь");
        list.add("Аксолотль");
        list.add("Аксолотль");
        list.add("Аксолотль");
        list.add("Аксолотль");
        list.add("Аксолотль");
        list.add("Аксолотль");
        list.add("Якубович");
        list.add(1,"Жена Полина");
        System.out.println(list.find(1));
        System.out.println(list.find("Кот Нээо"));
        list.printList();
        list.printUniqueElements();
        list.printNotUniqueElementsFast();
    }
}
