package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import gui.GrabDeskClient;

public class GrabDeskMain {
    public JFrame jf = new JFrame("GrabDesk");
    private JLabel brandName = new JLabel("GrabDesk");
    public JButton serverBtn;
    public JButton clientBtn;

    private static void addIcon(JFrame jf){
        Path pth = Paths.get("src/gui/resources");
        ImageIcon icon = new ImageIcon(pth.toAbsolutePath().toString() + "\\icon.png");
        System.out.println(icon);
        jf.setIconImage(icon.getImage());
    }

    private static JButton button(String name ,int x, int y){
        JButton btn = new JButton(name);
        btn.setBounds(x,y,100,50);
        btn.setBackground(Color.CYAN);
        btn.setFont(Font.getFont("SAN_SARIF"));
        return btn;
    }


    public GrabDeskMain() {
        jf.setSize(600,600);
        Rectangle rc = jf.getBounds();

        serverBtn = GrabDeskMain.button("Server",150,400);
        clientBtn = GrabDeskMain.button("Client",350,400);
        brandName.setBounds(235,100,150,48);
        brandName.setFont(brandName.getFont().deriveFont (28.0f));

        System.out.println(rc.height+ "  "+ rc.width);

        serverBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                    serverBtn.setVisible(false);
                    clientBtn.setVisible(false);
                    new GrabDeskServer(jf);
            }
        });
        clientBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){


                serverBtn.setVisible(false);
                clientBtn.setVisible(false);
                //brandName.setVisible(false);

                GrabDeskClient gbc = new GrabDeskClient(jf);



            }
        });


        GrabDeskMain.addIcon(jf);
        jf.add(brandName);
        jf.add(serverBtn);
        jf.add(clientBtn);


        jf.setLayout(null);
        jf.setVisible(true);
    }
}
