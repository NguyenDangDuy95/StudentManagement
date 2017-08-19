package helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Message;
import models.Student;

public class Server {

    private ServerSocket listener = null;
    private int clientNumber = 0;

    public void serve() throws IOException {
        System.out.println("Server is waiting to accept user...");
        try {
            listener = new ServerSocket(5555);
            while (true) {
                Socket socketOfServer = listener.accept();
                new ServiceThread(socketOfServer, clientNumber++).start();
            }

        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        } finally {
            listener.close();
        }
    }

    private static class ServiceThread extends Thread {

        private int clientNumber;
        private Socket socketOfServer;

        public ServiceThread(Socket socketOfServer, int clientNumber) {
            this.clientNumber = clientNumber;
            this.socketOfServer = socketOfServer;

            displayMessage("New connection with client # " + this.clientNumber + " at " + this.socketOfServer);
        }

        @Override
        public void run() {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(socketOfServer.getOutputStream());
                oos.flush();
                ObjectInputStream ois = new ObjectInputStream(socketOfServer.getInputStream());
                
                while (true) {
                    Message message = (Message)ois.readObject();
                    if (message != null) {
                        System.out.println(message.getTitle()+message.getBody());
                        if((message.getTitle()+message.getBody()).equals("studentlist"))
                        {
                            
                            System.out.println("received request");
                            Vector<Student> stdList = new Vector<>();
                            for(int i =0;i<3;i++)
                            {
                                Student std = new Student();
                                std.setFirstName("Duy");
                                stdList.add(std);
                            }
                            oos.writeObject(stdList);
                            oos.flush();
                            System.out.println("send list");
                        }
                        if (message.getTitle().equals("quit")) {
                            oos.writeObject(new Message("Disconnect with cloent# ", String.valueOf(clientNumber)));
                            oos.flush();
                            break;
                        }
                    }
                    else
                    {
                        
                        try {
                            
                        } catch (Exception e) {
                        }
                    }
                }
            } catch (IOException e) {

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private static void displayMessage(String message) {
        System.out.println(message);
    }
}
