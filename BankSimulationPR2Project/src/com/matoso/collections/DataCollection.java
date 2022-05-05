package com.matoso.collections;

import com.matoso.entities.Account;
import com.matoso.entities.Bank;
import com.matoso.entities.SavingAccount;

import java.util.ArrayList;
import java.util.List;

public class DataCollection
{
    public static class DATA_CONTEXT
    {
        public static List<Bank> BANKS = new ArrayList();
        public static List<Account> CURRENT_ACCOUNTS = new ArrayList();
        public static List<SavingAccount> SAVING_ACCOUNTS = new ArrayList();
    }
}
