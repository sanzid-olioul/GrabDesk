package client.gui;
import client.connection.MouseInfo;
import client.connection.Server;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static client.connection.Server.keyType;
import static client.connection.Server.mouseType;


public class GuiMain extends Application{
    private FXMLLoader mainLoader;
    private Thread socketThread, receiveThread;
    private Scene mainScene;
    private Parent root;
    private Server server;
    private static GuiMain main;
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        controller = new Controller();
        mainLoader.setController(controller);
        root = mainLoader.load();
        primaryStage.setTitle("Control");
        mainScene = new Scene(root, 640, 360);
        mainScene.setOnKeyPressed(this::watchKeyInput);
        mainScene.setOnKeyReleased(this::watchKeyInput);
        mainScene.setOnMousePressed(this::watchMouseKey);
        mainScene.setOnMouseReleased(this::watchMouseKey);
        mainScene.setOnMouseMoved(this::watchMouseKey);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void watchKeyInput(KeyEvent keyEvent) {
        try {
            server.sendKey(keyEvent, keyType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void watchMouseKey(MouseEvent mouseEvent) {
        try {
            server.sendMouse(new MouseInfo(mouseEvent), mainScene.getWidth(), mainScene.getHeight(), mouseType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            server = new Server(8080);
            server.acceptClient();
            server.setOnReceive(controller::onReceive);
            receiveThread = new Thread(() -> {
                try {
                    server.receive();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Receive");
            receiveThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuiMain() {
        socketThread = new Thread(this::start, "Socket");
        socketThread.start();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
        main = new GuiMain();
    }
}
