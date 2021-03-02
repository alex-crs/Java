package Lesson6;

public class Dog extends Animal {
    private static int count;

    public Dog(String name) {
        super(name);
        count++;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    void run(int distance) {
        super.run(distance);
    }

    @Override
    void swim(int distance) {
        super.swim(distance);
    }
}
