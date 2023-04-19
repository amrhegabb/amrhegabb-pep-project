package DAO;

import java.sql.*;


import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

    //Search account by username
    public Account searchaccount(Account account){
        Connection connection=ConnectionUtil.getConnection();
        try{
            String sql="SELECT * FROM account WHERE username = ? ";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, account.username);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
               return Account(rs.getInt("account_id"),rs.getString("username"),rs.getString("password"));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    private Account Account(int int1, String string, String string2) {
        return null;
    }


    //insert account
    public  Account insertaccount(Account account){

        Connection connection=ConnectionUtil.getConnection();
        
        try{
            String sql="INSERT INTO account (username,password) VALUES(?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(0, account.username);
            preparedStatement.setString(1, account.password);
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_Account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_Account_id, account.getUsername(), account.getPassword());
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    
        return null;

    }
    
}
