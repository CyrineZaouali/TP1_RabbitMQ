package pck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Program extends Thread{

    private static String QUEUE_NAME = "queue" ;
    int userNumber;
    public JTextArea area;
    public static JTextArea area1;


    Program(int n) {
        this.userNumber = n;
    }
    public void run(){

        JTextArea area = new JTextArea("");
        JTextArea area1 = new JTextArea(20, 20);
        JFrame f = new JFrame("Sender " + userNumber);
        area.setBounds(10, 50, 200, 40);
        // JTextComponent txt = new JTextComponent("Welcome! ") {};
        JLabel label = new JLabel(" Welcome! Enter your message ");
        label.setBounds(5, 1, 300, 40);
        label.setForeground(Color.DARK_GRAY);
        f.add(label);
        f.add(area);
        f.setSize(350, 150);
        f.setLayout(null);
        f.setVisible(true);
        JButton button = new JButton("Send");
        button.setBounds(220, 50, 70, 40);
        button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Sender sender = new Sender(area.getText());
                        try {
                            sender.sendMessages();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
        );
        f.add(button);

        area1.setBounds(20, 150, 500, 300);
        area1.setBackground(Color.WHITE);
        area1.setForeground(Color.DARK_GRAY);
        area1.setEditable(false);
        f.add(area1);

        JButton onEdit = new JButton("Edit");
        onEdit.setBounds(220, 500, 70, 40);
        onEdit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        area1.setEditable(true);

                    }
                }
        );
        f.add(onEdit);

        JButton onSave = new JButton("Save");
        onSave.setBounds(320, 500, 70, 40);
        onSave.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        area1.setEditable(false);
                    }
                }
        );
        f.add(onSave);

            Receiver.receive();

//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        System.out.println("line 19");
//        try (Connection connection1 = factory.newConnection();
//             Channel channel = connection1.createChannel(); ){
//            System.out.println("line 21");
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//                System.out.println("line 26");
//                String message = new String(delivery.getBody(), "UTF-8");
//                Program.area1.setText(Program.area1.getText() + message + "\n");
//                System.out.println(" [x] Received '" + message + "'");
//            };
//            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
//            System.out.println("line 32");
//
//        } catch(Exception e){
//            e.printStackTrace();
//        }
    }



}