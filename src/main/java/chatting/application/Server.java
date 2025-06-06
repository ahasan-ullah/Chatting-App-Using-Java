package chatting.application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Server implements ActionListener {
    private JPanel headerPanel,bodyPanel;
    private ImageIcon backIcon,backIconMain,profileIcon,prfileIconMain,videoIcon,videoIconMain,audioIcon,audioIconMain,moreIcon,moreIconMain;
    private Image backImage,profileImage,videoImage,audioImage,moreImage;
    private JLabel backlabel,profileLabel,videoLabel,audioLabel,moreLabel,name,status;
    private JTextField msgBox;
    private JButton sendBtn;
    private static Box vertical=Box.createVerticalBox();
    static JFrame serverFrame=new JFrame();
    private static DataOutputStream outputStream;

    Server(){
        serverFrame.setLayout(null);
        headerPanel=new JPanel();
        headerPanel.setBackground(new Color(7,94,84));
        headerPanel.setBounds(0,0,450,70);
        headerPanel.setLayout(null);

        //back buttton--------------
        backIcon = new ImageIcon(getClass().getResource("/icons/3.png"));
        backImage=backIcon.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        backIconMain=new ImageIcon(backImage);
        backlabel=new JLabel(backIconMain);
        backlabel.setBounds(10, 20, 25, 25);
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
        profileLabel.setBounds(45, 10, 50, 50);
        headerPanel.add(profileLabel);

        //video icon----------------
        videoIcon = new ImageIcon(getClass().getResource("/icons/video.png"));
        videoImage=videoIcon.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
        videoIconMain=new ImageIcon(videoImage);
        videoLabel=new JLabel(videoIconMain);
        videoLabel.setBounds(300, 20, 30, 30);
        headerPanel.add(videoLabel);

        //audio icon----------------
        audioIcon = new ImageIcon(getClass().getResource("/icons/phone.png"));
        audioImage=audioIcon.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
        audioIconMain=new ImageIcon(audioImage);
        audioLabel=new JLabel(audioIconMain);
        audioLabel.setBounds(350, 20, 30, 30);
        headerPanel.add(audioLabel);

        //more icon----------------
        moreIcon = new ImageIcon(getClass().getResource("/icons/3icon.png"));
        moreImage=moreIcon.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
        moreIconMain=new ImageIcon(moreImage);
        moreLabel=new JLabel(moreIconMain);
        moreLabel.setBounds(400, 20, 10, 25);
        headerPanel.add(moreLabel);

        //name
        name=new JLabel("Rahim");
        name.setBounds(110,15,100,10);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
        headerPanel.add(name);

        //status
        status=new JLabel("Active Now");
        status.setBounds(110,35,100,10);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF",Font.BOLD,14));
        headerPanel.add(status);


        //body panel
        bodyPanel=new JPanel();
        bodyPanel.setBounds(5,75,425,520);


        //msg Box
        msgBox=new JTextField();
        msgBox.setBounds(5,605,320,50);
        msgBox.setFont(new Font("SAN_SERIF",Font.PLAIN,16));

        //send btn
        sendBtn=new JButton("Send");
        sendBtn.setBounds(330,605,90,50);
        sendBtn.setBackground(new Color(7,94,84));
        sendBtn.setForeground(Color.WHITE);
        sendBtn.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        sendBtn.addActionListener(this);


        serverFrame.add(bodyPanel);
        serverFrame.add(headerPanel);
        serverFrame.add(msgBox);
        serverFrame.add(sendBtn);
        serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        serverFrame.setSize(450,700);
        serverFrame.getContentPane().setBackground(Color.WHITE);
        serverFrame.setLocation(200,50);
        serverFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==sendBtn){
            try {
                String out=msgBox.getText();
                JPanel msg=formatLabel(out);

                bodyPanel.setLayout(new BorderLayout());

                JPanel right=new JPanel(new BorderLayout());
                right.add(msg,BorderLayout.LINE_END);

                vertical.add(right);
                vertical.add(Box.createVerticalStrut(15));

                bodyPanel.add(vertical,BorderLayout.PAGE_START);

                //sending msg
                outputStream.writeUTF(out);

                msgBox.setText("");

                //for JFrame repainting
                serverFrame.repaint();
                serverFrame.invalidate();
                serverFrame.validate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static JPanel formatLabel(String out){
        JPanel msgPanel=new JPanel();
        msgPanel.setLayout(new BoxLayout(msgPanel,BoxLayout.Y_AXIS));
        JLabel output=new JLabel("<html><p style=\"width: 150px\">"+out+"</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN,16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        msgPanel.add(output);

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm");
        JLabel time=new JLabel();
        time.setText(dateFormat.format(calendar.getTime()));
        msgPanel.add(time);

        return msgPanel;
    }

    public static void main(String[] args){
        new Server();
        try{
            ServerSocket serverSocket=new ServerSocket(6001);
            while(true){
                Socket socket=serverSocket.accept();
                outputStream=new DataOutputStream(socket.getOutputStream());
                DataInputStream inputStream=new DataInputStream(socket.getInputStream());

                while(true){
                    String msg=inputStream.readUTF();
                    JPanel panel=formatLabel(msg);
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    vertical.add(left);
                    serverFrame.validate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}