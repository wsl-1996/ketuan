package com.skqtec.controller;

import java.sql.Timestamp;

public class merchantsTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void createProduct() {
        String a= "2018-07-20 23:00:00";
        String b="2018-07-20 22:59:00";
        Timestamp a1 = Timestamp.valueOf(a);
        Timestamp b1 = Timestamp.valueOf(b);
        long t1 = a1.getTime();
        long t2 = b1.getTime();
        long delta  = t1-t2;

        System.out.println(delta);
    }

    @org.junit.Test
    public void getAllMerchants() {
    }

    @org.junit.Test
    public void queryMerchants() {
    }
}