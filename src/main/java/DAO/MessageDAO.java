package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            String sql="SELECT * FROM Message";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                Message message= new Message(rs.getInt(" message_id"), rs.getInt("posted_by"),rs.getString("message_text"),rs.getLong("time_posted_epoch") );
                messages.add(message);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());

        }
        return messages;
    }
    //insert message 
    public Message insertMessage(Message message){
        Connection connection=ConnectionUtil.getConnection();
        try {
            String sql="INSERT INTO Message (posted_by,message_text,time_posted_epoch) VALUES(?,?,?) ";
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setInt(1, message.posted_by);
            preparedStatement.setString(2, message.message_text);
            preparedStatement.setLong(3, message.time_posted_epoch);

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet=preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_Message_id=(int) pkeyResultSet.getLong(1);
                return new Message(generated_Message_id, message.posted_by, message.message_text, message.getTime_posted_epoch());
            }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
        return null;
    }
    }
