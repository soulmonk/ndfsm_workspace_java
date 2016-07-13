package com.soulmonk.ndfsm.appConsole;

/**
 * User: SoulMonk
 * Date: 7/12/16
 * Time: 5:45 PM
 */
public class TestBits {
    public static void main(String[] args) {
        // 0x0FFFFFFF
        int x = 123123;
        System.out.println(Integer.toBinaryString(x));
        System.out.println(x);
        x = x & 255;
        System.out.println(Integer.toBinaryString(x));
        System.out.println(x);
        int y = 255;
        x = (0xFF) & y;
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(y));
        System.out.println(x);
        System.out.println(y);
    }
}
