package com.restassured;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Test implements Serializable {
    int i = 10, j = 20;
}

public class SerializationDeserialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Test t1 = new Test();

        // Serialization
        FileOutputStream fos = new FileOutputStream("Test.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(t1);
        oos.close();

        // Deserialization
        FileInputStream fis = new FileInputStream("Test.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Test t2 = (Test) ois.readObject(); // Type Casting
        System.out.println(t2.i + "\t" + t2.j);
        ois.close();
    }
}
