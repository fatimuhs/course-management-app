package HW1;
import java.io.Serializable;

abstract class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// priv var user, pass, fname, lname
	private String user, pass, fname, lname;
	
	// default constructor
	public User() {
		this.user = "";
		this.pass = "";
		this.fname = "";
		this.lname = "";
	}
	
	// 4 parameter constructor
	public User(String user, String pass, String first, String last) {
		this.user = user;
		this.pass = pass;
		this.fname = first;
		this.lname= last;
	}

	
	// getters
	public String getUser() { return user; }
	public String getPass() { return pass; }
	public String getFname() { return fname; }
	public String getLname() { return lname; }

	// setters
	public void setUser(String user) { this.user = user; }
	public void setPass(String pass) { this.pass = pass; }
	public void setFname(String fname) { this.fname = fname; }
	public void setLname(String lname) { this.lname = lname; }
	
	abstract void printMenu();
}
