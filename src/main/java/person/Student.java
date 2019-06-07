package person;

import card.StudentCard;

public class Student extends Person{

	private StudentCard studentCard;

	public Student(String firstName, String lastName, int id) {
		super(firstName, lastName, id);
		studentCard = new StudentCard(id);
	}

	public void showInfo(){
		System.out.println("Student id: " + this.id);
		System.out.println("First name: " + this.firstName);
		System.out.println("Last name: " + this.lastName);
	}
}
