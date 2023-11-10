package HW1;
import java.io.Serializable;
import java.util.ArrayList;

// interface
interface IS {
	void printMenu();
	ArrayList<Course> getRegistered();
	void addCourseToStudent(Course e);
}

public class Student extends User implements Serializable, IS {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Course> registered = new ArrayList<>();
	
	// constructor calls super
	public Student(String user, String pass, String fname, String lname) { super(user,pass,fname,lname); }
	
	// student menu
	public void printMenu() {
		System.out.println("1. View all courses"
				+ "\n2. View all courses that are not FULL"
				+ "\n3. Register on a course"
				+ "\n4. Withdraw from a course"
				+ "\n5. View all courses that the current student is being registered in"
				+ "\n6. Exit");
	}
	
	// registered arraylist methods
	public ArrayList<Course> getRegistered() {
		return registered;
	}
	
	public void add(Course c) {
		registered.add(c);
	}
	
	public void remove(Course c) {
		registered.remove(c);
	}
	
	@Override
	public String toString() {
		return "Student [ " + getLname() + ", " + getFname() + "]";
	}

	public void addCourseToStudent(Course e) {
		registered.add(e);
	}
}
