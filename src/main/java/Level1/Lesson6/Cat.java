package Level1.Lesson6;

public class Cat extends Animal {
    private static int count;
    private int appetite = 8;

    public Cat(String name, int appetite) {
        super(name);
        this.appetite = appetite;
        count++;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    void swim(int distance) {
        System.out.println(name + " не умеет плавать...:(");
    }

    @Override
    void run(int distance) {
        if (distance > 200) {
            System.out.println(name + " может пробежать максимум 200м!");
        } else {
            super.run(distance);
        }
    }

    @Override
    void eat(Plate plate) {
        if (plate.getPlateFood() >= appetite) {
            System.out.println(name + " кушает из миски.");
            plate.eatPlateFood(appetite);
            satiety = true;
        } else if (plate.getPlateFood() > 0) {
            System.out.println(name + ": " + "В миске видно дно!!:((( Не буду кушать!:(((");
            System.out.println(name + " ...Ушел несолоно хлебавши...");
        } else if (plate.getPlateFood() == 0) {
            System.out.println(name + " Миска пустая... Хозяин! Мяу?");
        }
    }
}
