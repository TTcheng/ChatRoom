package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Vector;

/**
 * Created by WangChuncheng on 2018/12/17 18:50
 */
public class ChatManager {
    private static final Logger logger = LoggerFactory.getLogger(ChatManager.class);
    private static ChatManager instance = new ChatManager();

    public static ChatManager getInstance() {
        return instance;
    }

    private ChatManager() {
    }

    private Integer clientNum = 0;
    private Vector<ChatSocket> chatSockets = new Vector<>();

    public void add(ChatSocket chatSocket) {
        chatSockets.add(chatSocket);
        logger.info(++clientNum + "个客户端已连接！");
    }

    public void publish(ChatSocket chatSocket, String out) {
        for (ChatSocket socket : chatSockets) {
            if (!chatSocket.equals(socket)) {
                socket.out(out);
            }
        }
    }

    public void remove(ChatSocket cs) {
        chatSockets.remove(cs);
    }
}
