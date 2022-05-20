package app;

import classes.Car;
import classes.User;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Object[] classes = loadClasses();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Classes:");
        for (Object o : classes) {
            System.out.println("\t - " + o.getClass().getSimpleName());
        }
        try {


            printDelimiter();
            Object objClass;
            objClass = selectClass(classes, scanner);
            printDelimiter();
            printClassInfo(objClass);
            createObject(objClass, scanner);
            scanner.nextLine();
            printDelimiter();
            updateObject(objClass, scanner);
            printDelimiter();
            invokeMethod(objClass, scanner);
        }
        catch (Exception e){
            System.err.println(e.getClass() + ": " + e.getMessage() );
        }

    }
    private static Object[] loadClasses() {
        Object[] ret = new Object[2];
        ret[0] = new User();
        ret[1] = new Car();
        return (ret);
    }
    private static Object selectClass(Object[] classes, Scanner scanner) {
        Object ret = null;
        String[] classNames = new String[classes.length];

        for (int i = 0; i < classes.length; i++) {
            classNames[i] = classes[i].getClass().getSimpleName();
        }

        System.out.println("Enter class name:");
        String className = scanner.nextLine();
        while (Arrays.stream(classNames).noneMatch(className::equals)) {
            System.out.println("Please enter correct class name.");
            className = scanner.nextLine();
        }

        for (int i = 0; i < classes.length; i++) {
            if (classes[i].getClass().getSimpleName().equals(className)) {
                ret = classes[i];
            }
        }
        return (ret);
    }

    private static Object scannerGetType(Class<?> param, Scanner scanner) {

        switch (param.getSimpleName().toLowerCase()) {
            case "string":
                return scanner.nextLine();
            case "int":
            case "integer":
                return scanner.nextInt();
            case "long":
                return scanner.nextLong();
            case "double":
                return scanner.nextDouble();
            case "float":
                return scanner.nextFloat();
            case "char":
            case "character":
                return scanner.next();
            default:
                throw new RuntimeException("Unrecognized type");
        }
    }

    private static Object createObject(Object subject, Scanner scanner) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Object ret = null;
        System.out.println("Let's create an object.");
        Constructor<?>[] constructors = subject.getClass().getDeclaredConstructors();
        Parameter[] parameters = null;
        int i;

        for (i = 0; i < constructors.length; i++) {
            if (constructors[i].getParameterCount() > 0) {
                parameters = constructors[i].getParameters();
                break;
            }
        }

        List<Object> constructorStuffFromConsole = new ArrayList<>();
        for (Parameter param : parameters) {
            System.out.print(param.getName() + "(" + param.getType().getSimpleName() + "): ");
            constructorStuffFromConsole.add(scannerGetType(param.getType(), scanner));
        }

        ret = constructors[i].newInstance(constructorStuffFromConsole.toArray());
        System.out.println("Object created: " + ret);
        return (ret);
    }

    private static void invokeMethod(Object subject, Scanner scanner) throws InvocationTargetException, IllegalAccessException {
        System.out.print("Enter name of method to invoke: [");

        for (Method m : subject.getClass().getDeclaredMethods()) {
            System.out.print(" " + m.getName());
        }

        System.out.println("]");
        Method method = null;
        Parameter[] parameters;
        List<Object> parsedParams = new ArrayList<>();
        while (method == null) {
            try {
                method = subject.getClass().getDeclaredMethod(scanner.nextLine());
            } catch (NoSuchMethodException e) {
                System.out.println("No such method! Try again!");
            }
        }
        parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            System.out.print("Enter " + parameter.getType().getSimpleName() + " value: ");
            parsedParams.add(scannerGetType(parameter.getType(), scanner));
        }
        Object ret = method.invoke(subject, parsedParams.toArray());
        if (ret != null) {
            System.out.println("Method returned: " + ret);
        }
    }

    private static Object updateObject(Object subject, Scanner scanner) throws IllegalAccessException {
        Field field = null;
        System.out.println("Updating object.\nEnter name of the field for changing:");
        while (field == null) {
            try {
                field = subject.getClass().getDeclaredField(scanner.nextLine());
            } catch (NoSuchFieldException e) {
                System.err.println("No such field, try again.");
            }
        }
        field.setAccessible(true);
        System.out.print("Enter (" + field.getType().getSimpleName() + ") value: ");
        field.set(subject, scannerGetType(field.getType(), scanner));
        System.out.println("Object updated: " + subject);
        return (subject);
    }

    private static void printClassInfo(Object subject) {
        Class<?> c = subject.getClass();
        Field[] fields = c.getDeclaredFields();
        Method[] methods = c.getDeclaredMethods();

        if (fields.length > 0) {
            System.out.println("fields:");
            for (Field f : fields) {
                System.out.println("\t\t" + f.getType().getSimpleName() + " " + f.getName());
            }
        }

        if (methods.length > 0) {
            System.out.println("methods:");
            for (Method m : methods) {
                System.out.print("\t\t" + m.getReturnType().getSimpleName() + " " + m.getName() + "(");
                Class<?>[] parameters = m.getParameterTypes();
                for (int i = 0; i < parameters.length; i++) {
                    System.out.print(parameters[i].getSimpleName());
                    if (i < parameters.length - 1)
                        System.out.print(", ");
                }
                System.out.println(")");
            }
        }
    }
    private static void printDelimiter() {
        int i = 0;
        while (i != 20)
        {
            System.out.print("=");
            i++;
        }
        System.out.println();
    }
}

