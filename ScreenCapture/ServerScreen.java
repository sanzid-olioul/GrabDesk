/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.grabdesk.ScreenCapture;

import java.awt.image.BufferedImage;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import project.grabdesk.gui.ServerFrame;

/**
 *
 * @author sanzid
 */

public class ServerScreen {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(2525);
        BufferedImage screen= null;
        ServerFrame fm = new ServerFrame();
        JLabel jLabel;
        while(true){
            Socket socket = serverSocket.accept();
                System.out.println(" Active ..");
                try{
                    screen = ImageIO.read(socket.getInputStream());
                    ImageIcon ImageIcon = new ImageIcon(screen);
                    jLabel = new JLabel(ImageIcon);
                    fm.add(jLabel);
                    socket.getOutputStream().write(65);
                }catch(Exception e){
                    e.printStackTrace();
                }
        }
    }
}
