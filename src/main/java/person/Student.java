package person;

import card.StudentCard;

public class Student extends Person{

	private StudentCard studentCard;

	public Student(String firstName, String lastName, String id) {
		super(firstName, lastName, id);
		studentCard = new StudentCard(id);
	}

}
