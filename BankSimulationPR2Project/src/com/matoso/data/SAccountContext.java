package com.matoso.data;

import com.matoso.collections.DataCollection;
import com.matoso.entities.Account;
import com.matoso.entities.SavingAccount;

import java.util.ArrayList;
import java.util.List;

public class SAccountContext
{
    public void add(SavingAccount account)
    {
        DataCollection.DATA_CONTEXT.SAVING_ACCOUNTS.add(account);
    }

    public SavingAccount get(int accountIdentifier)
    {
        for (var account : DataCollection.DATA_CONTEXT.SAVING_ACCOUNTS)
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

        DataCollection.DATA_CONTEXT.SAVING_ACCOUNTS.remove(account);
        return true;
    }

    public List<SavingAccount> all()
    {
        return DataCollection.DATA_CONTEXT.SAVING_ACCOUNTS;
    }

    public List<SavingAccount> all(int accountIdentifier)
    {
        List accounts = new ArrayList<Account>();

        for (var account : DataCollection.DATA_CONTEXT.SAVING_ACCOUNTS)
        {
            if (account.getAccountNumber() == accountIdentifier ||
                    account.getBankId() == accountIdentifier)
            {
                accounts.add(account);
            }
        }

        return accounts;
    }

    public List<SavingAccount> all(String accountIdentifier)
    {
        List accounts = new ArrayList<Account>();

        for (var account : DataCollection.DATA_CONTEXT.SAVING_ACCOUNTS)
        {
            if (account.getOwnerName() == accountIdentifier)
            {
                accounts.add(account);
            }
        }

        return accounts;
    }
}
