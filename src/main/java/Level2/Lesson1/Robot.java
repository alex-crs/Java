package Level2.Lesson1;

public class Robot implements Action{

    private boolean droppedOut;
    private int maxDistance;
    private int maxHeight;

    public Robot(int maxDistance, int maxHeight) {
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }

    public boolean isDroppedOut() {
        return droppedOut;
    }

    public void setDroppedOut(boolean droppedOut) {
        this.droppedOut = droppedOut;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public void run() {
        System.out.println(this.getClass().getSimpleName() + " пробежал дистанцию.");
    }

    @Override
    public void jump() {
        System.out.println(this.getClass().getSimpleName() + " перепрыгнул стену.");
    }

}
