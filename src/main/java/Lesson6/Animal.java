package Lesson6;

public class Animal {
    int distance;
    String name;
    private static int count = 0;

    public Animal(String name) {
        this.name = name;
        count++;
    }

    public int getCount() {
        return count;
    }

    void run(int distance) {
        System.out.println(name + " пробежал " + distance);
    }

    void swim(int distance) {
        System.out.println(name + " проплыл " + distance);
    }

}
