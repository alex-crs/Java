package Level2.Lesson3;

public class HomeWork3_2 {
    public static void main(String[] args) {
        PhoneDirectory directory = new PhoneDirectory();
        directory.add("Андреев", 89509716300L);
        directory.add("Борисов", 89509716300L);
        directory.get(89509716300L);
        directory.get("Андреев");
    }
}
