package Level2.Lesson3;

import java.util.HashMap;
import java.util.Map;

public class PhoneDirectory {
    Map<String, Integer> directory = new HashMap<>();

    public void add(String name, Integer phone) {
        directory.put(name, phone);
    }
    public void printDirectory(){
        for (Map.Entry entry: directory.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
