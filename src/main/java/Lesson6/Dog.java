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
        if (distance > 500) {
            System.out.println(this.name + " может пробежать максимум 500м!");
        } else {
            super.run(distance);
        }
    }

    @Override
    void swim(int distance) {
        if (distance > 10) {
            System.out.println(this.name + " может проплыть максимум 10м!");
        } else {
            super.swim(distance);
        }
    }
}
