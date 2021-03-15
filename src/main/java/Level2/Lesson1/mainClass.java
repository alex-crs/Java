package Level2.Lesson1;

public class mainClass {
    public static void main(String[] args) {
        Team team = new Team("Котаны", new Human(44, 2), new Cat(15, 1), new Robot(500, 10),new SuperCat(500,150));
        Course course = new Course(new RunningTrack(44), new Wall(2),new RunningTrack(55),new Wall(4),new RunningTrack(150));
        team.teamTable();
        course.dolt(team);
        team.showResult();
    }
}
