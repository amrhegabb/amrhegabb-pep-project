package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    MessageDAO messageDAO;
    public MessageService(){
        messageDAO=new MessageDAO();
    }
    public Message insertMessage(Message message){
        if(message.message_text.isBlank())
        return null;
        else if(message.message_text.length()>253)
        return null;
        else
        return messageDAO.insertMessage(message.getPosted_by(),message.getMessage_text(),message.getTime_posted_epoch());
    }
    public List<Message> getallMessages() {
        List<Message> mesages =messageDAO.getallMessages();
        return mesages;
    }
    public Message getMessagebyid() {
        return messageDAO.getMessagebyitsid();
    }

 
    
}
