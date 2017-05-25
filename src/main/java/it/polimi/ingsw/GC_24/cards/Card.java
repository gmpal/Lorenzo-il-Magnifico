package it.polimi.ingsw.GC_24.cards;

public class Card {
	
	private String name;
	private boolean permanentEffect; // this three boolean variables are
	private boolean immediateEffect; // used to check if these effects 
	private boolean specialEffect; // are present in the card
	
	public Card(String name,boolean permeff, boolean immeff, boolean speceff){
		this.name = name;
		this.permanentEffect = permeff;
		this.immediateEffect = immeff;
		this.specialEffect = speceff;
	}
	
		@Override
		public String toString() {
			return "Card [name=" + name + ", permanentEffect=" + permanentEffect +
					", immediateEffect=" + immediateEffect
					+ ", specialEffect=" + specialEffect + "]";
		}

	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPermanentEffect() {
		return permanentEffect;
	}

	public void setPermanentEffect(boolean permanentEffect) {
		this.permanentEffect = permanentEffect;
	}

	public boolean isImmediateEffect() {
		return immediateEffect;
	}

	public void setImmediateEffect(boolean immediateEffect) {
		this.immediateEffect = immediateEffect;
	}

	public boolean isSpecialEffect() {
		return specialEffect;
	}

	public void setSpecialEffect(boolean specialEffect) {
		this.specialEffect = specialEffect;
	}
	
}
