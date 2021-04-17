package Task3;

import java.util.ArrayList;


// b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
// поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
// условную сортировку делаю в методе main
public class Box<T> {
    private String name;
    // c. Для хранения фруктов внутри коробки можете использовать ArrayList;
    private ArrayList<T> fruits;
    private float weightPerUnit;

    public Box(float weightPerUnit) {
        this.weightPerUnit = weightPerUnit;
        fruits = new ArrayList<>();
    }

    public ArrayList<T> getFruits() {
        return fruits;
    }

    // g. Не забываем про метод добавления фрукта в коробку.
    public void addFruit(T t) {
        fruits.add(t);
    }

    // d. Сделать метод getWeight() который высчитывает вес коробки,
    // зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
    public float getWeight() {
        return (float) fruits.size() * weightPerUnit;
    }

    // e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
    // которую подадут в compare в качестве параметра, true - если их веса равны,
    // false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
    public boolean compare(Box<? extends Fruit> box) {
        return getWeight() == box.getWeight();
    }

    // f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
    // (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
    // соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты,
    // которые были в этой коробке;
    public void intersperse(Box<T> box) {
        System.out.println("Пересыпаем фрукты");
        fruits.addAll(box.getFruits());
        box.getFruits().clear();
    }

    @Override
    public String toString() {
        return "В ящике " + fruits.size() + " " + (fruits.size() != 0 ? fruits.get(0).toString() : "фруктов");
    }
}
