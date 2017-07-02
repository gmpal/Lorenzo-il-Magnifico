package it.polimi.ingsw.GC_24.model.cards;


public class Card implements java.io.Serializable{	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3554603775414321683L;
	protected String name;


	public Card(String name) {
		this.name = name;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "\n";
	}
}
