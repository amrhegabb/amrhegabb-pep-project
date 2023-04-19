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
    public static List<Message> getallMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages= new ArrayList<>();
        try{
            String sql="SELECT * FROM message";
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
}
