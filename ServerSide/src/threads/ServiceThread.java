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
import services.CourseDataService;
import services.EmployeeDataService;
import services.StudentDataService;

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
                    if (message.getTitle().equals(Request.GetStudentList)) {
                        oos.writeObject(StudentDataService.getStudentList());
                        oos.flush();
                        continue;
                    }
                    
                    if (message.getTitle().equals(Request.GetCourseList)) {
                        oos.writeObject(CourseDataService.getCourseList());
                        oos.flush();
                        continue;
                    }
                    
                    if (message.getTitle().equals(Request.GetEmployeeList)) {
                        oos.writeObject(EmployeeDataService.getEmployeeList());
                        oos.flush();
                        continue;
                    }
                    
                    if (message.getTitle().equals(Request.Verification)) {
                        Verification info = message.getInfo();
                        if (info != null) {
                            response(verify(info));
                        }
                        continue;
                    }

                    if (message.getTitle().equals("quit")) {
                        quit();
                        break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
        } catch (SQLException ex) {
            Logger.getLogger(ServiceThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void quit() throws IOException {
        oos.writeObject(new Message("Disconnect with cloent# ", String.valueOf(clientNumber)));
        oos.flush();
    }

    private Message verify(Verification account) {

        return null;
    }
}
