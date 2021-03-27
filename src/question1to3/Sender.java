package question1to3;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Sender {
    private String message;
    public Sender(String message){
        this.message = message;
    }
    public void sendMessages (String QUEUE_NAME) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println(" [x] send : "+message );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
