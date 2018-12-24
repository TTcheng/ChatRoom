package chat;

import ui.Controller;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatConnection extends Thread {

    private Controller windowController;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private String serverIp;
    private Integer serverPort;
    private volatile Boolean listening = false;

    public ChatConnection(Controller windowController, String serverIp, Integer serverPort) {
        this.windowController = windowController;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(serverIp, serverPort);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                windowController.appendShowInfo("接收：" + line);
            }
            writer.close();
            reader.close();
            writer = null;
            reader = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void output(String string) {
        try {
            socket.getOutputStream().write((string + "\n").getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean isListening() {
        return listening;
    }

    public void setListening(Boolean listening) {
        this.listening = listening;
    }
}
