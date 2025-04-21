package chatting.application;

import javax.swing.*;
import java.awt.*;

public class Server extends JFrame {

    Server(){
        setLayout(null);

        setSize(450,700);
        getContentPane().setBackground(Color.WHITE);
        setLocation(200,50);
        setVisible(true);
    }

    public static void main(String[] args){
        new Server();
    }
}