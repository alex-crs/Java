package Level2.Lesson3;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class PhoneDirectory {
    Map<String, Long> directory = new HashMap<>();

    public void add(String name, Long phone) {
        directory.put(name, phone);
    }

    public void get(Long phone) {
        System.out.println("В телефонной книге номеру телефона " + phone + " соответствуют следующие фамилии:");
        for (Map.Entry entry : directory.entrySet()) {
            if (entry.getValue().equals(phone)) {
                System.out.println(entry.getKey());
            }
        }
        System.out.println("=============================");
    }

    public void get(String name) {
        System.out.println("В телефонной книге фамилии " + name + " соответствуют следующие номера телефонов:");
        for (Map.Entry entry : directory.entrySet()) {
            if (entry.getKey().equals(name)) {
                System.out.println(entry.getValue());
            }
        }
        System.out.println("=============================");
    }

    public void print() {
        for (Map.Entry entry : directory.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
