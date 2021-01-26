/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.grabdesk.ScreenCapture;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.Socket;
import javax.imageio.ImageIO;
/**
 *
 * @author sanzid
 */
public class ClientScreen {
     public static void main(String[] args) throws Exception{
         while(true){
            Socket socket = new Socket("localhost",2525);
            Robot robot = new Robot();
            Rectangle rec = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screen = robot.createScreenCapture(rec);
            try{
                ImageIO.write(screen, "JPG", socket.getOutputStream());
                InputStream bufstr = socket.getInputStream();
                String str = bufstr.toString();
                System.out.println(str);
                
            }catch(Exception e){
                e.printStackTrace();
            }
            Thread.sleep(50);
        }
    }
}