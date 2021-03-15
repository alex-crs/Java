package Level1.Lesson6;

public class HomeWork6 {
    public static void main(String[] args) {
        Plate plate = new Plate();
        plate.addFood(21);
        Cat[] cats = new Cat[5];
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat("Кот №" + (i+1),8);
        }
        for (Cat cat : cats) {
            cat.eat(plate);
            System.out.println(cat.getName() + " " + (cat.isSatiety() ? "Сыт" : "Голоден") + "\n");
        }
        plate.plateInfo();
    }
}
