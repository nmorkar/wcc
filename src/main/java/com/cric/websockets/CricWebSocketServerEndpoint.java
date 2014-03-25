package com.cric.websockets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;

import com.cric.model.CricModel;
import com.cric.util.CricUtil;

@ServerEndpoint("/cric")
public class CricWebSocketServerEndpoint {

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	@OnOpen
	public void onConnectionOpen(Session session) {
		logger.info("Connection opened ... " + session.getId());
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		try{
			
		
		if (StringUtils.isBlank(message)) {
			 return ;
		}
		
		String[] ms = message.split(":"); //0-action:1-uname
		
		CricModel m = null;
		if("pick".equals(ms[0])){
			m = CricUtil.selectPlayer(ms[1]);
		}else if("reset".equals(message)){
			m = CricUtil.rest();
		}else if("new".equals(ms[0])){
			m = CricUtil.startNew(ms[1]);
		}
		
		
		for (Session s : session.getOpenSessions()) {
			System.out.println(s.getId());
			if (s.isOpen()) {
				try {
					s.getAsyncRemote().sendText(CricUtil.convertToJSON(m));
					System.out.println("Message Sent " + message);
				} catch (Exception ex) {
					ex.printStackTrace();
					System.err.println("Error in sending message to " + session
							+ ": " + ex);
				}
			}
		}
		
		/*if (isLogout) {
            session.close(new CloseReason(
                    CloseReason.CloseCodes.NORMAL_CLOSURE,
                    user + " leaves"));
        }*/
		
		// session.
		// return StringUtils.reverse(message);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@OnClose
	public void onConnectionClose(Session session) {
		logger.info("Connection close .... " + session.getId());
	}
}
