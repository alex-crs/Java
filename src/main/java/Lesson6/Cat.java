package Lesson6;

public class Cat extends Animal {
    private static int count;

    public Cat(String name) {
        super(name);
        count++;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    void swim(int distance) {
        System.out.println(this.name + " не умеет плавать...:(");
    }

    @Override
    void run(int distance) {
        super.run(distance);
    }
}
