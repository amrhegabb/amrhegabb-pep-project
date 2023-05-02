package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;
public class MessageDAO {
    //get all messages function
    public List<Message> getallMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages= new ArrayList<>();
        try{
            String sql="SELECT * FROM message";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                Message message= new Message(rs.getInt("message_id"), rs.getInt("posted_by"),rs.getString("message_text"),rs.getLong("time_posted_epoch") );
                messages.add(message);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());

        }
        return messages;
    }
    //insert message 
    public Message insertMessage(int posted_by,String message_text,long time_posted_epoch){
        Connection connection=ConnectionUtil.getConnection();
        try {
            String sql="INSERT INTO message (posted_by,message_text,time_posted_epoch) VALUES(?,?,?) ";
            PreparedStatement preparedStatement =connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, posted_by);
            preparedStatement.setString(2, message_text);
            preparedStatement.setLong(3, time_posted_epoch);

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet=preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_Message_id=pkeyResultSet.getInt(1);
                return new Message(generated_Message_id,posted_by, message_text, time_posted_epoch);
            }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        return null;
    }
    public Message getMessagebyitsid(int id) {
       Connection connection =ConnectionUtil.getConnection();
       try {
        String sql="SELECT * FROM message WHERE message_id= ?";
        PreparedStatement preparedStatement =connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs =preparedStatement.executeQuery();
        while(rs.next()){
            Message message =new Message(rs.getInt("message_id"),rs.getInt("posted_by"),rs.getString("message_text"),rs.getLong("time_posted_epoch"));
            return message;
        }
        
       } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        return null;
    }
    public Message deleteMessagebyid(Message message) {
        Connection connection =ConnectionUtil.getConnection();
        try {
         String sql="DELETE FROM message WHERE message_id= ?";
         PreparedStatement preparedStatement =connection.prepareStatement(sql);
         preparedStatement.setInt(1, message.message_id);
        } catch (SQLException e) {
         System.out.println(e.getMessage());
         }
         return null;
    }
    public List<Message> getallMessagesforuser(int id) {
        Connection connection =ConnectionUtil.getConnection();
        List<Message> messages= new ArrayList<>();
        try {
         String sql="SELECT * FROM message WHERE posted_by= ?";
         PreparedStatement preparedStatement =connection.prepareStatement(sql);
         preparedStatement.setInt(1, id);
         ResultSet rs =preparedStatement.executeQuery();
         while(rs.next()){
             Message message =new Message(rs.getInt("message_id"),rs.getInt("posted_by"),rs.getString("message_text"),rs.getLong("time_posted_epoch"));
            messages.add(message);
            return messages;
         }
         
        } catch (SQLException e) {
         System.out.println(e.getMessage());
         }
         return messages;
    }
    public Message updateMessagebymessage(int id, Message message) {
        Connection connection =ConnectionUtil.getConnection();
        try {
            String sql="UPDATE message SET message_text = ? WHERE message_id=?;";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, message.message_text);
            preparedStatement.setInt(2, id);
            
           if(preparedStatement.executeUpdate()==1){
            return getMessagebyitsid(id);
           }
           else{
            return null;
           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    }
