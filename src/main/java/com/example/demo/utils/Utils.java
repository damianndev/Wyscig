package com.example.demo.utils;

import java.util.Random;
import java.util.UUID;

public class Utils {
    public static int losuj(int minimum, int maximum) {
        Random rn = new Random();
        return rn.nextInt(maximum - minimum + 1) + minimum;
    }

    public  static UUID stworzUUID(){
        return UUID.randomUUID();

    }
}
