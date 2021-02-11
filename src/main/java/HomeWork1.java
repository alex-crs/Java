public class HomeWork1 {
    public static void main(String[] args) {
        byte a = 121;
        short b = 31100;
        int c = 2147483647;
        long d = 2147483647L;
        float e = 1.5f;
        double f = 1.5;
        char g = '@';
        boolean isNaN = true;
    }

    public static float action(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    public static boolean isSumCheck(int a, int b) {
        if ((a + b >= 10) && ((a + b <= 20))) {
            return true;
        } else {
            return false;
        }
    }

    public static void MinusAlert(int a) {
        if (a < 0) {
            System.out.println("Число отрицательное!");
        } else {
            System.out.println("Число положительное!");
        }
    }

    public static boolean isMinus(int a) {
        if (a < 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void helloName(String name) {
        System.out.println("Привет, " + name + "!");
    }

    public static void leapYear(int year) {
        if ((year % 400 == 0) || (year % 100 != 0) && (year % 4 == 0)) {
            System.out.println("Високосный");
        } else System.out.println("Не високосный");
    }
}
