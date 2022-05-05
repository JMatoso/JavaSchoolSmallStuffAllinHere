package com.matoso.services;

import com.matoso.data.DataContext;
import com.matoso.entites.Agenda;

import java.time.LocalDateTime;
import java.util.Scanner;

import static java.lang.System.out;

public class AgendaWorker {
    public static void add() {
        out.println("\n======== NOVO REGISTO ========\n");

        var console = new Scanner(System.in);

        var agenda = new Agenda();

        out.print("Insira o seu nome: ");
        agenda.setName(console.nextLine());

        out.print("Insira o seu email: ");
        agenda.setEmail(console.nextLine());

        out.print("Insira a hora (hh mm): ");
        var stringHour = console.nextLine();

        out.print("Insira o dia (dd): ");
        var day = console.nextInt();

        out.print("Insira o mês (mm): ");
        var month = console.nextInt();

        out.print("Insira o ano (yyyy): ");
        var year = console.nextInt();

        var hour = stringHour.split(" ");

        agenda.setDate(hour.length > 1 ?
                LocalDateTime.of(year, month, day, Integer.parseInt(hour[0]), Integer.parseInt(hour[1])) :
                LocalDateTime.of(year, month, day, Integer.parseInt(hour[0]), 0));

        DataContext.DB.add(agenda);
    }

    public static void remove() {
        out.println("\n======== REMOVER REGISTO ========\n");

        var console = new Scanner(System.in);

        out.print("Insira o email: ");
        var agenda = DataContext.DB.get(console.nextLine());

        if(agenda != null) {
            DataContext.DB.remove(agenda);
            return;
        }

        out.println("Registo não encontrado.");
    }

    public static void setAsImportant() {
        out.println("\n======== PRIORIZAR ========\n");

        var console = new Scanner(System.in);

        out.print("Insira o email: ");
        var agenda = DataContext.DB.get(console.nextLine());

        if(agenda != null) {
            agenda.setImportant(true);
            DataContext.DB.update(agenda);

            return;
        }

        out.println("Registo não encontrado.");
    }

    public static void list() {
        out.println("\n======== CONSULTAR ========\n");

        var console = new Scanner(System.in);

        out.println("[1] - Consultar Um");
        out.println("[2] - Consultar Todos");
        out.print("> ");

        int option = console.nextInt();

        if(option == 1) {
            out.print("Insira o email: ");
            console.nextLine();
            var agenda = DataContext.DB.get(console.nextLine());

            if(agenda != null) {
                out.println();
                out.printf("ID: %d%n", agenda.getId());
                out.printf("Nome: %s%n", agenda.getName());
                out.printf("Email: %s%n", agenda.getEmail());
                out.printf("Priorizado: %s%n", agenda.isImportant() ? "Sim" : "Não");
                out.printf("Date: %s %s, %s às %s:%s%n",
                        agenda.getDate().getMonth().name(), agenda.getDate().getDayOfMonth(), agenda.getDate().getYear(),
                        agenda.getDate().getHour(), agenda.getDate().getMinute());
                return;
            }

            out.println("Registo não encontrado.");
            return;
        }

        if(option == 2) {
            out.println();
            for (var agenda : DataContext.DB.all()) {
                out.printf("ID: %d%n", agenda.getId());
                out.printf("Nome: %s%n", agenda.getName());
                out.printf("Email: %s%n", agenda.getEmail());
                out.printf("Priorizado: %s%n", agenda.isImportant() ? "Sim" : "Não");
                out.printf("Date: %s %d, %d às %d:%d%n",
                        agenda.getDate().getMonth().name(), agenda.getDate().getDayOfMonth(), agenda.getDate().getYear(),
                        agenda.getDate().getHour(), agenda.getDate().getMinute());

                out.println("___________________________\n");
            }
        }
    }
}
