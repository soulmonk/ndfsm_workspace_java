package com.soulmonk.ndfsm.appConsole;

import com.soulmonk.ndfsm.appConsole.helpers.UTF8ToAscii;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Reads file in UTF-8 encoding and output to STDOUT in ASCII with unicode
 * escaped sequence for characters outside of ASCII.
 * It is equivalent to:
 * native2ascii -encoding utf-8
 * <p/>
 * User: soulmonk
 * Date: 06.03.14
 * Time: 17:09
 */
public class UTF8ToAsciiFile {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java UTF8ToAscii <filename>");
            return;
        }

        BufferedReader r = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(args[0]),
                "UTF-8"
            )
        );
        String line = r.readLine();
        while (line != null) {
            System.out.println(UTF8ToAscii.unicodeEscape(line));
            line = r.readLine();
        }
        r.close();
    }
}