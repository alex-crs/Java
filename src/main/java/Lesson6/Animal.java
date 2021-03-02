package Lesson6;

public class Animal {
    int distance;
    String name;
    private static int count = 0;

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

}
