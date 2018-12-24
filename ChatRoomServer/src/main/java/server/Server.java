package server;

import exceptions.DefaultExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by WangChuncheng on 2018/12/17 17:27
 */
public class Server {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Server.class);
        ChatListener chatListener = new ChatListener(12345);
        chatListener.start();//thread start
        logger.info("服务器已启动！");
        Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler());
    }
}
