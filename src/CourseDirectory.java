package HW1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

/**
 * // admin course management methods
	void addCourse();
	void delCourse();
	void editCourse();
	void displayCourse();
	void register();
	void exit();
	
	// admin report methods	
	void viewCourse();
	void viewFull();
	void outputFull();
	void viewCourseStudents();
	void viewGivenStudent();
	void sort();

	// student course management	
	void viewCourse();
	void sviewNotFull();
	void register();
	void withdraw();
	void sviewCurrent();
	void sexit();
 * @author Fatima
 **/


public class CourseDirectory extends Course {
	
	/**
	 * 
	 */
	// private variables
	private static final long serialVersionUID = 1L;
	private static ArrayList<Course> CourseList = new ArrayList<>();
	private Scanner in = new Scanner(System.in);
	
	// default constructor
	public CourseDirectory() {	
	}
	
	// arraylist of all the courses
	public ArrayList<Course> getDir() {
		return CourseList;
	}
	
	// serialization methods: save/load/read
	public void savefile() {
		try {
			//write data to a file
			FileOutputStream fos = new FileOutputStream("CourseDirectory.ser");			
			//write objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//writes objects to oos
			oos.writeObject(CourseList);
			
			// close streams
			oos.close();
			fos.close();			
			System.out.println("Course save complete.");
		} catch (IOException e) {
			System.out.println("Error saving file: " + e.getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	public void loadfile() {
		try {
			File file = new File("CourseDirectory.ser");
			
			if (file.exists()) {
				FileInputStream fis = new FileInputStream("CourseDirectory.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object o = ois.readObject();
					
				CourseList = (ArrayList<Course>)o;
				ois.close();
				fis.close();
			} else {
				readfile();
			}
		} catch(IOException | ClassNotFoundException e) {
		   	e.printStackTrace();
		   	return;
		}
	}
	public void readfile() {
		try{
			BufferedReader reader = new BufferedReader(new FileReader("src/MyUniversityCourses.csv"));
			String line = reader.readLine();
			line = reader.readLine();
			
			while (line != null) {
				// split with each comma
				String[] parts = line.split(",");
				String course = parts[0];
				String id = parts[1];
				int max = Integer.parseInt(parts[2]);
				int current = Integer.parseInt(parts[3]);
				ArrayList<Student> students = null;
				String instructor = parts[5];
				int section = Integer.parseInt(parts[6]);
				String location = parts[7];
				line = reader.readLine();
				Course de = new Course (course,id,max,current,students,instructor,section,location);
				CourseList.add(de);
			}
			
			reader.close();
		 } catch (IOException ioe) {
			    ioe.printStackTrace();
		 }
	}
	
	// overloaded find methods
	public Course find() {
		System.out.println("Enter id:");
		String id = in.nextLine();
		
		for (int i=0; i < CourseList.size(); i++) {
			if (CourseList.get(i).getId() == id) {
				return CourseList.get(i);
			}
		}
		
		return null;
	}
	public Course find(String id) {
		Course find = null;
		
		for (int i=0; i < CourseList.size(); i++) {
			Course current = CourseList.get(i);
			if (current.getId().equals(id)) {
				find = CourseList.get(i);
				break;
			}
		}
		
		return find;
	}
	
	// general courselist editing methods
	public void addCourse() {
		System.out.println("Enter Course Information: ");
		System.out.println("Course title: ");
		String title = in.next();
		in.nextLine();
		System.out.println("Course id: ");
		String id = in.nextLine();
		System.out.println("Max Students: ");
		int max = in.nextInt();
		in.nextLine();
		System.out.println("Current Students: ");
		int current = in.nextInt();
		in.nextLine();
		ArrayList<Student> students = new ArrayList<>();
		System.out.println("Instructor: ");
		String instructor = in.next();
		System.out.println("Section: ");
		int section = in.nextInt();
		System.out.println("Location: ");
		String location = in.next();
		
		Course e = new Course(title, id, max, current, students, instructor, section, location);
		CourseList.add(e);
		System.out.println("Course has been added.");
	}
	public void deleteCourse() {
		in.nextLine();
		System.out.println("Enter Course ID: ");
		String id = in.nextLine();
		CourseList.remove(find(id));
		System.out.println("Course has been removed.");
	}
	public void editCourse() {
		in.nextLine();
		System.out.println("Enter course id: ");
		String id = in.nextLine();
		
		System.out.println("What would you like to edit?:\n"
				+ "1. Max Students\r\n"
				+ "2. Instructor\r\n"
				+ "3. Section\r\n"
				+ "4. Location");
		int choice = in.nextInt();
		
		switch(choice) {
			case 1:
				System.out.println("Enter new max number of students:");
				int max = in.nextInt();
				find(id).setMax(max);
				System.out.println("Edit saved.");
				break;
			case 2:
				System.out.println("Enter new Instructor name:");
				String inst = in.nextLine();
				find(id).setInstructor(inst);
				System.out.println("Edit saved.");
				break;
			case 3:
				System.out.println("Enter new Section number:");
				int num = in.nextInt();
				find(id).setSection(num);
				System.out.println("Edit saved.");
				break;
			case 4:
				System.out.println("Enter new Location:");
				String location = in.nextLine();
				find(id).setLocation(location);
				System.out.println("Edit saved.");
				break;
			default:
				System.out.println("Error.");
		}
	}
	public void displayCourse() {
		in.nextLine();
		System.out.println("Enter Course ID:");
		String id = in.nextLine();
		Course c = find(id);
		
		if (c != null) {
			String display = c.toString();
			System.out.println(display);
		} else {
			System.out.println("Course not found.");
		}
	}
	public void viewCourses() {
		for (int i = 0; i < CourseList.size(); i++) {
			System.out.println((i+1) + ". " + CourseList.get(i).toString());
		}
	}
	public void viewFullCourses() {
		for (int i = 0; i < CourseList.size(); i++) {
			if (CourseList.get(i).getMax() == CourseList.get(i).getCurrent()) {
				System.out.println((i+1) + ". " + CourseList.get(i).toString());
			}
		}
	}
	public void viewNotFull() {
		for (int i = 0; i < CourseList.size(); i++) {
			if (CourseList.get(i).getMax() != CourseList.get(i).getCurrent()) {
				System.out.println((i+1) + ". " + CourseList.get(i).toString());
			}
		}
	}
	public void writeFullCourses() {
		ArrayList<Course> full = new ArrayList<>();
		
		for (int i = 0; i < CourseList.size(); i++) {
			if (CourseList.get(i).getMax() == CourseList.get(i).getCurrent()) {
				full.add(CourseList.get(i));
			}
		}
		
		try {
			FileOutputStream fos = new FileOutputStream("FullCourses.ser");
			
			//write objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			//writes objects to oos
			oos.writeObject(full);
			
			// close streams
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void viewStudents() {
		in.nextLine();
		System.out.println("Enter id: ");
		String id = in.nextLine();
		Course c = find(id);
		
		c.getStuNames();
	}
	
	public void sortList() {
		Course temp;
		for (int i=0; i < CourseList.size(); i++) {
			for (int j=0; j <CourseList.size(); j++) {
				if ( CourseList.get(i).getCurrent() > CourseList.get(j).getCurrent()) {
					temp = CourseList.get(i);
					CourseList.set(i, CourseList.get(j));
					CourseList.set(j, temp);
				}
			}
		}

		System.out.println("Course has been sorted.");
	}

	public void registerStudent(Student stu) {
		in.nextLine();
		System.out.println("Enter Course Name: ");
		String name = in.nextLine();
		System.out.println("Enter Section Number: ");
		String section = in.nextLine();
		
		System.out.println("Enter id:");
		String id = in.nextLine();
		Course course = find(id);
		
		int max = course.getMax();
		int current = course.getCurrent();
		
		if (current < max) {
			course.addStudentToCourseList(stu);
			stu.add(course);
			course.setCurrent(current + 1);
			System.out.println(stu.getFname() + " " + stu.getLname() + " has been added to " + name );
		} else {
			System.out.println("Course is Full. Unable to add.");
		}
	}
	
	public void withdrawStudent(Student stu) {
		in.nextLine();
		System.out.println("Enter Course Name: ");
		String name = in.nextLine();
		System.out.println("Enter Course id:");
		String id = in.nextLine();
		
		Course course = find(id);
		
		boolean withdrawn = course.getStudents().remove(stu);
		
		if (withdrawn) { 
			stu.remove(course);
			System.out.println("Student has been withdrawn from " + name + ".");
		} else {
			System.out.println("Student or course not found.");
		}
	}

}
