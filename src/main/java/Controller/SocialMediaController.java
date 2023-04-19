package Controller;

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
    accountService= new AccountService();
    messageService= new MessageService();
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
                ctx.status(401);
            }else{
                ctx.status(200);
                ctx.json(accountService.loginAccount(addedAccount));
            }
        }

     //register
    private void register(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount==null){
            ctx.status(400);
        }else{
            ctx.status(200);
            ctx.json(mapper.writeValueAsString(addedAccount));
            ctx.json(accountService.addAccount(addedAccount));
        }
    }

    private void getAllMessagesHandler(Context ctx){
        ctx.json(messageService.getallMessages());
    }

    //add message
    private void addMessage(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.insertMessage(message);
        if(addedMessage==null){
            ctx.status(400);
        }else{
            ctx.status(200);
            ctx.json(messageService.insertMessage(addedMessage));
        }
    }
   


}