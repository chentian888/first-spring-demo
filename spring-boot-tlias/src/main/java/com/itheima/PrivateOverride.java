package com.itheima;

import java.math.BigDecimal;
import java.time.Instant;

public class PrivateOverride {
    private void f() {
        System.out.println("private f()");
    }
    public static void main(String[] args) {
        PrivateOverride po = new Derived();
        po.f();
        Double.toString(22.34);
        Integer.valueOf("22");
        System.out.println(Instant.now());
        System.out.println(System.currentTimeMillis());
//        System.out.println(BigDecimal.valueOf(System.currentTimeMillis()));
        System.out.println(BigDecimal.valueOf(System.currentTimeMillis(), 1));
    }
}

class Derived extends PrivateOverride {
    public void f() { System.out.println("public f()"); }
}