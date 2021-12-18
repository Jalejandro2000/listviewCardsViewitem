package com.example.listviewcardsviewitem.util;

public class Utils {
    public static <T> T coalesce(T one, T two)
    {
        return one != null ? one : two;
    }
}
