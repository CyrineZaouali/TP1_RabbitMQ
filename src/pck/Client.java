package pck;

public class Client {
    public static void main(String[] args) throws Exception {
        //for (int i=1; i<=Integer.parseInt(args[0]) ;i++)
            Program prg = new Program(1);
            //Receiver rcv = new Receiver();
            prg.start();
            //rcv.start();
        }
    }
