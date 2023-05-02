package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 *
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    
public SocialMediaController(){
    this.accountService= new AccountService();
    this.messageService= new MessageService();
}
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::register);
        app.post("/login", this::login);
        app.post("messages", this::addMessage);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessagebyid);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    //login
        private void login(Context ctx) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Account account = mapper.readValue(ctx.body(), Account.class);
            Account addedAccount = accountService.loginAccount(account);
            if(addedAccount==null){
               ctx.status(400);
            }else{
                ctx.status(200);
                ctx.json(mapper.writeValueAsString(addedAccount));
            }
        }

     //register
    private void register(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount!=null){
            ctx.status(200);
            ctx.json(mapper.writeValueAsString(addedAccount));
        }else{
            ctx.status(400);
        }
    }

    private void getAllMessagesHandler(Context ctx){
        List<Message> messages=messageService.getallMessages();
        ctx.json(messages);
    }

    //add message
    private void addMessage(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.insertMessage(message);
        if(addedMessage!=null){
            ctx.status(200);
            ctx.json(mapper.writeValueAsString(addedMessage));
        }else{
           ctx.status(400);
        }
    }
    private void getMessagebyid(Context ctx) {
        ObjectMapper mapper =new ObjectMapper();
        Message message = messageService.getMessagebyid();
        ctx.json(message);

    }
  
   


}