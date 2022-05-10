package com.matoso;

import java.util.Scanner;
import java.time.LocalDateTime;

import com.matoso.helpers.Helper;
import com.matoso.data.DataContext;
import com.matoso.services.AgendaWorker;

import static java.lang.System.*;

public class Main {
    private static final Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        DataContext.DB.apply();
        greetings();
        menu();
    }

    public static void menu() {
        while (true) {
            out.println("\n======== MENU ========\n");

            out.println("[1] - New Schedule");

            if (!DataContext.DB.all().isEmpty()) {
                out.println("[2] - Check");
                out.println("[3] - Remove");
                out.println("[4] - Set As Priority");
                out.println("[5] - Information");
            }

            out.println("[0] - Exit");
            out.print("> ");

            int option = console.nextInt();

            switch (option) {
                case 1 -> AgendaWorker.add();
                case 2 -> AgendaWorker.list();
                case 3 -> AgendaWorker.remove();
                case 4 -> AgendaWorker.setAsImportant();
                case 5 -> info();
                case 0 -> {
                    out.println("\nThank You!");

                    AgendaWorker.speech.speak("Thank you!");
                    AgendaWorker.speech.close();

                    exit(200);
                }
                default -> {
                    out.println("\nOption not allowed!");
                    AgendaWorker.speech.speak("Option not allowed!");
                }
            }
        }
    }
    public static void greetings() {
        var hour = LocalDateTime.now().getHour();

        if(hour < 12) out.print("Good morning, ");
        if (hour >= 12 && hour < 18) out.print("Good afternoon, ");
        if (hour >= 18) out.print("Good evening, ");

        out.println(System.getProperty("user.name") + "!");
    }
    public static void info() {
        out.println("\n======== INFORMATION ========\n");

        try {
            out.println("Application: EmailSchedule");
            out.println("Application Version: 1.0.0.0");
            out.println("Application Package: " + Class.forName("com.matoso.Main").getPackageName());
            out.println("Java Version: " + System.getProperty("java.version"));
            out.println("OS: " + System.getProperty("os.name") + " version " + System.getProperty("os.version"));

            var dateAndTime = LocalDateTime.now();

            out.printf("Date: %s %s, %s at %s:%s%n",
                    dateAndTime.getMonth().name(), dateAndTime.getDayOfMonth(), dateAndTime.getYear(),
                    dateAndTime.getHour(), dateAndTime.getMinute());
        } catch(ClassNotFoundException e) {
            System.out.println("[ERROR] " + e.getMessage());
            System.out.println("_______________________\n");
        }

        Helper.clickToContinue();
    }
}
