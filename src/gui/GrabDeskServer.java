package gui;
import java.net.InetAddress;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GrabDeskServer {
    private static JButton button(String name , int x, int y){
        JButton btn = new JButton(name);
        btn.setBounds(x,y,100,50);
        btn.setBackground(Color.CYAN);
        btn.setFont(Font.getFont("SAN_SARIF"));
        return btn;
    }
    public GrabDeskServer(JFrame jf){
        String ipaddress = "";
        try{
            InetAddress iadd = InetAddress.getLocalHost();
            ipaddress = iadd.getHostAddress().trim();
        }catch(Exception e){

        }

        JLabel IP = new JLabel("Host Address : " + ipaddress);
        JLabel PORT = new JLabel("Host Port :  8080");

        IP.setBounds(125,225,150,48);
        PORT.setBounds(125,300,150,48);


        IP.setFont(IP.getFont().deriveFont (18.0f));
        PORT.setFont(PORT.getFont().deriveFont (18.0f));
        jf.add(IP);
        jf.add(PORT);
        jf.invalidate();
        jf.validate();
        jf.repaint();

        try {
            new Thread(() -> {
                try {
                    new client.gui.GuiMain().mustRun();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Send").start();

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
