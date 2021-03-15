package Level2.Lesson1;

public class Course{
    protected Go[] course;

    public Course(Go ... course) {
        this.course = course;
    }

    void dolt(Team obj){
        for (Action u:obj.getTeams()){
            for (Go t:course){
                t.go(u);
            }
        }
    }
}
