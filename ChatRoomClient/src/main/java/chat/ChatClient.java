package chat;

import ui.Controller;
/**
 * Created by WangChuncheng on 2018/12/17 18:46
 */
public class ChatClient {
    private static ChatClient chatClient = new ChatClient();

    public static ChatClient getInstance() {
        return chatClient;
    }

    private ChatClient() {
    }

    private Controller windowController;
    private ChatConnection listener;

    public void connect(String serverIp, Integer serverPort) {
        if (serverIp == null || serverPort == null) {
            throw new RuntimeException("IP或端口为空！");
        }
        listener = new ChatConnection(windowController,serverIp,serverPort);
        listener.setListening(true);
        listener.start();
    }

    public void send(String msg) {
        listener.output(msg);
    }

    public void setWindowController(Controller windowController) {
        this.windowController = windowController;
    }

    public void disconnect() {
        if (listener.isListening()){
            listener.setListening(false);
        }
    }
}
