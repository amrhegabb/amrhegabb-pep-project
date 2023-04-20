package DAO;

import java.sql.*;


import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    //get all accounts

    //Search account by username
    public Account searchaccountbyusername (String username){
        Connection connection=ConnectionUtil.getConnection();
        try{
            String sql="SELECT * FROM account WHERE username = ? ";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account account=new Account(rs.getInt("account_id"),rs.getString("username"),rs.getString("password"));
                return account;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    //insert account
    public  Account insertaccount(String username,String password){

        Connection connection=ConnectionUtil.getConnection();
        
        try{
            
            String sql="INSERT INTO Account (username,password) VALUES(?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            while(pkeyResultSet.next()){
                int generated_Account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_Account_id, username, password);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    
        return null;

    }
    
    //login account 
    public Account loginAccount(String username,String password){
        Connection connection= ConnectionUtil.getConnection();
        
        try {
            String sql="SELECT * FROM accounts WHERE username = ?,password = ? ";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,username );
            preparedStatement.setString(2, password);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                Account account=new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
