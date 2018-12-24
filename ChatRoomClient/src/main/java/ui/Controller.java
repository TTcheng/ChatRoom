package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import chat.ChatClient;

import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox rootBox;

    @FXML
    private HBox serverBox;

    @FXML
    private JFXTextField ipInput;

    @FXML
    private JFXTextField portInput;

    @FXML
    private JFXButton serverLinkBtn;

    @FXML
    private JFXTextArea showArea;

    @FXML
    private HBox inputBox;

    @FXML
    private JFXTextField inputText;

    @FXML
    private JFXButton inputBtn;

    private Socket socket;

    private Boolean connected = false;

    @FXML
    private void link(ActionEvent event) {
        connected = !connected;
        ipInput.setDisable(true);
        portInput.setDisable(true);
        //serverLinkBtn.setDisable(true);
        if (connected) {
            String ip = ipInput.getText();
            String portStr = portInput.getText();
            Integer port = Integer.valueOf(portStr);
            ChatClient.getInstance().connect(ip, port);
            serverLinkBtn.setText("Disconnect");
        } else {
            ChatClient.getInstance().disconnect();
            serverLinkBtn.setText("Connect");
        }
    }

    public void setShowInfo(String info) {
        showArea.setText(info);
    }

    public void appendShowInfo(String info) {
        showArea.appendText(info + "\n");
    }

    @FXML
    public void send(ActionEvent actionEvent) {
        String text = inputText.getText();
        ChatClient.getInstance().send(text);
        appendShowInfo("发送：" + text);
        inputText.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChatClient.getInstance().setWindowController(this);
        showArea.setEditable(false);
    }
}
