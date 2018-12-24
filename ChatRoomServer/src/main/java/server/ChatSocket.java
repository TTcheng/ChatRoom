package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by WangChuncheng on 2018/12/17 17:27
 */
public class ChatSocket extends Thread {
    private static final String THREAD_NAME = "ChatSocket";
    private Socket socket;

    public ChatSocket(Socket socket) {
        super(THREAD_NAME);
        this.socket = socket;
    }

    public void out(String info) {
        try {
            socket.getOutputStream().write((info + "\n").getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        out("你已经连接到本服务器了");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(Thread.currentThread().getName() + line);
                ChatManager.getInstance().publish(this, line);
            }
            br.close();
            ChatManager.getInstance().remove(this);
        } catch (IOException e) {
            ChatManager.getInstance().remove(this);
            e.printStackTrace();
        }

    }
}
