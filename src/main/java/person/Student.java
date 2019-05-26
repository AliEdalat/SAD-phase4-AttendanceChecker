package person;

public class Student extends Person{
	
	private int chairNumber;

	public Student(String firstName, String lastName, String id, int chairNumber) {
		super(firstName, lastName, id);
		this.chairNumber = chairNumber;
	}
	
	public int getChairNumber() {
		return chairNumber;
	}

}
