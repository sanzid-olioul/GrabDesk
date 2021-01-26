/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.grabdesk.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author sanzid
 */
public class ServerFrame extends Thread{
    JFrame jFrame;
    JLabel preLabel = new JLabel();

    public ServerFrame(){
        //jFrame.add(preLabel);
        start();
    }

    @Override
    public void run(){
        jFrame = new JFrame("Server");
        jFrame.setSize(800, 600);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
    }
    public void add(JLabel label){
        jFrame.remove(preLabel);
        jFrame.add(label);
        jFrame.repaint();
        jFrame.revalidate();
        preLabel = label;
        System.out.println("Added");
    }
}