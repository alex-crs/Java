package Level3.Lesson7;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Tester {
    private static Method beforeSuite = null, afterSuite = null;

    public static void start(Class<?> obj) {
        Method[] methods = obj.getDeclaredMethods();
        checkConditions(obj, methods);  //проверяем уникальность BeforeSuite, AfterSuite и приоритеты
        try {
            if (beforeSuite != null) {  //запускаем BeforeSuite
                methodRun(obj, beforeSuite);
            }
            testRunner(obj, methods);  //запускаем методы с аннотацией @Test, согласно приоритетам
            if (afterSuite != null) {  //запускаем afterSuite
                methodRun(obj, afterSuite);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void methodRun(Class<?> obj, Method method) throws InvocationTargetException, IllegalAccessException {
        method.setAccessible(true);
        method.invoke(obj);
        method.setAccessible(false);
    }

    private static void testRunner(Class<?> obj, Method[] methods) throws InvocationTargetException, IllegalAccessException {
        for (int priority = 1; priority <= 10; priority++) {
            for (int i = 0; i < methods.length; i++) {
                Annotation[] an = methods[i].getDeclaredAnnotations();
                for (int j = 0; j < an.length; j++) {
                    if (an[j].annotationType().equals(Test.class) && methods[i].getAnnotation(Test.class).priority() == priority) {
                        methodRun(obj, methods[i]);
                    }
                }
            }
        }
    }

    private static void checkConditions(Class<?> obj, Method[] methods) {
        for (int i = 0; i < methods.length; i++) {
            Annotation[] an = methods[i].getDeclaredAnnotations();
            for (int j = 0; j < an.length; j++) {
                if (an[j].annotationType().equals(BeforeSuite.class)) {
                    if (beforeSuite == null) {
                        beforeSuite = methods[i];
                    } else {
                        throw new RuntimeException(methods[i].getAnnotation(BeforeSuite.class).message());
                    }
                }
                if (an[j].annotationType().equals(AfterSuite.class)) {
                    if (afterSuite == null) {
                        afterSuite = methods[i];
                    } else {
                        throw new RuntimeException(methods[i].getAnnotation(AfterSuite.class).message());
                    }
                }
                if (an[j].annotationType().equals(Test.class)) {
                    int currentPriority = methods[i].getAnnotation(Test.class).priority();
                    if (currentPriority < 1 || currentPriority > 10) {
                        throw new RuntimeException(methods[i].getAnnotation(Test.class).message());
                    }
                }
            }
        }
    }
}
