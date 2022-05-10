package com.matoso.services;

import com.matoso.helpers.Helper;
import com.matoso.entites.Agenda;
import com.matoso.data.DataContext;

import java.time.LocalDateTime;
import java.util.Scanner;

import static java.lang.System.out;

public class AgendaWorker {
    public static final SpeechService speech = new SpeechService();

    public static void add() {
        out.println("\n======== NEW SCHEDULE ========\n");

        speech.speak("New schedule.");

        var agenda = new Agenda();
        var console = new Scanner(System.in);

        out.print("Insert your name: ");
        speech.speak("Insert your name.");
        agenda.setName(console.nextLine());

        out.print("Insert your email: ");
        speech.speak("Insert your email.");
        agenda.setEmail(console.nextLine());

        out.print("Insert the time (hh mm): ");
        speech.speak("Insert the time.");
        var stringHour = console.nextLine();

        out.print("Insert the day (dd): ");
        speech.speak("Insert the day.");
        var day = console.nextInt();

        out.print("Insert the month (mm): ");
        speech.speak("Insert the month.");
        var month = console.nextInt();

        out.print("Insert the year (yyyy): ");
        speech.speak("Insert the year.");
        var year = console.nextInt();

        out.println("\nScheduling in progress...");
        speech.speak("Scheduling in progress...");

        var hour = stringHour.split(" ");

        agenda.setDate(hour.length > 1 ?
                LocalDateTime.of(year, month, day, Integer.parseInt(hour[0]), Integer.parseInt(hour[1])) :
                LocalDateTime.of(year, month, day, Integer.parseInt(hour[0]), 0));

        out.println();
        DataContext.DB.add(agenda);
        Helper.clickToContinue();
    }

    public static void remove() {
        out.println("\n======== REMOVE SCHEDULE ========\n");

        speech.speak("Remove schedule.");

        var console = new Scanner(System.in);

        out.print("Insert the email: ");
        speech.speak("Insert the email.");

        var agenda = DataContext.DB.get(console.nextLine());

        if (agenda != null) {
            DataContext.DB.remove(agenda);
            Helper.clickToContinue();
            return;
        }

        out.println("Register not found.");
        speech.speak("Register not found.");
        Helper.clickToContinue();
    }

    public static void setAsImportant() {
        out.println("\n======== SET AS PRIORITY ========\n");

        speech.speak("Set as priority.");

        var console = new Scanner(System.in);

        out.print("Insert the email: : ");
        speech.speak("Insert the email.");

        var agenda = DataContext.DB.get(console.nextLine());

        if (agenda != null) {
            agenda.setImportant(true);
            DataContext.DB.update(agenda);
            Helper.clickToContinue();
            return;
        }

        out.println("Register not found.");
        speech.speak("Register not found.");
        Helper.clickToContinue();
    }

    public static void list() {
        out.println("\n======== CHECK OUT ========\n");

        speech.speak("Check out.");

        var console = new Scanner(System.in);

        out.println("[1] - Check One");
        out.println("[2] - Check All");
        out.print("> ");

        int option = console.nextInt();

        if (option == 1) {
            speech.speak("Checking one.");
            out.print("Insert the email: ");
            speech.speak("Insert the email.");

            console.nextLine();
            var agenda = DataContext.DB.get(console.nextLine());

            if (agenda != null) {
                out.println();
                out.printf("ID: %d%n", agenda.getId());
                out.printf("Name: %s%n", agenda.getName());
                out.printf("Email: %s%n", agenda.getEmail());
                out.printf("Priority: %s%n", agenda.isImportant() ? "Yes" : "Not");
                out.printf("Date: %s %s, %s at %s:%s%n",
                        agenda.getDate().getMonth().name(), agenda.getDate().getDayOfMonth(), agenda.getDate().getYear(),
                        agenda.getDate().getHour(), agenda.getDate().getMinute());

                Helper.clickToContinue();
                return;
            }

            out.println("Register not found.");
            speech.speak("Register not found.");
            Helper.clickToContinue();
            return;
        }

        if (option == 2) {
            out.println();
            speech.speak("Checking all.");
            out.println("Showing (" + (long) DataContext.DB.all().size() + ") registers.\n");
            speech.speak("Showing " + (long) DataContext.DB.all().size() + " registers.");

            for (var agenda : DataContext.DB.all()) {
                out.printf("ID: %d%n", agenda.getId());
                out.printf("Name: %s%n", agenda.getName());
                out.printf("Email: %s%n", agenda.getEmail());
                out.printf("Priority: %s%n", agenda.isImportant() ? "Yes" : "Not");
                out.printf("Date: %s %d, %d at %d:%d%n",
                        agenda.getDate().getMonth().name(), agenda.getDate().getDayOfMonth(), agenda.getDate().getYear(),
                        agenda.getDate().getHour(), agenda.getDate().getMinute());

                out.println("___________________________\n");
            }

            Helper.clickToContinue();
        }
    }
}
