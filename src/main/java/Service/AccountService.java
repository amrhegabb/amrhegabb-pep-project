package Service;


import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;
    public AccountService(){
        accountDAO=new AccountDAO();
    }

    public Account addAccount(Account account) {
        if(account.username!=null&&account.username!=""&&account.password.length()>4&&accountDAO.searchaccount(account)!=null)
        return accountDAO.insertaccount(account);
        else
        return null;
    }

    
}
