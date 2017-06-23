package com.zhanghao.service;

import java.util.Random;

/**
 * Created by zhanghao on 2017/6/23.
 */
public class Test {
    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(10));
        }
    }
}
