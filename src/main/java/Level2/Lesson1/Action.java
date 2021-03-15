package Level2.Lesson1;

public interface Action {

    boolean isDroppedOut = false;

    void run();

    void jump();

    int getMaxDistance();

    int getMaxHeight();

    boolean isDroppedOut();

    void setDroppedOut(boolean isDroppedOut);

}
