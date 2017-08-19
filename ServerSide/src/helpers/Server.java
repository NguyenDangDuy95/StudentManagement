package helpers;

import controllers.StudentController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Employee;
import models.Message;
import models.Request;
import models.Student;
import models.Verification;

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
        private ObjectOutputStream oos = null;
        private ObjectInputStream ois = null;

        public ServiceThread(Socket socketOfServer, int clientNumber) {
            this.clientNumber = clientNumber;
            this.socketOfServer = socketOfServer;

            displayMessage("New connection with client # " + this.clientNumber + " at " + this.socketOfServer);
        }

        @Override
        public void run() {
            try {
                oos = new ObjectOutputStream(socketOfServer.getOutputStream());
                oos.flush();
                ois = new ObjectInputStream(socketOfServer.getInputStream());

                while (true) {
                    Message message = (Message) ois.readObject();
                    if (message != null) {
                        System.out.println(message.getTitle() + message.getBody());
                        String request = message.getTitle() + message.getBody();
                        if (request.equals("studentlist")) {
                            sendListOfStudent();
                            continue;
                        }
                        if (request.equals(Request.Verification)) {
                            Verification info = message.getInfo();
                            if (info != null) {
                                verificationResponse(verify(info));
                            }
                            continue;
                        }
                        if (request.equals("quit")) {
                            quit();
                            break;
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
            }
        }

        private void sendListOfStudent() throws IOException {
            System.out.println("received request");
            Vector<Student> stdList = new Vector<>();
            for (int i = 0; i < 3; i++) {
                Student std = new Student();
                std.setFirstName("Duy");
                stdList.add(std);
            }
            oos.writeObject(stdList);
            oos.flush();
            System.out.println("send list");
        }

        private void quit() throws IOException {
            oos.writeObject(new Message("Disconnect with cloent# ", String.valueOf(clientNumber)));
            oos.flush();

        }

        private Message verify(Verification account) {
            ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.getAllUser);
            try {
                while (rs.next()) {
                    if (account.getUsername().equals(rs.getString(0))) {
                        if (account.getPassword().equals(rs.getString(1))) {
                            if(rs.getString(3).toLowerCase().equals("student"))
                            {
                                return new Message("Success", StudentController.getStudentByID(rs.getString(2)));
                            }
                            else return new Message("Success", (Employee) DatabaseConnection.getExecutedResultSet(SQLHelper.getElementByID(rs.getString(2), rs.getString(3)))); 
                        }
                        return new Message("Wrong Password", "");
                    }
                    return new Message("Wrong Username or Password", "");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            return new Message("Server Error", "");
        }

        private void verificationResponse(Message msg) {
            try {
                oos.writeObject(msg);
                oos.flush();
            } catch (IOException e) {
            }
        }
    }

    private static void displayMessage(String message) {
        System.out.println(message);
    }
}
