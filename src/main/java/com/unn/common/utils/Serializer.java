package com.unn.common.utils;

import java.io.*;

public class Serializer {
    private static final String version = "v1";

    public static void write(Object o, String path, String extension) {
        String fullPath = String.format("%s.%s.%s", path, version, extension);
        ObjectOutputStream objectOutputStream = null;
        try {
            RandomAccessFile raf = new RandomAccessFile(fullPath, "rw");
            FileOutputStream fos = new FileOutputStream(raf.getFD());
            objectOutputStream = new ObjectOutputStream(fos);
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
            fos.close();
            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
