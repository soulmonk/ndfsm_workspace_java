package com.soulmonk.ndfsm.appConsole;

import com.soulmonk.ndfsm.appConsole.helpers.UTF8ToAscii;

import java.io.Console;

public class UTF8ToAsciiConsole {
    public static void main(String args[]) {
        Console cons = System.console();
        if (cons == null) {
            System.out.println("cons is NULL!!!");
            return;
        }
        System.out.println("Enter something here : ");
        String s = cons.readLine();
        System.out.println(UTF8ToAscii.unicodeEscape(s));
    }
}