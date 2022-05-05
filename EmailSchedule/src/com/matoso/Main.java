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

            out.println("[1] - Novo Registo");

            if(!DataContext.DB.all().isEmpty()) {
                out.println("[2] - Consultar");
                out.println("[3] - Remover");
                out.println("[4] - Priorizar");
            }

            out.println("[0] - Sair");
            out.print("> ");

            int option = console.nextInt();

            switch (option) {
                case 1 -> AgendaWorker.add();
                case 2 -> AgendaWorker.list();
                case 3 -> AgendaWorker.remove();
                case 4 -> AgendaWorker.setAsImportant();
                case 0 -> {
                    out.println("\nObrigado!");
                    exit(200);
                }
                default -> out.println("Opção inválida!");
            }
        }
    }
}
