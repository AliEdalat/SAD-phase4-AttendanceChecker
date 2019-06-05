package person;

import card.ProfessorCard;

public class Professor extends Person{

	private ProfessorCard card;

	public Professor(String firstName, String lastName, Integer id) {
		super(firstName, lastName, id);
		card = new ProfessorCard(id);
	}

}
