package chatting.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Server extends JFrame implements ActionListener {
    private JPanel headerPanel;
    private ImageIcon backIcon,backIconMain,profileIcon,prfileIconMain;
    private Image backImage,profileImage;
    private JLabel backlabel,profileLabel;

    Server(){
        setLayout(null);
        headerPanel=new JPanel();
        headerPanel.setBackground(new Color(7,94,84));
        headerPanel.setBounds(0,0,450,70);
        headerPanel.setLayout(null);

        //back buttton--------------
        backIcon = new ImageIcon(getClass().getResource("/icons/3.png"));
        backImage=backIcon.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        backIconMain=new ImageIcon(backImage);
        backlabel=new JLabel(backIconMain);
        backlabel.setBounds(5, 20, 25, 25);
        backlabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                System.exit(0);
            }
        });
        headerPanel.add(backlabel);

        //profile pic-------------
        profileIcon = new ImageIcon(getClass().getResource("/icons/1.png"));
        profileImage=profileIcon.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH);
        prfileIconMain=new ImageIcon(profileImage);
        profileLabel=new JLabel(prfileIconMain);
        profileLabel.setBounds(40, 10, 50, 50);
        headerPanel.add(profileLabel);


        add(headerPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,700);
        getContentPane().setBackground(Color.WHITE);
        setLocation(200,50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

    }

    public static void main(String[] args){
        new Server();
    }
}