/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedme.controller;

import feedme.model.JSONUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author adi
 */
@ServerEndpoint("/sock")
public class SocketServer {
    // set to store all the live sessions
    private static final Set<Session> sessions = Collections
            .synchronizedSet(new HashSet<Session>());
 
    // Mapping between session and person name
    private static final HashMap<String, Session> nameSessionPair = new HashMap<String, Session>();
 
    private JSONUtils jsonUtils = new JSONUtils();
 
    // Getting query params
    public static Map<String, String> getQueryMap(String query) {
        Map<String, String> map = new HashMap<String, String>();
        if (query != null) {
            String[] params = query.split("&");
            for (String param : params) {
                String[] nameval = param.split("=");
                map.put(nameval[0], nameval[1]);
            }
        }
        return map;
    }
 
    /**
     * Called when a socket connection opened
     * */
    @OnOpen
    public void onOpen(Session session) {
 
        System.out.println(session.getId() + " has opened a connection");
 
        Map<String, String> queryParams = getQueryMap(session.getQueryString());
 
        String name = "";
 
        if (queryParams.containsKey("name")) {
 
            // Getting client name via query param
            name = queryParams.get("name");
            try {
                name = URLDecoder.decode(name, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
 
            // Mapping client name and session id
            
            
        }
        if( !name.equals("customer") ) {
            nameSessionPair.put(name, session);
            sessions.add(session);
        }
        
       
        
        // Adding session to session list
        
 
        try {
            // Sending session id to the client that just connected
            session.getBasicRemote().sendText(jsonUtils.getClientDetailsJson(name,"Your session details"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
      System.out.println("Sessions size " + sessions.size());
      for (Iterator<Map.Entry<String,Session>> it = nameSessionPair.entrySet().iterator(); it.hasNext();) {
        Map.Entry<String,Session> e = it.next();
            System.out.println("rest id " + e.getKey() + " rest_session_id "+e.getValue().getId().toString());
       }
 
        
 
    }
 
    /**
     * method called when new message received from any client
     * 
     * @param message
     *            JSON message from client
     * */
    @OnMessage
    public void onMessage(String message, Session session) {
 
        System.out.println("Message from " + session.getId() + ": " + message);
 
        JSONObject jObj = null;
 
        // Parsing the json and getting message
        try {
            jObj = new JSONObject(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
 
        // Sending the message to all clients
        sendMessageToResturent(jObj);
//        sendMessageToAll(session.getId(), nameSessionPair.get(session.getId()),
//                msg, false, false);
    }
 
    /**
     * Method called when a connection is closed
     * */
    @OnClose
    public void onClose(Session session) {
 
        System.out.println("Session " + session.getId() + " has ended");
 
        // Getting the client name that exited
        
        for (Iterator<Map.Entry<String,Session>> it = nameSessionPair.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String,Session> e = it.next();
            if (session.equals(e.getValue())) {
             it.remove();
            }
           }
        // removing the session from sessions list
        sessions.remove(session);
 
      System.out.println("Sessions size" + sessions.size());
      for (Iterator<Map.Entry<String,Session>> it = nameSessionPair.entrySet().iterator(); it.hasNext();) {
        Map.Entry<String,Session> e = it.next();
            System.out.println("rest id " + e.getKey() + " rest_session_id "+e.getValue().getId().toString());
       }
 
    }
    
    
    
    
    private void sendMessageToResturent(JSONObject msg) {
         System.out.println("Message  " + msg);
        try {
            JSONObject order = msg.getJSONObject("order");
            Session ses = nameSessionPair.get(String.valueOf(order.getInt("rest_id")));
            if(ses != null)
                ses.getBasicRemote().sendText(order.toString());
        } catch (JSONException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
    
    
    
}
