package Service;


import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;
    public AccountService(){
        accountDAO=new AccountDAO();
    }

    public Account addAccount(Account account) {
        if(account.getUsername()==" "&&account.getPassword().length()<=4&&accountDAO.searchaccountbyusername(account.getUsername())!=null)
        return null;
        else
        return accountDAO.insertaccount(account.getUsername(), account.getPassword());
    }
    public Account loginAccount(Account account){
        return accountDAO.loginAccount(account.getUsername(), account.getPassword());
    }

    
}
