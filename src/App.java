package HW1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class App {

	// objects
	private static Admin admin = new Admin();
	private static StudentDirectory StudentDir = new StudentDirectory();
	private static CourseDirectory CourseDir = new CourseDirectory();
	private static Scanner myS = new Scanner (System.in);
	private static int loggedStudent;
	
	// authorization booleans
	public static boolean AdminAuthorized(String user, String pass) {
		if (user.equals(admin.getUser()) && pass.equals(admin.getPass())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean StudentAuthorized(String user, String pass) {
		for (int i = 0; i < StudentDir.getDir().size(); i++) {
			if (user.equals(StudentDir.getDir().get(i).getUser()) &&
					pass.equals(StudentDir.getDir().get(i).getPass())) {
				loggedStudent = i;
				return true;
			}
		}
		return false;
	}

	// login method
	public static void login() {
		
		String choice = "";
		
		while (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("n")){
			System.out.println("If you are an admin enter y.  If you are a student enter n.");
			choice = myS.nextLine();
		}
		
		if (choice.equals("y")) {
			boolean status;
			
			do {			
				System.out.println("Enter Username:");
				String user = myS.nextLine();
				System.out.println("Enter Pass:");
				String pass = myS.nextLine();
				
				status = AdminAuthorized(user,pass);
				
				if (status) { 
					runAdmin();
					return;
				}
				
			} while (!status);
	
		} else if (choice.equals("n")) {
			boolean status;
			
			do {
				System.out.println("Enter Username:");
				String suser = myS.nextLine();
				System.out.println("Enter Pass:");
				String spass = myS.nextLine();
				
				status = StudentAuthorized(suser, spass);
				
				if (status) { 
					runStudent(loggedStudent); 
					return;
				}
				
			} while (!status);
			
		} while (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("n"));
	}
	
	// logout method
	public static void logout() {
		System.out.println("Exiting system...");
	}
	
	public static void runAdmin() {
		int choice; 
		
		do {
			System.out.println("Please select:\n" 
				+ "1. Course Management\n"
				+ "2. Reports\n"
				+ "3. Logout");
			choice = myS.nextInt();
			
			if (choice == 1) {
				int menuChoice;
				do {
					myS.nextLine();
					admin.printMenu();
					menuChoice = myS.nextInt();
					switch (menuChoice) {
						case 1:
							CourseDir.addCourse();
							break;
						case 2:
							CourseDir.deleteCourse();
							break;
						case 3:
							CourseDir.editCourse();
							break;
						case 4:
							CourseDir.displayCourse();
							break;
						case 5:
							StudentDir.register();
							break;
						default:
							break;
					}
				} while (menuChoice != 6);
			} else if (choice == 2) {
				int menuChoice;
				do {
					admin.printReportMenu();
					menuChoice = myS.nextInt();
					switch (menuChoice) {
						case 1:
							CourseDir.viewCourses();
							break;
						case 2:
							CourseDir.viewFullCourses();
							break;
						case 3:
							CourseDir.writeFullCourses();
							break;
						case 4:
							CourseDir.viewStudents();
							break;
						case 5:
							StudentDir.viewRegistered();
							break;
						case 6:
							CourseDir.sortList();
							break;
						default:
							break;
					}
				} while (menuChoice != 7);
			} else if (choice == 3) {
				return;
			}
		} while (choice > 0 && choice < 4);
	}
	
	public static void runStudent(int student) {
		int choice;
		Student s = StudentDir.getDir().get(student);
		
		do {
			myS.nextLine();
			s.printMenu();
			choice = myS.nextInt();
			switch (choice) {
				case 1:
					CourseDir.viewCourses(); // checked
					break;
				case 2:
					CourseDir.viewNotFull(); // checked
					break;
				case 3:
					CourseDir.registerStudent(s); // checked (?)
					break;
				case 4:
					CourseDir.withdrawStudent(s);
					break;
				case 5:
					int count = 1;
					for (Course course : s.getRegistered()) {
						System.out.println("" + count + ". " + course.getCourse());
					}
					break;
				default:
					break;
			}
		} while (choice != 6);
		
		return;
	}
	
	public static void serialize() {
		CourseDir.savefile();
		StudentDir.savefile();
	}
	
	public static void deserialize() {
		CourseDir.loadfile();
		StudentDir.loadfile();
	}
	
	// what will run
	public static void main (String[]args) {
		deserialize();
		login();
		serialize();
		logout();
	}
}
