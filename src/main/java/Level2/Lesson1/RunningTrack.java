package Level2.Lesson1;

public class RunningTrack implements Go {

    private int trackDistance;

    public RunningTrack(int trainingDistance) {
        this.trackDistance = trainingDistance;
    }

    @Override
    public void go(Action obj) {
        if (obj.getMaxDistance() >= trackDistance && !obj.isDroppedOut()) {
            obj.run();
        } else if (!obj.isDroppedOut()){
            System.out.println(obj.getClass().getSimpleName() + " не смог пробежать, слишком большая дистанция.\n" + obj.getClass().getSimpleName() + " сошел с дистанции.\n============================");
            obj.setDroppedOut(true);
        }
    }
}
