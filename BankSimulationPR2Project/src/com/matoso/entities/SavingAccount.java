package com.matoso.entities;

import java.time.LocalDate;
import java.util.Random;

public class SavingAccount extends Account {
    public int periodToTakeMoneyInYears;
    public LocalDate timeToTakeMoney;

    private Random rnd = new Random();

    private static final int _maxAccountNumberValue = 9999;
    private static final int _maxAccountIdValue = 9999999;

    public SavingAccount()
    {
        this.bankId = 1;
        this.openedDate = LocalDate.now();
        this.id = rnd.nextInt(_maxAccountIdValue);
        this.accountNumber = rnd.nextInt(_maxAccountNumberValue);
    }

    public SavingAccount(int id, String ownerName, int bankId)
    {
        this.id = id;
        this.ownerName = ownerName;
        this.bankId = bankId;

        limit = 0;
        balance = 0f;
        accountNumber = rnd.nextInt(_maxAccountNumberValue);
    }

    public SavingAccount(int id, String ownerName, int bankId, String password)
    {
        this.id = id;
        this.ownerName = ownerName;
        this.bankId = bankId;
        this.password = password;

        limit = 0;
        balance = 0f;
        periodToTakeMoneyInYears = 1;
        accountNumber = rnd.nextInt(_maxAccountNumberValue);
    }

    public SavingAccount(int id, String ownerName, int bankId, String password, double balance)
    {
        this.id = id;
        this.ownerName = ownerName;
        this.bankId = bankId;
        this.balance = balance;
        this.password = password;

        limit = 0;
        periodToTakeMoneyInYears = 1;
        accountNumber = rnd.nextInt(_maxAccountNumberValue);
    }

    public SavingAccount(int id, String ownerName, int bankId, String password, double balance, int limit, int yearToTake)
    {
        this.id = id;
        this.ownerName = ownerName;
        this.bankId = bankId;
        this.balance = balance;
        this.limit = limit;
        this.password = password;
        this.periodToTakeMoneyInYears = yearToTake;

        accountNumber = rnd.nextInt(_maxAccountNumberValue);
    }

    public SavingAccount(int id, String ownerName, int bankId, String password, double balance, int limit, int yearToTake, int accountNumber)
    {
        this.id = id;
        this.ownerName = ownerName;
        this.bankId = bankId;
        this.password = password;
        this.balance = balance;
        this.limit = limit;
        this.periodToTakeMoneyInYears = yearToTake;
        this.accountNumber = accountNumber;
    }

    public int getPeriodToTakeMoneyInYears() {
        return periodToTakeMoneyInYears;
    }

    public void setPeriodToTakeMoneyInYears(int periodToTakeMoneyInYears) {
        this.periodToTakeMoneyInYears = periodToTakeMoneyInYears;
    }

    public LocalDate getTimeToTakeMoney() {
        return timeToTakeMoney;
    }

    public void setTimeToTakeMoney(LocalDate timeToTakeMoney) {
        this.timeToTakeMoney = timeToTakeMoney;
    }
}
