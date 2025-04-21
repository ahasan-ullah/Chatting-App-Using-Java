package chatting.application;

import javax.swing.*;
import java.awt.*;

public class Server extends JFrame {
    private JPanel headerPanel;
    private ImageIcon backIcon,backIconMain;
    private Image backImage;
    private JLabel backlabel;

    Server(){
        setLayout(null);
        headerPanel=new JPanel();
        headerPanel.setBackground(new Color(7,94,84));
        headerPanel.setBounds(0,0,450,70);
        headerPanel.setLayout(null);

        backIcon = new ImageIcon(getClass().getResource("/icons/3.png"));
        backImage=backIcon.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        backIconMain=new ImageIcon(backImage);
        backlabel=new JLabel(backIconMain);
        backlabel.setBounds(5, 20, 25, 25);
        headerPanel.add(backlabel);

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