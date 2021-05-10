package Level3.Lesson7;

public class TestedClass {
    @BeforeSuite
    public static void method1() {
        System.out.println("Запущен метод 1");
    }

    @Test(priority = 1)
    public static void method2() {
        System.out.println("Запущен метод 2");
    }

    @Test(priority = 2)
    public static void method6() {
        System.out.println("Запущен метод 6");
    }

    @Test(priority = 7)
    public static void method7() {
        System.out.println("Запущен метод 7");
    }

    @Test(priority = 3)
    public static void method3() {
        System.out.println("Запущен метод 3");
    }

    @AfterSuite
    public static void method4() {
        System.out.println("Запущен метод 4");
    }

    //    @Test(priority = 1)
    public static void method5() {
        System.out.println("Запущен метод 5");
    }
}
