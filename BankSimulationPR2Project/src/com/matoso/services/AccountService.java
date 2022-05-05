package com.matoso.services;

import com.matoso.data.AccountContext;
import com.matoso.data.SAccountContext;
import com.matoso.entities.Account;
import com.matoso.entities.SavingAccount;
import com.matoso.extensions.GeneralExtensions;

import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.String.format;
import static java.lang.System.out;

public class AccountService
{
    private Scanner _scanner = new Scanner(System.in);
    private AccountContext _account = new AccountContext();
    private SAccountContext _sAccount = new SAccountContext();

    private FileService _fileService;

    public AccountService()
    {
        out.println("\nOlá, seja bem-vindo ao gestor de contas!\n");
    }

    public void openAccount()
    {
        var action = new GeneralExtensions();
        double balance;
        int option, limit, time;
        String ownerName, password;

        do
        {
            out.print("Escolha um tipo de conta.\n1 - Conta Poupança\n2 - Conta Corrente\n> ");
            option = _scanner.nextInt();

            out.print("\nInsira o seu nome: ");
            ownerName = _scanner.nextLine();
            ownerName = _scanner.nextLine();

            out.print("Insira o seu saldo: ");
            balance = _scanner.nextDouble();

            out.print("Insira o seu Password: ");
            password = _scanner.nextLine();
            password = _scanner.nextLine();

            out.print("Defina o seu limite diário: ");
            limit = _scanner.nextInt();

            switch (option)
            {
                case 1:
                    new GeneralExtensions.clear();
                    var savingAccount = new SavingAccount();

                    out.print("Defina o seu tempo para levantamento (em anos): ");
                    time = _scanner.nextInt();

                    savingAccount.setOwnerName(ownerName);
                    savingAccount.setLimit(limit);
                    savingAccount.setBalance(balance);
                    savingAccount.setPassword(password);
                    savingAccount.setPeriodToTakeMoneyInYears(time);

                    _sAccount.add(savingAccount);

                    new GeneralExtensions.readAndClear().pressAndClear();

                    out.println("\n\nConta criada com sucesso.");
                    out.println(format(
                            "Informações da conta.\n\nProprietário: %s\nSaldo: %f\nNúmero da Conta: %d\nTipo de Conta: Poupança\nTempo Para Inicio de Levantamentos: %d\nData de Abertura: %s\n\n",
                            savingAccount.getOwnerName(), savingAccount.getBalance(), savingAccount.getAccountNumber(), savingAccount.getPeriodToTakeMoneyInYears(), savingAccount.getOpenedDate()));

                    //_fileService.saveData(DataCollection.DATA_CONTEXT.CURRENT_ACCOUNTS);
                    break;
                case 2:
                    new GeneralExtensions.clear();
                    var account = new Account();

                    account.setOwnerName(ownerName);
                    account.setLimit(limit);
                    account.setBalance(balance);
                    account.setPassword(password);

                    _account.add(account);

                    new GeneralExtensions.readAndClear();

                    out.println("\n\nConta criada com sucesso.");
                    out.println(format(
                            "Informações da conta.\n\nProprietário: %s\nSaldo: %f\nNúmero da Conta: %d\nTipo de Conta: Corrente\nData de Abertura: %s\n\n",
                            account.getOwnerName(), account.getBalance(), account.getAccountNumber(),account.getOpenedDate()));

                    //_fileService.<List<Account>>saveData(DataCollection.DATA_CONTEXT.CURRENT_ACCOUNTS);
                    break;
                default: out.println("Tipo de conta inválido!");
                    break;
            }
        } while (option <= 0 && option > 2);
    }

    public void closeAccount(int accountNumber)
    {
        var account = _account.get(accountNumber);

        if(account != null)
        {
            _account.remove(accountNumber);
            return;
        }

        var account2 = _sAccount.get(accountNumber);

        if(account2 != null)
        {
            _sAccount.remove(accountNumber);
            return;
        }
    }

    public void checkBalance(int accountNumber)
    {
        var account = _account.get(accountNumber);

        if(account != null)
        {
            out.println("O seu saldo atual é de: " + account.getBalance());
            return;
        }

        var account2 = _sAccount.get(accountNumber);

        if(account2 != null)
        {
            out.println("O seu saldo atual é de: " + account2.getBalance());
            return;
        }

        out.println("\nConta não encontrada.");
    }

    public void deposit(int accountNumber)
    {
        var account = _account.get(accountNumber);

        if(account != null)
        {
            out.print("Insire a quantia que prentende depositar: ");
            var newValue = _scanner.nextDouble();

            account.setBalance(account.getBalance() + newValue);
            out.println("Quantia depositada!");

            return;
        }

        var account2 = _sAccount.get(accountNumber);

        if(account2 != null)
        {
            out.print("Insire a quantia que prentende depositar: ");
            var newValue = _scanner.nextDouble();

            account2.setBalance(account2.getBalance() + newValue);
            out.println("Quantia depositada!");

            return;
        }

        out.println("\nConta não encontrada.");
    }

    public void transfer(int accountNumber)
    {
        var account = _account.get(accountNumber);

        if(account != null)
        {
            out.print("Insire a quantia que prentende tranferir: ");
            var value = _scanner.nextDouble();

            if(value > account.getBalance())
            {
                out.println("Quantia insuficiente.");
                return;
            }

            out.print("Insire o número da conta: ");
            var otherAccountNumber = _scanner.nextDouble();

            account.setBalance(account.getBalance() - value);
            out.println("Quantia transferida!");

            return;
        }

        var account2 = _sAccount.get(accountNumber);

        if(account2 != null)
        {
            out.print("Insire a quantia que prentende tranferir: ");
            var value = _scanner.nextDouble();

            if(!account2.getOpenedDate().plusYears(account2.periodToTakeMoneyInYears).isAfter(LocalDate.now()))
            {
                out.println("IMpossível retirar o dinheiro.");
                return;
            }

            if(value > account2.getBalance())
            {
                out.println("Quantia insuficiente.");
                return;
            }

            out.print("Insire o número da conta: ");
            var otherAccountNumber = _scanner.nextDouble();

            account.setBalance(account2.getBalance() - value);
            out.println("Quantia transferida!");

            return;
        }

        out.println("\nConta não encontrada.");
    }

    public void lift(int accountNumber)
    {
        var account = _account.get(accountNumber);

        if(account != null)
        {
            out.print("Insire a quantia que prentende levantar: ");
            var newValue = _scanner.nextDouble();

            if(newValue > account.getLimit())
            {
                out.println("Quantia superior ao limite diário.");
                return;
            }

            if(newValue > account.getBalance())
            {
                out.println("Quantia insuficiente.");
                return;
            }

            account.setBalance(account.getBalance() - newValue);
            out.println("Quantia levantada!");
        }

        var account2 = _sAccount.get(accountNumber);

        if(account2 != null)
        {
            out.print("Insire a quantia que prentende levantar: ");
            var newValue = _scanner.nextDouble();

            if(!account2.getOpenedDate().plusYears(account2.periodToTakeMoneyInYears).isAfter(LocalDate.now()))
            {
                out.println("Impossível retirar o dinheiro.");
                return;
            }

            if(newValue > account2.getLimit())
            {
                out.println("Quantia superior ao limite diário.");
                return;
            }

            if(newValue > account2.getBalance())
            {
                out.println("Quantia insuficiente.");
                return;
            }

            account.setBalance(account2.getBalance() - newValue);
            out.println("Quantia levantada!");
        }

        out.println("\nConta não encontrada.");
    }
}
