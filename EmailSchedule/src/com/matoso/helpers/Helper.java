package com.matoso.helpers;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Helper {
    public static void clearConsole()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void clickToContinue() {
        try {
            System.out.println("\nPress <ENTER> to continue...");
            var br = new BufferedReader(new InputStreamReader(System.in));
            br.readLine();

            clearConsole();
        } catch (IOException e) {
            System.out.println("[ERROR] " + e.getMessage());
            System.out.println("_______________________\n");
        }
    }
}
