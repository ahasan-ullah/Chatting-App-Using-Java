package chatting.application;

import javax.swing.*;
import java.awt.*;

public class Server extends JFrame {
    private JPanel headerPanel;

    Server(){
        setLayout(null);
        headerPanel=new JPanel();
        headerPanel.setBackground(new Color(7,94,84));


        headerPanel.setBounds(0,0,450,70);
        add(headerPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,700);
        getContentPane().setBackground(Color.WHITE);
        setLocation(200,50);
        setVisible(true);
    }

    public static void main(String[] args){
        new Server();
    }
}