package com.matoso.entities;

import com.matoso.models.AccountTypes;

import java.time.LocalDate;
import java.util.Random;

public class Account {
    public int id;
    public int accountNumber;
    public String ownerName;
    public double balance;
    public int limit;
    public int bankId;
    public String password;
    public LocalDate openedDate;
    public AccountTypes accountType;

    private Random rnd = new Random();

    private static final int _maxAccountNumberValue = 9999;
    private static final int _maxAccountIdValue = 9999999;

    public Account()
    {
        bankId = 1;
        openedDate = LocalDate.now();
        id = rnd.nextInt(_maxAccountIdValue);
        accountNumber = rnd.nextInt(_maxAccountNumberValue);
    }

    public Account(int id, String ownerName, int bankId)
    {
        this.id = id;
        this.ownerName = ownerName;
        this.bankId = bankId;

        limit = 0;
        balance = 0f;
        accountNumber = rnd.nextInt(_maxAccountNumberValue);
    }

    public Account(int id, String ownerName, int bankId, String password)
    {
        this.id = id;
        this.ownerName = ownerName;
        this.bankId = bankId;
        this.password = password;

        limit = 0;
        balance = 0f;
        accountNumber = rnd.nextInt(_maxAccountNumberValue);
    }

    public Account(int id, String ownerName, int bankId, String password, double balance)
    {
        this.id = id;
        this.ownerName = ownerName;
        this.bankId = bankId;
        this.balance = balance;
        this.password = password;

        limit = 0;
        accountNumber = rnd.nextInt(_maxAccountNumberValue);
    }

    public Account(int id, String ownerName, int bankId, String password, double balance, int limit)
    {
        this.id = id;
        this.ownerName = ownerName;
        this.bankId = bankId;
        this.balance = balance;
        this.limit = limit;
        this.password = password;

        accountNumber = rnd.nextInt(_maxAccountNumberValue);
    }

    public Account(int id, String ownerName, int bankId, String password, double balance, int limit,  int accountNumber)
    {
        this.id = id;
        this.ownerName = ownerName;
        this.bankId = bankId;
        this.password = password;
        this.balance = balance;
        this.limit = limit;
        this.accountNumber = accountNumber;
    }

    public int getId() {
        return id;
    }

    public int getBankId() {
        return bankId;
    }

    public String getPassword() {
        return password;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getLimit() {
        return limit;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public LocalDate getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(LocalDate openedDate) {
        this.openedDate = openedDate;
    }
}