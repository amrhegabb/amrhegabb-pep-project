package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    //get all accounts
    public List<Account> getAllaccounts(){
        Connection connection=ConnectionUtil.getConnection();
        List<Account> accounts=new ArrayList<>();
        try{
              //Write SQL logic here
              String sql = "SELECT * FROM account";
              PreparedStatement preparedStatement = connection.prepareStatement(sql);
              ResultSet rs = preparedStatement.executeQuery();
              while(rs.next()){
                Account account=new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                accounts.add(account);
        }}
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    //insert account
    public  Account insertaccount(String username,String password){

        Connection connection=ConnectionUtil.getConnection();
        
        try{
            
            String sql="INSERT INTO account (username,password) VALUES (?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            while(pkeyResultSet.next()){
                int generated_Account_id =  pkeyResultSet.getInt(1);
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
            String sql="SELECT * FROM account WHERE username = ?,password = ? ";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,username );
            preparedStatement.setString(2, password);
            ResultSet rs=preparedStatement.executeQuery(sql);
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
