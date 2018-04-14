
package WebSocket;

import com.sun.faces.el.ELUtils;
import javax.websocket.OnMessage;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import javax.json.Json;
import com.google.gson.JsonObject;

@ServerEndpoint("/WebSocket1")
public class WebSocket1 {
    static Set <Session> ChatroomUsers=Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void handleOpen(Session userSession){
        ChatroomUsers.add(userSession);
        
    }
    
    @OnClose
    public void handleClose(Session userSession){
         ChatroomUsers.remove(userSession);
    }
    
    @OnMessage
    public String onMessage(String message, Session userSession) throws Exception {
        String username=(String) userSession.getUserProperties().get("usuario");
        if(username==null){
            userSession.getUserProperties().put("username",message);
            userSession.getBasicRemote().sendText(message);
        }else{
            Iterator<Session> iterator=ChatroomUsers.iterator();
            while(iterator.hasNext()){
                iterator.next().getBasicRemote().sendText(message);
            }
        }
        return message;
    }
    
   
    
}
