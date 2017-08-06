package threads;

import com.models.Student;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import helpers.DatabaseConnection;
import helpers.SQLHelper;


public class ServerThread extends Thread{
	private Socket socket;
	private int index;
	public ServerThread(Socket socket, int index) {
		this.socket = socket;
		this.index = index;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		try {
			//ois = new ObjectInputStream(socket.getInputStream());
			//Student std1 =  (Student) ois.readObject();
			//System.out.printf("Student #%d name: %s age: %s", std1.getId(),std1.getName(),std1.getAge());
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String request = br.readLine();
			if(request.equalsIgnoreCase("Send me list of students"))
			{
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				ResultSet rs = DatabaseConnection.getExecutedResultSet(SQLHelper.createSelectSQL("STUDENT", "*"));
				ArrayList<Student> stdList = new ArrayList<Student>();
				while(rs.next())
				{
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String age = rs.getString("age");
					Student std = new Student();
					stdList.add(std);
				}
				oos.writeObject(stdList);	
				oos.flush();
				rs.close();
				oos.close();
			}
			br.close();
		
			
		} catch (IOException | SQLException e) {
                    // TODO Auto-generated catch block
		}
            // TODO Auto-generated catch block	
	}
}
