package it.polimi.ingsw.GC_24.cards;


public class Card implements java.io.Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3429619393274815938L;
	
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
