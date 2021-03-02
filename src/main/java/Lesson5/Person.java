package Lesson5;

public class Person {
    private String name;
    private String function;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Person(String name, String function, String email, String phone, int salary, int age) {
        this.name = name;
        this.function = function;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void print() {
        System.out.println("Фамилия, Имя: " + name + "\n" + "Должность: " + function + "\n" + "Электронная почта: " + email + "\n" + "Телефон: " + phone + "\n" + "Заработная плата: " + salary + "\n" + "Возраст: " + age + "\n" + "------------");
    }

}
