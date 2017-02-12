package com.thirdOverride;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class OverrideThirdPartyClassMapper {

    private static OverrideThirdPartyClassMapper inst = new OverrideThirdPartyClassMapper();

    private Map<String, Date> classesDates = new HashMap<String, Date>();

    public static OverrideThirdPartyClassMapper getInst() {
        return inst;
    }

    private OverrideThirdPartyClassMapper() {
        init();
    }

    private void init() {

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
                    classesDates.put(name, date);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Date getClassDate(String className) {
        return classesDates.get(className);
    }

}
