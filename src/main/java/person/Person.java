package person;

public class Person {
	
	private String firstName;
	private String lastName;
	private int id;
	
	public Person(String firstName, String lastName, int id) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

}
