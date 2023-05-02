package Service;


import java.util.List;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;
    public AccountService(){
        accountDAO=new AccountDAO();
    }

    public Account addAccount(Account account) {
        List<Account> accounts= accountDAO.getAllaccounts();
        if(account.getUsername().isBlank()&&account.getPassword().length()<4&&accounts.contains(account))
        return null;
        else if(account.username.isBlank())
        return null;
        else if(account.password.length()<4)
        return null;
        else
        return accountDAO.insertaccount(account.getUsername(), account.getPassword());
    }

    public Account loginAccount(Account account){
        List<Account> accounts= accountDAO.getAllaccounts();
        if(accounts.contains(account))
        return accountDAO.loginAccount(account.getUsername(), account.getPassword());
        else
        return null;
    }

    public List<Account> getAllAccounts() {
        return  accountDAO.getAllaccounts();
    }

    
}
