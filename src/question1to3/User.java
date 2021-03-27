package question1to3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User {

    private String SEND_TO_QUEUE_NAME = "queue";
    int userNumber;

    User(int n){
        this.userNumber = n;
        this.SEND_TO_QUEUE_NAME += this.userNumber;
        JFrame f= new JFrame("Sender " + userNumber);
        JTextArea area=new JTextArea("");
        area.setBounds(10,50, 200,40);
        JLabel label = new JLabel(" Welcome! Enter your message ");
        label.setBounds(5,1, 300,40);
        label.setForeground(Color.DARK_GRAY);
        f.add(label);
        f.add(area);
        f.setSize(350,150);
        f.setLayout(null);
        f.setVisible(true);
        JButton button = new JButton("Send");
        button.setBounds(220,50,70,40);
        button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Sender sender = new Sender(area.getText());
                        try {
                            sender.sendMessages(SEND_TO_QUEUE_NAME);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
        );
        f.add(button);

    }

    public static void main(String[] argv)throws Exception{
        for(int i=1; i<=Integer.parseInt(argv[0]); i++)
            new User(i);
    }

}