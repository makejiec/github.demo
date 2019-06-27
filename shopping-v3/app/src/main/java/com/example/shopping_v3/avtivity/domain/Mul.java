package com.example.shopping_v3.avtivity.domain;

public class Mul {
    static {
        System.loadLibrary("native-lib");
    }

    //加法
    public static native int mul(int a);
}
