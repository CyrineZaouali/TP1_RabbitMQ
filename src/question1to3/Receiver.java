package question1to3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receiver{
//    private final static String QUEUE_NAME_1 = "queue1";
//    private final static String QUEUE_NAME_2 = "queue2";
    private final static String BASE_QUEUE_NAME = "queue";
    private static int nb_users;


    Receiver(int n) throws IOException, TimeoutException {
        this.nb_users = n;

        JFrame f= new JFrame("Receiver ");
        JPanel container = new JPanel();
        JScrollPane scrPane = new JScrollPane(container);
        f.add(scrPane);

        f.setSize(600,1000);
        f.setLayout(null);
        f.setVisible(true);

        for(int i=1; i <= this.nb_users; i++) {
            JTextArea area = new JTextArea("");
            JLabel label = new JLabel(" Sender" + i + "  wrote : ");
            label.setBounds(5, -5 + (i - 1) * 300, 150, 150);
            label.setForeground(Color.DARK_GRAY);
            f.add(label);
            area.setEditable(false);
            area.setBounds(5, 100 + (i - 1) * 300, 500, 200);
            f.add(area);

            JButton onEdit = new JButton("Edit");
            onEdit.setBounds(150, 310 + (i - 1) * 300, 70, 40);
            onEdit.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            area.setEditable(true);

                        }});
            f.add(onEdit);

            JButton onSave = new JButton("Save");
            onSave.setBounds(250, 310+ (i - 1) * 300, 70, 40);
            onSave.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            area.setEditable(false);
                        }
                    }
            );
            f.add(onSave);

            String QUEUE_NAME = BASE_QUEUE_NAME + i;
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            DeliverCallback deliverCallback1 = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                area.setText(area.getText() + message + "\n");

                System.out.println(" [x] Received '" + message + "'");
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback1, consumerTag -> { });
        }


//        JTextArea area2 = new JTextArea("");
//        JLabel label2 = new JLabel(" Sender 2 wrote : ");
//        label2.setBounds(5,350, 200,40);
//        label2.setForeground(Color.DARK_GRAY);
//        f.add(label2);
//        area2.setEditable(false);
//        area2.setBounds(5,400, 500,200);
//        f.add(area2);

        //Question 2
//        JButton onEdit1 = new JButton("Edit");
//        onEdit1.setBounds(150, 310+ (i - 1) * 300, 70, 40);
//        onEdit1.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        area1.setEditable(true);
//
//                    }});
//        f.add(onEdit1);
//        JButton onEdit2 = new JButton("Edit");
//        onEdit2.setBounds(150, 610, 70, 40);
//        onEdit2.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        area2.setEditable(true);
//
//                    }});
//        f.add(onEdit2);

//        JButton onSave1 = new JButton("Save");
//        onSave1.setBounds(250, 310, 70, 40);
//        onSave1.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        area1.setEditable(false);
//                    }
//                }
//        );
//        f.add(onSave1);
//        JButton onSave2 = new JButton("Save");
//        onSave2.setBounds(250, 610, 70, 40);
//        onSave2.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        area2.setEditable(false);
//                    }
//                }
//        );
//        f.add(onSave2);

//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//
//        Connection connection1 = factory.newConnection();
//        Channel channel1 = connection1.createChannel();
//        channel1.queueDeclare(QUEUE_NAME_1, false, false, false, null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//        DeliverCallback deliverCallback1 = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            area1.setText(area1.getText() + message + "\n");
//
//            System.out.println(" [x] Received '" + message + "'");
//        };
//        channel1.basicConsume(QUEUE_NAME_1, true, deliverCallback1, consumerTag -> { });
//
//        Connection connection2 = factory.newConnection();
//        Channel channel2 = connection1.createChannel();
//        channel2.queueDeclare(QUEUE_NAME_2, false, false, false, null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//        DeliverCallback deliverCallback2 = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            area2.setText(area2.getText() + message + "\n");
//
//            System.out.println(" [x] Received '" + message + "'");
//        };
//        channel1.basicConsume(QUEUE_NAME_2, true, deliverCallback2, consumerTag -> { });
    }

    public static void main(String[] argv)throws Exception{
        new Receiver(Integer.parseInt(argv[0]));
    }
}