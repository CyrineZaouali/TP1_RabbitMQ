package question4and5;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import question1to3.Sender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class P2 {
    private final static String QUEUE_NAME_1 = "queue1";
    private final static String QUEUE_NAME_2 = "queue2";
    private int id;

    P2() throws IOException, TimeoutException {

        JFrame f = new JFrame("Sender 2 Zone ");
        JPanel container = new JPanel();
        JScrollPane scrPane = new JScrollPane(container);
        f.add(scrPane);
        f.setSize(600, 800);
        f.setLayout(null);
        f.setVisible(true);

        JTextArea area1 = new JTextArea("");
        JLabel label1 = new JLabel(" write here : ");
        label1.setBounds(5, 1, 200, 40);
        label1.setForeground(Color.DARK_GRAY);
        f.add(label1);
        area1.setEditable(true);
        area1.setBounds(5, 50, 300, 200);
        f.add(area1);

        JButton button = new JButton("Send");
        button.setBounds(350,200,70,40);
        button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Sender sender = new Sender(area1.getText());
                        try {
                            sender.sendMessages(QUEUE_NAME_2);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }
        );
        f.add(button);


        JTextArea area2 = new JTextArea("");
        JLabel label2 = new JLabel(" Sender 1 wrote : ");
        label2.setBounds(5, 300, 200, 40);
        label2.setForeground(Color.DARK_GRAY);
        f.add(label2);
        area2.setEditable(false);
        area2.setBounds(5, 350, 300, 200);
        f.add(area2);

        JButton onEdit = new JButton("Edit");
        onEdit.setBounds(150, 600, 70, 40);
        onEdit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        area2.setEditable(true);

                    }
                });
        f.add(onEdit);

        JButton onSave = new JButton("Save");
        onSave.setBounds(250, 600, 70, 40);
        onSave.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        area1.setEditable(false);
                    }
                }
        );
        f.add(onSave);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection1 = factory.newConnection();
        Channel channel1 = connection1.createChannel();
        channel1.queueDeclare(QUEUE_NAME_1, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback1 = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            area2.setText(area2.getText() + message + "\n");

            System.out.println(" [x] Received '" + message + "'");
        };
        channel1.basicConsume(QUEUE_NAME_1, true, deliverCallback1, consumerTag -> {
        });
    }

}
