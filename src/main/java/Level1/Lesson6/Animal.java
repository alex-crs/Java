package Level1.Lesson6;

public class Animal {
    int distance;
    String name;
    private static int count = 0;
    Plate plate;
    boolean satiety = false;
    int hungry;

    public String getName() {
        return name;
    }

    public boolean isSatiety() {
        return satiety;
    }

    public Animal(String name) {
        this.name = name;
        this.count++;
    }

    public int getCount() {
        return this.count;
    }

    void run(int distance) {
        System.out.println(name + " пробежал " + distance + " метров!");
    }

    void swim(int distance) {
        System.out.println(name + " проплыл " + distance + " метров!");
    }

    void eat(Plate plate){

    }

}
