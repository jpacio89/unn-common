package com.unn.common.utils;

import java.io.*;

public class Serializer {
    private static final String version = "v1";

    public static void write(Object o, String path, String extension) {
        try {
            String fullPath = String.format("%s.%s.%s", path, version, extension);
            File f = new File(fullPath);
            if (!f.exists()) {
                f.createNewFile();
            }
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

    public static Object read(String path, String extension) {
        String fullPath = String.format("%s.%s.%s", path, version, extension);
        Object o = null;
        try {
            FileInputStream fileIn = new FileInputStream(fullPath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            o = in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return o;
        } catch (ClassNotFoundException c) {
            System.out.printf("Deserializing not possible for object %s", fullPath);
            c.printStackTrace();
            return o;
        }
        return o;
    }


}
