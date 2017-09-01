/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import helpers.DatabaseConnection;
import helpers.SQLHelper;
import helpers.Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Message;
import models.Request;
import models.Student;
import models.Verification;

/**
 *
 * @author Duy
 */
public class ServiceThread extends Thread {

    private int clientNumber;
    private Socket socketOfServer;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;

    public ServiceThread(Socket socketOfServer, int clientNumber) {
        this.clientNumber = clientNumber;
        this.socketOfServer = socketOfServer;

        displayMessage("New connection with client # " + this.clientNumber + " at " + this.socketOfServer);
    }
    private static void displayMessage(String message) {
        System.out.println(message);
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
                            response(verify(info));
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
        ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.GetAllUser);
        Message mss = null;
        try {

            while (rs.next()) {
                System.out.println("usernameDB" + rs.getString(1));
                System.out.println("UsernameInput" + account.getUsername());
                if (account.getUsername().equals(rs.getString(1))) {
                    System.out.println("PassDB" + rs.getString(2));
                    System.out.println("PassInput" + account.getPassword());
                    if (account.getPassword().trim().equalsIgnoreCase(rs.getString(2).trim())) {
                        System.out.println("ahihi");
                        System.out.println(rs.getString(3));
                        System.out.println(rs.getString(4));
                        if (rs.getString(4).toLowerCase().equals("student")) {
                            System.out.println("ok man");
                            //mss = new Message("Success", StudentController.getStudentByID(rs.getString(3)));
                            break;
                            //return new Message("Success", StudentController.getStudentByID(rs.getString(3)));
                        }
                        //else return new Message("Success", "ahihi"); 
                    }

                }
                mss = new Message("Server Error", "");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mss;
    }

    private void response(Message msg) {
        try {
            oos.writeObject(msg);
            oos.flush();
        } catch (IOException e) {
        }
    }
}
