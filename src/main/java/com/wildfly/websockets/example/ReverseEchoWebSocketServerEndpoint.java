package com.wildfly.websockets.example;

import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;

@ServerEndpoint("/echo")
public class ReverseEchoWebSocketServerEndpoint {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @OnOpen
    public void onConnectionOpen(Session session) {
        logger.info("Connection opened ... " + session.getId());
    }

    @OnMessage
    public void onMessage(String message,Session session) {
        if (StringUtils.isBlank(message)) {
           // return "Please send message";
        }
        
        for (Session s : session.getOpenSessions()) {
        	//s.getId();
            if (s.isOpen()) {
            	try {
                    session.getBasicRemote().sendObject(StringUtils.reverse(message));
                } catch(Exception ex) {
                    System.err.println("Error in sending message to " + session + ": " + ex);
                }
            }
        }
       // session.
        //return StringUtils.reverse(message);
    }

    @OnClose
    public void onConnectionClose(Session session) {
        logger.info("Connection close .... " + session.getId());
    }
}
