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
        return messageDAO.insertMessage(message);
    }
    public List<Message> getallMessages() {
        return messageDAO.getallMessages();
    }

 
    
}
