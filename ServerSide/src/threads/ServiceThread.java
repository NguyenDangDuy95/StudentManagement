/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import models.Message;
import models.Request;
import models.Verification;
import services.BatchDataService;
import services.CourseDataService;
import services.EmployeeDataService;
import services.StudentDataService;
import services.SubjectDataService;
import services.VerificationService;

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
                System.out.println(message.getTitle() + message.getBody());
                if (message != null) {
                    
                    if (message.getTitle().equals(Request.GetStudentList)) {
                        oos.writeObject(StudentDataService.GetStudentList());
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
                    if(message.getTitle().equals(Request.GetBatchList)){
                        oos.writeObject(BatchDataService.getFullBatchList());
                        oos.flush();
                        continue;
                    }
                    
                    if(message.getTitle().equals(Request.DeleteMessage)){
                        if(message.getBody().equals(Request.StudentObject)){
                            oos.writeBoolean(StudentDataService.DeleteStudent(message.getStudent()));
                            oos.flush();
                            continue;
                        }
                        
                        if(message.getBody().equals(Request.EmployeeObject)){
                        
                        }
                    }
                       
                    if(message.getTitle().equals(Request.UpdateMessage)){
                        if(message.getBody().equals(Request.StudentObject)){
                            //have not handle yet
                        }
                        if(message.getBody().equals("attendance")){
                            BatchDataService.updateBatch(message.getBatch(), "attendance");
                        }
                        if(message.getBody().equals("score")){
                            BatchDataService.updateBatch(message.getBatch(), "score");
                        }
                        if(message.getBody().equals(Request.EmployeeObject)){
                            EmployeeDataService.UpdateEmployee(message.getEmployee());
                        }
                    }
                    if(message.getTitle().equals(Request.AddMessage)){
                        if(message.getBody().equals(Request.StudentObject)){                           
                            StudentDataService.AddStudent(message.getStudent());
                        }
                        if(message.getBody().equals(Request.SubjectObject)){
                        
                        }
                        if(message.getBody().equals(Request.EmployeeObject)){
                        
                        }
                    }
                    if (message.getTitle().equals(Request.Verification)) {
                        verify(message.getBody());
                        continue;
                    }

                    if (message.getTitle().equals("quit")) {
                        quit();
                        break;
                    }
                }
            }
            oos.close();
            ois.close();
            socketOfServer.close();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage()+ "ahihi");
        }
    }

    private void quit() throws IOException {
        oos.writeObject(new Message("Disconnect with client# ", String.valueOf(clientNumber)));
        oos.flush();
    }

    private void verify(String body) throws SQLException, IOException{
        System.out.println(body);
        String[] user = body.split(" ");
        Verification account = new Verification(user[0], user[1]);
        Message mgs = VerificationService.checkValidation(account);
        oos.writeObject(mgs);
        oos.flush();
    }
}
