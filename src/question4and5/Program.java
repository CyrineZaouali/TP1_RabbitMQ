package question4and5;

public class Program {
    private final static String QUEUE_NAME_1 = "queue1";
    private final static String QUEUE_NAME_2 = "queue2";
    private int id;

//    Program(int i) throws IOException, TimeoutException {
//
//        this.id = i;
//
//        JFrame f = new JFrame("Sender " + this.id + " Zone ");
//        JPanel container = new JPanel();
//        JScrollPane scrPane = new JScrollPane(container);
//        f.add(scrPane);
//        f.setSize(600, 600);
//        f.setLayout(null);
//        f.setVisible(true);
//
//        JTextArea area1 = new JTextArea("");
//        JLabel label1 = new JLabel(" Sender 2 wrote : ");
//        label1.setBounds(5, 1, 200, 40);
//        label1.setForeground(Color.DARK_GRAY);
//        f.add(label1);
//        area1.setEditable(false);
//        area1.setBounds(5, 20, 300, 200);
//        f.add(area1);
//
//        JTextArea area2 = new JTextArea("");
//        JLabel label2 = new JLabel(" Sender 2 wrote : ");
//        label2.setBounds(5, 230, 200, 40);
//        label2.setForeground(Color.DARK_GRAY);
//        f.add(label2);
//        area2.setEditable(false);
//        area2.setBounds(5, 250, 300, 200);
//        f.add(area2);
//
//        JButton onEdit = new JButton("Edit");
//        onEdit.setBounds(150, 220, 70, 40);
//        onEdit.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        area2.setEditable(true);
//
//                    }
//                });
//        f.add(onEdit);
//
//        JButton onSave = new JButton("Save");
//        onSave.setBounds(250, 220, 70, 40);
//        onSave.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        area1.setEditable(false);
//                    }
//                }
//        );
//        f.add(onSave);
//
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//
//        Connection connection1 = factory.newConnection();
//        Channel channel1 = connection1.createChannel();
//        channel1.queueDeclare(QUEUE_NAME_2, false, false, false, null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//        DeliverCallback deliverCallback1 = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            area1.setText(area1.getText() + message + "\n");
//
//            System.out.println(" [x] Received '" + message + "'");
//        };
//        channel1.basicConsume(QUEUE_NAME_2, true, deliverCallback1, consumerTag -> {
//        });
//    }

    public static void main(String[] argv)throws Exception{
        //new Program(Integer.parseInt(argv[0]));
        new P1();
        new P2();
    }
}
