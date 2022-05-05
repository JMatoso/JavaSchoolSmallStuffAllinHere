package com.matoso.data;

import com.matoso.collections.DataCollection;
import com.matoso.entities.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountContext
{

    public void add(Account account)
    {
        DataCollection.DATA_CONTEXT.CURRENT_ACCOUNTS.add(account);
    }

    public Account login(int accountNumber, String password)
    {
        for (var account : DataCollection.DATA_CONTEXT.CURRENT_ACCOUNTS)
        {
            if (account.getAccountNumber() == accountNumber && account.getPassword().equals(password))
            {
                return account;
            }
        }

        for (var account : DataCollection.DATA_CONTEXT.SAVING_ACCOUNTS)
        {
            if (account.getAccountNumber() == accountNumber && account.getPassword().equals(password))
            {
                return account;
            }
        }

        return null;
    }

    public Account get(int accountIdentifier)
    {
        for (var account : DataCollection.DATA_CONTEXT.CURRENT_ACCOUNTS)
        {
            if (account.getId() == accountIdentifier || account.getAccountNumber() == accountIdentifier)
            {
                return account;
            }
        }

        return null;
    }

    public boolean remove(int accountIdentifier)
    {
        var account = get(accountIdentifier);

        if(account == null) return false;

        DataCollection.DATA_CONTEXT.CURRENT_ACCOUNTS.remove(account);
        return true;
    }

    public List<Account> all()
    {
        return DataCollection.DATA_CONTEXT.CURRENT_ACCOUNTS;
    }

    public List<Account> all(int accountIdentifier) {
        List accounts = new ArrayList<Account>();

        for (var account : DataCollection.DATA_CONTEXT.CURRENT_ACCOUNTS)
        {
            if (account.getAccountNumber() == accountIdentifier ||
                    account.getBankId() == accountIdentifier)
            {
                accounts.add(account);
            }
        }

        return accounts;
    }

    public List<Account> all(String accountIdentifier)
    {
        List accounts = new ArrayList<Account>();

        for (var account : DataCollection.DATA_CONTEXT.CURRENT_ACCOUNTS)
        {
            if (account.getOwnerName() == accountIdentifier)
            {
                accounts.add(account);
            }
        }

        return accounts;
    }
}
