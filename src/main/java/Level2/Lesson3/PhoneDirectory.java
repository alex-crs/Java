package Level2.Lesson3;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class PhoneDirectory {
    Map<Person, PhoneNumber> directory = new HashMap<>();

    public void add(String name, Long phone) {
        directory.put(new Person(name), new PhoneNumber(phone));
    }

    public void get(Long phone) {
        System.out.println("В телефонной книге номеру телефона " + phone + " соответствуют следующие фамилии:");
        for (Map.Entry<Person, PhoneNumber> pair : directory.entrySet()) {
            Person key = pair.getKey();
            PhoneNumber value = pair.getValue();
            if (value.getPhone().equals(phone)) {
                System.out.println(key.getName());
            }
        }
        System.out.println("=============================");
    }

    public void get(String name) {
        System.out.println("В телефонной книге фамилии " + name + " соответствуют следующие номера телефонов:");
        for (Map.Entry<Person, PhoneNumber> pair : directory.entrySet()) {
            Person key = pair.getKey();
            PhoneNumber value = pair.getValue();
            if (key.getName().equals(name)) {
                System.out.println(value.getPhone());
            }
        }
        System.out.println("=============================");
    }

    public void print() {
        for (Map.Entry<Person, PhoneNumber> pair : directory.entrySet()) {
            Person key = pair.getKey();
            PhoneNumber value = pair.getValue();
            System.out.println(key.getName() + " " + value.getPhone());
        }
    }
}
