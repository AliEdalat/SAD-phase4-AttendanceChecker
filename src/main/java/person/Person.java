package person;

public class Person {
	
	private String firstName;
	private String lastName;
	private String id;
	
	public Person(String firstName, String lastName, String id) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getId() {
		return id;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

}
