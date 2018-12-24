package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by WangChuncheng on 2018/12/17 18:38
 */
public class ChatListener extends Thread {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String THREAD_NAME = "ChatListener";
    private Integer port;

    public ChatListener(Integer port) {
        super(THREAD_NAME);
        this.port = port;
        if (port < 1024 || port > 49151) {
            logger.error("Invalid Port", port);
            throw new RuntimeException("Invalid port" + port + "！！");
        }
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            while (true) {
                //block
                Socket socket = serverSocket.accept();
                ChatSocket chatSocket = new ChatSocket(socket);
                chatSocket.start();
                ChatManager.getInstance().add(chatSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
