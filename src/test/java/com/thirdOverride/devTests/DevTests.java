/*package com.thirdOverride.devTests;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevTests {

    @Test
    public void mapClassPathClassesDates() {
        Map<String, Date> classesDates = new HashMap<String, Date>();
        ClassLoader c1 = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) c1).getURLs();

        try {
            for (URL jar : urls) {
                ZipInputStream zip = new ZipInputStream(jar.openStream());
                while (true) {
                    ZipEntry e = zip.getNextEntry();
                    if (e == null) {
                        break;
                    }
                    String name = e.getName().replace('/', '.').replaceAll(".class", "");
                    FileTime lastModifiedTime = e.getLastModifiedTime();
                    long l = lastModifiedTime.to(TimeUnit.MILLISECONDS);
                    Date date = new Date(l);
                    System.out.println(jar.getFile() + ": " + name);
                    classesDates.put(name, date);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String className : classesDates.keySet()) {
            Date date = classesDates.get(className);
            System.out.println("c:" + className + "-modifiedOn>" + date);
        }
    }

    @Test
    public void mapClassPathMethods() {
        Map<String, ArrayList<Executable>> classesMethods = new HashMap<String, ArrayList<Executable>>();
        ClassLoader c1 = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader) c1).getURLs();

        try {
            for (URL jar : urls) {
                ZipInputStream zip = new ZipInputStream(jar.openStream());
                while (true) {
                    ZipEntry e = zip.getNextEntry();
                    if (e == null) {
                        break;
                    }
                    String name = e.getName().replace('/', '.').replaceAll(".class", "");
                    System.out.println(jar.getFile() + ": " + name);

                    classesMethods.put(name, new ArrayList<Executable>());
                }

            }

            for (String className : classesMethods.keySet()) {
                Class<?> forName = Class.forName(className);
                Constructor<?>[] declaredConstructors = forName.getDeclaredConstructors();
                for (Constructor<?> constructor : declaredConstructors) {
                    classesMethods.get(className).add(constructor);
                }

                Method[] declaredMethods = forName.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    classesMethods.get(className).add(method);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
*/