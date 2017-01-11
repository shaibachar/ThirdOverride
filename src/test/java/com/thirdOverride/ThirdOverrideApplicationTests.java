package com.thirdOverride;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.thirdOverride.dataTypes.TestObjectChild;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThirdOverrideApplicationTests {

    @Autowired
    private TestObjectChild testObjectChild;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testMe() {
        testObjectChild.printer();
    }

    @Test
    public void printClassPath() {
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
                    String name = e.getName();
                    System.out.println(jar.getFile() + ": " + name);
                }

                String className = "";
                Class<?> forName = Class.forName(className);
                forName.getDeclaredConstructors();
                forName.getDeclaredMethods();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
