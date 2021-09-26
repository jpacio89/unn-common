package com.unn.common.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializer {
    private static final String version = "v1";

    public static void write(Object o, String path, String extension) {
        try {
            String fullPath = String.format("%s.%s.%s", path, version, extension);
            FileOutputStream fileOut = new FileOutputStream(fullPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in %s", fullPath);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
