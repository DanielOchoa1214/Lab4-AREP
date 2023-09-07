package org.arep.taller1.minispring;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

import org.arep.taller1.minispark.Request;

public class ComponentLoader {
    private static final Map<String, Method> GET_SERVICES = new HashMap<>();
    private static final Map<String, Method> POST_SERVICES = new HashMap<>();

    public static void loadComponents() throws ClassNotFoundException {
        String packageName = "org/arep/taller1/springrestclient";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL packageURL = classLoader.getResource(packageName);

        if (packageURL != null) {
            String packagePath = packageURL.getPath();
            if (packagePath != null) {
                File packageDir = new File(packagePath);
                if (packageDir.isDirectory()) {
                    File[] files = packageDir.listFiles();
                    for (File file : files) {
                        String className = file.getName();
                        if (className.endsWith(".class")) {
                            className = packageName + "/" + className.substring(0, className.length() - 6);
                            Class<?> clazz = Class.forName(className.replace("/", "."));
                            if(clazz.isAnnotationPresent(Component.class)){
                                System.out.println("LLamaste a: " + clazz);
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
                    }
                }
            }
        }
    }

    public static Method search(String endpoint, String verb){
        switch (verb){
            case "GET":
                return GET_SERVICES.get(endpoint);
            case "POST":
                return POST_SERVICES.get(endpoint);
        }
        return null;
    }

    public static String execute(Method method, Request param) throws InvocationTargetException, IllegalAccessException {
        return (String) method.invoke(null, param);
    }
}
