package question4and5;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {
    private String message;
    private static String EXCHANGE_NAME = "msg";
    Sender(String message){
        this.message = message;
    }
    public void sendMessages (String QUEUE_NAME) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
            System.out.println(" [x] send : "+message );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
