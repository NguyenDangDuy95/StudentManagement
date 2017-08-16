package helpers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import com.models.Student;

public class ServerConnection {
	PrintStream ps = null;
	ObjectInputStream ois = null;
	private Socket socket;

	@SuppressWarnings("unchecked")
	public ArrayList<Student> connect() {
		ArrayList<Student> stdList=null;
		try {
			System.out.println("Client 1 is ready");
			socket = new Socket("192.168.1.77", 3001);
			ps = new PrintStream(socket.getOutputStream());
			ps.println("Send me list of students");
			ps.flush();
			// ObjectOutputStream oos = new ObjectOutputStream(
			// socket.getOutputStream());
			// Student std1 = new Student(1, "Duy", "21");
			// oos.writeObject(std1);
			stdList = new ArrayList<>();
			ois = new ObjectInputStream(socket.getInputStream());
			stdList = (ArrayList<Student>) ois.readObject();

			//for (Student std : stdList) {
			//	System.out.println(std.getId() + std.getName() + std.getAge());
			//}

			ois.close();
			ps.close();
		} catch (IOException | ClassNotFoundException e) {
                    // TODO Auto-generated catch block

		}
            // TODO Auto-generated catch block
		return stdList;
	}
}
