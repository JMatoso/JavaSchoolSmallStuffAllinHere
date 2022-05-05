package com.matoso.extensions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GeneralExtensions {
    public static class clear
    {
        public static void console()
        {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public static class readKey
    {
        public static void key()
        {
            System.out.println("Pressione <ENTER> para continuar...");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class readAndClear
    {
        public void pressAndClear()
        {
            new readKey();
            new clear();
        }
    }
}
