package Task3;

public class MainClass {
    public static void main(String[] args) {
        Box<Apple> box1 = new Box<>(1.0f);
        Box<Orange> box2 = new Box<>(1.5f);
        box1.addFruit(new Apple());
        box1.addFruit(new Apple());
        box2.addFruit(new Orange());
        box2.addFruit(new Orange());
        System.out.println(box2.getWeight());
        System.out.println(box1.compare(box2));
        Box<Apple> box3 = new Box<>(1.5f);
        box3.intersperse(box1);
        System.out.println(box1);
        System.out.println(box3);
        System.out.println(box2);
    }
}
