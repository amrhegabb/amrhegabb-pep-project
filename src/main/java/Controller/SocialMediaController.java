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
        app.delete("/messages/{message_id}", this::deleteMessagebyid);
        app.get("/accounts/{account_id}/messages", this::getallMessagesforuser);
        app.patch("/messages/{message_id}", this::updateMessagebymessageid);

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
            Account logiAccount = accountService.loginAccount(account);
            if(logiAccount==null){
                ctx.status(401);
            }else {
                ctx.status(200);
                ctx.json(mapper.writeValueAsString(logiAccount));
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
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.getMessagebyid(id);
        if(message==null){
            ctx.status(200);
            ctx.json("");
        }else
        ctx.json(message);

    }
    private void deleteMessagebyid(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        Message message =messageService.deleteMessagebyid(id);
        if(message==null){
            ctx.status(200);
        }else{
            ctx.status(200);
            ctx.json(message);
        }
    }
    private void getallMessagesforuser(Context ctx) {
        int id=Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> messages=messageService.getallMessagesforuser(id); 
        System.out.println(id);
        System.out.println(messages);
        ctx.status(200);
        ctx.json(messages);
    }
    private void updateMessagebymessageid(Context ctx) throws  JsonProcessingException {
        int id = Integer.parseInt(ctx.pathParam("message_id"));
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message updatedMessage = messageService.updateMessagebymessageid(id,message);
        if(updatedMessage==null){
            ctx.status(400);
        }else
        {
            ctx.status(200);
            ctx.json(updatedMessage);
        }
       

      
       
    }
  
   


}