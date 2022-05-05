package com.matoso;

import com.matoso.data.AccountContext;
import com.matoso.entities.Account;
import com.matoso.services.AccountService;

import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.out;

public class Main
{
    public static class DATA
    {
        public static boolean LOGGED = false;
        public static Account CURRENT_ACCOUNT = new Account();
    }

    public static void main(String[] args)
    {
        menu();
    }

    public static void menu()
    {
        out.println("SEJA-BENVINDO\n");
        var console = new Scanner(System.in);

        out.println("1 - Abrir Conta");
        out.println("2 - Entrar");
        out.println("3 - Sair");

        out.print("> ");

        var op = console.nextInt();

        switch (op)
        {
            case 1:
                var account = new AccountService();
                account.openAccount();
                menu();
                break;
            case 2: login();
                break;
            case 3: exit(1);
                break;
            default: menu();
                break;
        }
    }

    public static  void inMenu()
    {
        out.println("\nSEJA-BENVINDO\n");
        var console = new Scanner(System.in);

        out.println("1 - Consultar");
        out.println("2 - Depositar");
        out.println("3 - Transferência");
        out.println("4 - Levantar");
        out.println("5 - Fechar Conta");

        out.print("> ");

        var op = console.nextInt();

        var accountService = new AccountService();

        do {
            switch (op)
            {
                case 1:
                    accountService.checkBalance(DATA.CURRENT_ACCOUNT.accountNumber);
                    inMenu();
                    break;
                case 2:
                    accountService.deposit(DATA.CURRENT_ACCOUNT.accountNumber);
                    inMenu();
                    break;
                case 3:
                    accountService.transfer(DATA.CURRENT_ACCOUNT.accountNumber);
                    inMenu();
                    break;
                case 4:
                    accountService.lift(DATA.CURRENT_ACCOUNT.accountNumber);
                    inMenu();
                    break;
                case 5:
                    accountService.closeAccount(DATA.CURRENT_ACCOUNT.accountNumber);
                    inMenu();
                    break;
                default: out.println("Opção inválida!");

            }
        } while (op <= 0 || op > 4);

    }

    public static void login()
    {
        var loginAttempts = 3;
        var console = new Scanner(System.in);


        do {
            out.print("Insira o número de conta: ");
            var accountNumber = console.nextLine();

            out.print("Insira a sua palavra-passe: ");
            var password = console.nextLine();

            var context = new AccountContext();

            var account = context.login(Integer.parseInt(accountNumber), password);

            if(account != null)
            {
                out.println("Logado.");

                DATA.LOGGED = true;
                DATA.CURRENT_ACCOUNT = account;

                break;
            }

            out.println("Credenciais erradas.");
            loginAttempts--;

        } while (loginAttempts > 0 && !DATA.LOGGED);

        if(loginAttempts <= 0)
        {
            out.print("\nSaindo\nErrou a palavra-passe muitas vezes.");
            exit(1);
            return;
        }

        inMenu();
    }
}
