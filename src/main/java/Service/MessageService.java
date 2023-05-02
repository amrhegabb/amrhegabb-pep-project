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
    public Message getMessagebyid(int id) {
        return messageDAO.getMessagebyitsid(id);
    }
    public Message deleteMessagebyid(int id) {
        Message deletedMessage= messageDAO.getMessagebyitsid(id);
        if(deletedMessage==null){
            return null;
        }else{
        messageDAO.deleteMessagebyid(deletedMessage);
        return deletedMessage;
    }
    }
    public List<Message> getallMessagesforuser(int id) {
  
        return messageDAO.getallMessagesforuser(id);
    }
    public Message updateMessagebymessageid(int id, Message message) {
        if(message.message_text.isBlank()){
            return null;
        }else if(getMessagebyid(id)==null){
            return null;
        }else if(message.message_text.length()>254){
            return null;
        }else
        return messageDAO.updateMessagebymessage(id,message) ;

    }

 
    
}
