package Level2.Lesson1;

public class Team {
    private String name;
    Action[] teams;

    public Team(String name, Action... names) {
        this.name = name;
        this.teams = names;
    }

    public Action[] getTeams() {
        return teams;
    }

    void teamTable() {
        System.out.println("============================\nНа стартовой решетке команда " + name+" в составе: ");
        for (Action t : teams) {
            System.out.println(t.getClass().getSimpleName());
        }
        System.out.println("============================");
    }

    void showResult() {
        System.out.println("============================\nИз всей команды " + name + " дистанцию прошли: ");
        for (Action t : teams) {
            if (!t.isDroppedOut()) {
                System.out.println(t.getClass().getSimpleName());
            }
        }
        System.out.println("============================");
    }
}
