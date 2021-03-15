package Level1.Lesson6;

public class Plate {
    private int plateFood;

    public void addFood(int food) {
        plateFood = food + plateFood;
        System.out.println("В миску добавлено " + food + " еды.");
        plateInfo();
        System.out.println();
    }

    public int getPlateFood() {
        return plateFood;
    }

    public void eatPlateFood(int appetite) {
        plateFood -= appetite;
    }

    public void plateInfo() {
        System.out.println("В миске - " + plateFood + " еды!");
    }
}
