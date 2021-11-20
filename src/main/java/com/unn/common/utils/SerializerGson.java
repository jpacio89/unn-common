package com.unn.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class SerializerGson {
    private static final String version = "v1";

    public static void write(Object o, String path, String extension) {
        try {
            String fullPath = String.format("%s.%s.%s", path, version, extension);
            Writer writer = new FileWriter(fullPath);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(o, writer);
            System.out.printf("Serialized data is saved in %s%n", fullPath);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Object read(String path, String extension, Class classOf) {
        String fullPath = String.format("%s.%s.%s", path, version, extension);
        Object o = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            o = gson.fromJson(new FileReader(fullPath), classOf);
        } catch (FileNotFoundException e) {
            System.out.printf("Deserializing not possible for object %s", fullPath);
            e.printStackTrace();
        }
        return o;
    }


}
