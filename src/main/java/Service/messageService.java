package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class messageService {
    public List<Message> getAllFlights() {
        return MessageDAO.getallMessages();
    }

    public static Object getallMessages() {
        return null;
    }
    
}
