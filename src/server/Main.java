package server;

import server.connection.Server;
import server.request.GetInput;
import server.request.SendScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;
public class Main {
    private Server server;
    private GetInput getInput;
    private Thread socketThread, sendThread;
    private SendScreen sendScreen;

    private String host;
    private int port;

    public Main(String host,int port) throws Exception {
        this.host = host;
        this.port =port;
        start();
    }

    void start() throws Exception {
        server = new Server(host, port);
        getInput = new GetInput();

        server.setOnReceive(getInput::onReceive);
        sendScreen = new SendScreen();
        socketThread = new Thread(() -> {
            try {
                server.receive();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Receive");
        socketThread.start();
        sendThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(5);
                    server.sendImage(sendScreen.getScreen());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Send");
        sendThread.start();
    }
}
