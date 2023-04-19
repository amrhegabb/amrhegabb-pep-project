package Service;


import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;
    public AccountService(){
        accountDAO=new AccountDAO();
    }

    public Account addAccount(Account account) {
        if(account.username!=null&&account.username!=""&&account.password.length()>4&&accountDAO.searchaccountbyusername(account.username)==null)
        return accountDAO.insertaccount(account.username,account.password);
        else
        return null;
    }
    public Account loginAccount(Account account){
        return accountDAO.loginAccount(account.username, account.password);
    }

    
}
