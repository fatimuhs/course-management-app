package HW1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentDirectory {
	
	/** NOTE: TEST STUDENT HAS [USER: test] AND [PASS: test]
	 * **/
	public ArrayList<Student> studentList = new ArrayList<>();
	private Scanner myS = new Scanner(System.in);
	
	public StudentDirectory() {
	}
	
	public void addStudent(Student student) {
		studentList.add(student);
	}
	
	public void removeStudent(Student student) {
		studentList.remove(student);
	}
	
	public ArrayList<Student> getDir() {
		return studentList;
	}

	public Student find(String user) {
		for (int i = 0; i < studentList.size(); i++ ) {
			if (studentList.get(i).getUser().equals(user)) {
				return studentList.get(i);
			}
		}
		
		return null;
	}
	
	public Student find() {
		System.out.println("Enter user: ");
		String user = myS.nextLine();
		for (int i = 0; i < studentList.size(); i++ ) {
			if (studentList.get(i).getUser().equals(user)) {
				return studentList.get(i);
			}
		}
		
		return null;
	}
	
	// admin method
	public void viewRegistered() {
		System.out.println(find().getRegistered());
	}
	
	@SuppressWarnings("unchecked")
	public void loadfile() {
		//FileInputSystem recieves bytes from a file
		try {
			File file = new File("StudentDirectory.ser");
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object o = ois.readObject();
					
				studentList = (ArrayList<Student>)o;
				ois.close();
				fis.close();
			} else {
				System.out.println("No Students currently in system.");
			}
		} catch(IOException ioe) {
		   	ioe.printStackTrace();
		   	return;
		} catch(ClassNotFoundException cnfe) {
		   	cnfe.printStackTrace();
		   	return;
		}
	}
	
	public void savefile() {
		ArrayList<Student> s = new ArrayList<>();
		for (Student stu : studentList) {
			s.add(stu);
		}
		
		try {
			//write data to a file
			FileOutputStream fos = new FileOutputStream("StudentDirectory.ser");			
			//write objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			//writes objects to oos
			oos.writeObject(s);
			
			// close streams
			oos.close();
			fos.close();			
			System.out.println("Student save complete.");
		} catch (IOException e) {
			System.out.println("In IOException");
		}
	}
	
	public void register() {
		
		System.out.println("Enter user: ");
		String user = myS.nextLine();
		System.out.println("Enter pass: ");
		String pass = myS.nextLine();
		System.out.println("Enter first name: ");
		String fname = myS.nextLine();
		System.out.println("Enter last name: ");
		String lname = myS.nextLine();
		Student add = new Student(user,pass,fname,lname);
		studentList.add(add);
		System.out.println("Student has been added.");
		/** TESTING for (Student s: studentList) {
			System.out.println(s);
		}**/
	}
}
