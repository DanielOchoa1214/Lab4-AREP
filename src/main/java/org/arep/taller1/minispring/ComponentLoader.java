package org.arep.taller1.minispring;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

import org.arep.taller1.minispark.Request;

/**
 * Class that manages the Methods to be run when a HTTP requests comes in
 *  when a HTTP
 */
public class ComponentLoader {
    private static final Map<String, Method> GET_SERVICES = new HashMap<>();
    private static final Map<String, Method> POST_SERVICES = new HashMap<>();

    private static final String PACKAGE_NAME = "org/arep/taller1/springrestclient";

    /**
     * Method that searches the packet "org/arep/taller1/springrestclient" for Spring components and saves
     * the methods to be run
     * @throws ClassNotFoundException When the ClassLoader couldn't find the class
     */
    public static void loadComponents() throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL packageURL = classLoader.getResource(PACKAGE_NAME);

        loadMethods(packageURL);
    }

    /**
     * Method that checks if a given method exists
     * @param endpoint Endpoint of the method
     * @param verb HTTP verb the method was saved as
     * @return A Method object representing the method
     */
    public static Method search(String endpoint, String verb){
        switch (verb){
            case "GET":
                return GET_SERVICES.get(endpoint);
            case "POST":
                return POST_SERVICES.get(endpoint);
        }
        return null;
    }

    /**
     * Method that calls a stored method
     * @param method Method object representing the method
     * @param param Request object that will be passed as parameter to the method
     * @return String with the response of the method called
     * @throws InvocationTargetException If the method failed to be executed
     * @throws IllegalAccessException If the method can't be accessed
     */
    public static String execute(Method method, Request param) throws InvocationTargetException, IllegalAccessException {
        return (String) method.invoke(null, param);
    }


    /*
    Method that loads methods annotated with a MiniSpring annotation
     */
    private static void loadMethods(URL packageURL) throws ClassNotFoundException {
        if (packageURL != null) {
            String packagePath = packageURL.getPath();
            if (packagePath != null) {
                File packageDir = new File(packagePath);
                if (packageDir.isDirectory()) {
                    File[] files = packageDir.listFiles();
                    assert files != null;
                    checkComponent(files);
                }
            }
        }
    }

    /*
    Method that checks if a class is annotated with @Component
     */
    private static void checkComponent(File[] files) throws ClassNotFoundException {
        for (File file : files) {
            String className = file.getName();
            if (className.endsWith(".class")) {
                className = PACKAGE_NAME + "/" + className.substring(0, className.length() - 6);
                Class<?> clazz = Class.forName(className.replace("/", "."));
                if(clazz.isAnnotationPresent(Component.class)){
                    saveComponentMethods(clazz);
                }
            }
        }
    }

    /*
    Method that saves the Method into the Map
     */
    private static void saveComponentMethods(Class<?> clazz){
        Method[] methods = clazz.getDeclaredMethods();
        for(Method m : methods){
            if(m.isAnnotationPresent(GetMapping.class)){
                String endpoint = m.getAnnotation(GetMapping.class).value();
                GET_SERVICES.put(endpoint, m);
            } else if(m.isAnnotationPresent(PostMapping.class)){
                String endpoint = m.getAnnotation(PostMapping.class).value();
                POST_SERVICES.put(endpoint, m);
            }
        }
    }

}
