package person;

public class Person {
	
	protected String firstName;
	protected String lastName;
	protected int id;
	
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
