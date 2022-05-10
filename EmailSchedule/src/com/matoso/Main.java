/*
    AGENDAMENTO POR EMAIL

    GRUPO: 8 | LCC1M | PR2

    Bernarda Gonçalves
    Jeremias Dizinga
    Joaquim José (Líder)
    Sara Samuel
 */

package com.matoso;

import com.matoso.data.Seed;
import com.matoso.data.DataContext;
import com.matoso.services.AgendaWorker;
import com.matoso.services.SpeechService;

import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.out;

public class Main {
    private static final Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        Seed.apply();
        menu();
    }

    public static void menu() {
        while (true) {
            out.println("\n======== MENU ========\n");

            out.println("[1] - New Schedule");

            if(!DataContext.DB.all().isEmpty()) {
                out.println("[2] - Check");
                out.println("[3] - Remove");
                out.println("[4] - Set As Priority");
            }

            out.println("[0] - Exit");
            out.print("> ");

            int option = console.nextInt();

            switch (option) {
                case 1 -> AgendaWorker.add();
                case 2 -> AgendaWorker.list();
                case 3 -> AgendaWorker.remove();
                case 4 -> AgendaWorker.setAsImportant();
                case 0 -> {
                    out.println("\nThank You!");
                    AgendaWorker.speech.speak("Thank you!");
                    exit(200);
                }
                default -> {
                    out.println("Option not allowed!");
                    AgendaWorker.speech.speak("Option not allowed!");
                }
            }
        }
    }
}
