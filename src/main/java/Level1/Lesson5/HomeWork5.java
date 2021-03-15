package Level1.Lesson5;

public class HomeWork5 {
    public static void main(String[] args) {
        Person[] personArray = new Person[5];
        personArray[0] = new Person("Андреев Алексей", "Главный инженер проекта/IT специалист", "alexav5@yandex.ru", "89233550895", 80000, 35);
        personArray[1] = new Person("Пугачева Алла", "Примадонна", "pugacheva@mail.ru", "89349755052", 3000000, 71);
        personArray[2] = new Person("Путин Владимир", "Президент РФ", "secret", "8XXXXXXXXXX", 1, 68);
        personArray[3] = new Person("Маск Илон", "Генеральный директор SpaceX ", "maskLead@mask.ru", "+12097895638", 50000000, 49);
        personArray[4] = new Person("Талсания Архам", "Программист", "talsania@india.ru", "+9189509753052", 500000, 6);
        for (Person person : personArray) {
            if (person.getAge() > 40) {
                person.print();
            }
        }
    }
}
