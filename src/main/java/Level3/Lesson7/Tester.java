package Level3.Lesson7;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Tester {

    public static void start(Class<?> obj) {
        Method beforeSuite = null, afterSuite = null;
        Method[] methods = obj.getDeclaredMethods();
        int methodRunCount = methods.length;
        for (int i = 0; i < methods.length; i++) {
            Annotation[] an = methods[i].getDeclaredAnnotations();
            for (int j = 0; j < an.length; j++) {
                if (an[j].annotationType().equals(BeforeSuite.class)) {
                    if (beforeSuite == null) {
                        beforeSuite = methods[i];
                        methodRunCount--;
                    } else {
                        throw new RuntimeException(methods[i].getAnnotation(BeforeSuite.class).message());
                    }
                }
                if (an[j].annotationType().equals(AfterSuite.class)) {
                    if (afterSuite == null) {
                        afterSuite = methods[i];
                        methodRunCount--;
                    } else {
                        throw new RuntimeException(methods[i].getAnnotation(AfterSuite.class).message());
                    }
                }
            }
        }
        try {
            if (beforeSuite != null) {
                beforeSuite.setAccessible(true);
                beforeSuite.invoke(obj);
                beforeSuite.setAccessible(false);
            }
            for (int priority = 1; methodRunCount != 0 && priority <= 10; priority++) {
                for (int i = 0; i < methods.length; i++) {
                    Annotation[] an = methods[i].getDeclaredAnnotations();
                    for (int j = 0; j < an.length; j++) {
                        if (an[j].annotationType().equals(Test.class) && methods[i].getAnnotation(Test.class).priority() == priority) {
                            methods[i].setAccessible(true);
                            methods[i].invoke(obj);
                            methods[i].setAccessible(false);
                            methodRunCount--;
                        }
                    }
                }
            }
            if (afterSuite != null) {
                afterSuite.setAccessible(true);
                afterSuite.invoke(obj);
                afterSuite.setAccessible(false);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
