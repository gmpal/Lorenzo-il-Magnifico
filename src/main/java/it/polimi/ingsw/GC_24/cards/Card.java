package it.polimi.ingsw.GC_24.cards;

public class Card {

	private String name;

	public Card(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Card [name=" + name + "]";
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
