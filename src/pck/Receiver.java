package pck;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receiver{
    private final static String QUEUE_NAME = "queue";
    //private final static String QUEUE_NAME2 = "queue2";

    //public void run() {
    public static void receive(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        System.out.println("line 19");
        try (Connection connection1 = factory.newConnection();
             Channel channel = connection1.createChannel(); ){
            System.out.println("line 21");
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {

                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(message);
                //Program.area1.setText(Program.area1.getText() + message + "\n");
                System.out.println(" [x] Received '" + message + "'");
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

        } catch(Exception e){
            e.printStackTrace();
        }

        /*******************************************************************************************/
//        Connection connection2 = factory.newConnection();
//        Channel channel2 = connection2.createChannel();
//
//        channel2.queueDeclare(QUEUE_NAME2,false,false,false,null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//        DeliverCallback deliverCallback2 = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            area.setText(area.getText()+ message+"\n");
//
//            System.out.println(" [x] Received '" + message + "'");
//        };
//        channel2.basicConsume(QUEUE_NAME2, true, deliverCallback2, consumerTag -> { });


    }
}