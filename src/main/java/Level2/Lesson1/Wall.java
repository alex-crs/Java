package Level2.Lesson1;

public class Wall implements Go {

    private int wallHeight;

    public Wall(int wallHeight) {
        this.wallHeight = wallHeight;
    }

    @Override
    public void go(Action obj) {
        if (obj.getMaxHeight() >= wallHeight && !obj.isDroppedOut()) {
            obj.jump();
        } else if (!obj.isDroppedOut()){
            System.out.println(obj.getClass().getSimpleName() + " не смог перепрыгнуть, слишком большая высота.\n" + obj.getClass().getSimpleName() + " сошел с дистанции.\n============================");
            obj.setDroppedOut(true);
        }
    }
}
