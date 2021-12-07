package com.company.springexample;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private static Map<Class<?>,Object> map = new HashMap<>();

    public ApplicationContext(Class<?> config){
        try {
            Spring.initializeSpringContext(config);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public <T> T getBean(Class<T> clss) throws IllegalAccessException {
        T obj = (T) map.get(clss);
        Field[] declaredFields = clss.getDeclaredFields();
        injectBean(obj, declaredFields);
        return obj;
    }

    private <T> void injectBean(T obj, Field[] declaredFields) throws IllegalAccessException {
        for (Field field: declaredFields) {
            if(field.isAnnotationPresent(Autowired.class)){
                field.setAccessible(true);
                Class<?> type = field.getType();
                Object innerObject = map.get(type);
                field.set(obj,innerObject);
                Field[] declaredFields1 = type.getDeclaredFields();
                injectBean(innerObject,declaredFields1);
            }
        }
    }

    private static class Spring {
        private static void initializeSpringContext(Class<?> clss) throws ClassNotFoundException, NoSuchMethodException,
                InvocationTargetException, InstantiationException, IllegalAccessException {

            if(!clss.isAnnotationPresent(Configuration.class)){
                throw new RuntimeException(clss.getSimpleName() + " must be a configuration class");
            }
            ComponentScan annotation = clss.getAnnotation(ComponentScan.class);
            String value = annotation.value(); //esto nos dice en que paquete estan los "beans"

            String packageStructure = "src/"+value.replace(".","/");
            File[] files = findClasses(new File(packageStructure));

            for (File file: files) {
                String name = value + "." + file.getName().replace(".java","");
                Class<?> loadingClass = Class.forName(name);
                if(loadingClass.isAnnotationPresent(Component.class)){
                    Constructor<?> constructor = loadingClass.getConstructor();
                    Object newInstance = constructor.newInstance();
                    map.put(loadingClass,newInstance);
                }
            }
        }

        private static File[] findClasses(File file) {
            if(!file.exists()){
                throw new RuntimeException("Package " + file + " does not exists.");
            }
            return file.listFiles(e -> e.getName().endsWith(".java"));
        }
    }
}
