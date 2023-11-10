package HW1;

// interface
interface IA {
	// course/student editing methods accessed through coursedirectory
	void printMenu();
	void printReportMenu();
}

public class Admin extends User implements IA {

	private static final long serialVersionUID = 1L;
	
	// one admin in program
	public Admin() { super("Admin", "Admin001", "Admin", ""); }

	public void printMenu() {
		System.out.println("1. Create a new course\r\n"
				+ "2. Delete a course\r\n"
				+ "3. Edit a course (except for course ID and name)\r\n"
				+ "4. Display information for a given course (by course ID)\r\n"
				+ "5. Register a student\r\n"
				+ "6. Exit");
	}
	public void printReportMenu() {
		System.out.println("1. View all courses.\r\n"
				+ "2. View all courses that are FULL (reached the maximum number of students).\r\n"
				+ "3. Write to a file the list of course that are Full.\r\n"
				+ "4. View the names of the students being registered in a specific course.\r\n"
				+ "5. View the list of courses that a given student is being registered on.\r\n"
				+ "6. Sort course list based on current number of students.\r\n"
				+ "7. Exit");
	}
	public void um() {
		
	}
}
