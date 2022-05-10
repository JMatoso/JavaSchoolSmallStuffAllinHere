package com.matoso.services;

import com.matoso.data.DataContext;
import com.matoso.entites.Agenda;

import java.time.LocalDateTime;
import java.util.Scanner;

import static java.lang.System.out;

public class AgendaWorker {
    public static final SpeechService speech = new SpeechService();

    public static void add() {
        out.println("\n======== NEW SCHEDULE ========\n");

        var agenda = new Agenda();
        var console = new Scanner(System.in);

        out.print("Insert your name: ");
        agenda.setName(console.nextLine());

        out.print("Insert your email: ");
        agenda.setEmail(console.nextLine());

        out.print("Insert the time (hh mm): ");
        var stringHour = console.nextLine();

        out.print("Insert the day (dd): ");
        var day = console.nextInt();

        out.print("Insert the month (mm): ");
        var month = console.nextInt();

        out.print("Insert the year (yyyy): ");
        var year = console.nextInt();

        var hour = stringHour.split(" ");

        agenda.setDate(hour.length > 1 ?
                LocalDateTime.of(year, month, day, Integer.parseInt(hour[0]), Integer.parseInt(hour[1])) :
                LocalDateTime.of(year, month, day, Integer.parseInt(hour[0]), 0));

        out.println();
        DataContext.DB.add(agenda);
    }

    public static void remove() {
        out.println("\n======== REMOVE SCHEDULE ========\n");

        var console = new Scanner(System.in);

        out.print("Insert the email: ");
        var agenda = DataContext.DB.get(console.nextLine());

        if(agenda != null) {
            DataContext.DB.remove(agenda);
            return;
        }

        out.println("Register not found.");
        speech.speak("Register not found.");
    }

    public static void setAsImportant() {
        out.println("\n======== SET AS PRIORITY ========\n");

        var console = new Scanner(System.in);

        out.print("Insert the email: : ");
        var agenda = DataContext.DB.get(console.nextLine());

        if(agenda != null) {
            agenda.setImportant(true);
            DataContext.DB.update(agenda);

            return;
        }

        out.println("Register not found.");
        speech.speak("Register not found.");
    }

    public static void list() {
        out.println("\n======== CHECK OUT ========\n");

        var console = new Scanner(System.in);

        out.println("[1] - Check One");
        out.println("[2] - Check All");
        out.print("> ");

        int option = console.nextInt();

        if(option == 1) {
            out.print("Insert the email: ");
            console.nextLine();
            var agenda = DataContext.DB.get(console.nextLine());

            if(agenda != null) {
                out.println();
                out.printf("ID: %d%n", agenda.getId());
                out.printf("Name: %s%n", agenda.getName());
                out.printf("Email: %s%n", agenda.getEmail());
                out.printf("Priority: %s%n", agenda.isImportant() ? "Yes" : "Not");
                out.printf("Date: %s %s, %s às %s:%s%n",
                        agenda.getDate().getMonth().name(), agenda.getDate().getDayOfMonth(), agenda.getDate().getYear(),
                        agenda.getDate().getHour(), agenda.getDate().getMinute());
                return;
            }

            out.println("Register not found.");
            speech.speak("Register not found.");
            return;
        }

        if(option == 2) {
            out.println();
            for (var agenda : DataContext.DB.all()) {
                out.printf("ID: %d%n", agenda.getId());
                out.printf("Name: %s%n", agenda.getName());
                out.printf("Email: %s%n", agenda.getEmail());
                out.printf("Priority: %s%n", agenda.isImportant() ? "Yes" : "Not");
                out.printf("Date: %s %d, %d às %d:%d%n",
                        agenda.getDate().getMonth().name(), agenda.getDate().getDayOfMonth(), agenda.getDate().getYear(),
                        agenda.getDate().getHour(), agenda.getDate().getMinute());

                out.println("___________________________\n");
            }
        }
    }
}
