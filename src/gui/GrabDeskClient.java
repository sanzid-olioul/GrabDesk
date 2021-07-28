package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GrabDeskClient {


    private static JButton button(String name ,int x, int y){
        JButton btn = new JButton(name);
        btn.setBounds(x,y,100,50);
        btn.setBackground(Color.CYAN);
        btn.setFont(Font.getFont("SAN_SARIF"));
        return btn;
    }


    public GrabDeskClient(JFrame jf){
        JLabel hostName = new JLabel("Host Name :");
        JTextField hostNameText = new JTextField("Type Server Host name");
        JLabel portNo = new JLabel("Port No :");
        JTextField portNoText = new JTextField("Type Server Port No.");
        hostName.setBounds(75,250,125,35);
        hostNameText.setBounds(225,250,150,35);
        portNo.setBounds(75,300,125,35);
        portNoText.setBounds(225,300,150,35);



        JButton submit = button("Connect",400,400);


        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                String host = hostNameText.getText().toString().trim();
                int port = 0;
                try {
                    port = Integer.parseInt(portNoText.getText().trim());
                }catch (Exception ex){
                    ex.printStackTrace();
                }

                try {
                    new server.Main(host,port);

                }catch (Exception ex){

                    ex.printStackTrace();
                }


            }
        });
        jf.add(hostName);
        jf.add(hostNameText);
        jf.add(portNo);
        jf.add(portNoText);

        jf.add(submit);
        jf.invalidate();
        jf.validate();
        jf.repaint();


    }
}
