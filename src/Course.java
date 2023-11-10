package HW1;
import java.util.ArrayList;

public class Course implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// declare private variables -- 
		// course name, id, max, current
		// names, instructor, section number, location
	// transient to ensure data cannot be serialized
	private String course;
	private String id;
	private int max;
	private int current;
	private ArrayList<Student> students;
	private String instructor;
	private int section;
	private String location;
	
	// default constructor
	public Course() {
		this.course = "";
		this.id = "";
		this.max = 0;
		this.current = 0;
		this.students = null;
		this.instructor = "";
		this.section = 0;
		this.location =	 "";
	}
	
	// constructor
	public Course(String course, String id, int max, int current, ArrayList<Student> students, String instructor, int section, String location) {
		this.course = course;
		this.id = id;
		this.max = max;
		this.current = current;
		this.students = students;
		this.instructor = instructor;
		this.section = section;
		this.location = location;
	}

	// getters and setters
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public void printCourse() {
		System.out.println("" + course + "-- ID = " + id + ", MAX =" + max + ", CURRENT=" + current + ", STUDENTS ="
				+ students + ", INSTRUCTOR =" + instructor + ", SECTION =" + section + ", LOCATION =" + location);
	}	
	
	
	@Override
	public String toString() {
		return "[" + course + ", id=" + id + ", max=" + max + ", current=" + current + ", students="
				+ students + ", instructor=" + instructor + ", section=" + section + ", location=" + location + "]";
	}

	public void getStuNames() {
		ArrayList<Student> studentlist = getStudents();
		// see if student list is null first
		if (students == null) {
			System.out.println("No students in this class");
		} else {
			int count = 1;
			for (Student stu : students) {
				String name = stu.toString();
				System.out.println(name);
				count++;
			}
		}
	}
	
	public void addStudentToCourseList(Student e) {
		students.add(e);
	}
}
