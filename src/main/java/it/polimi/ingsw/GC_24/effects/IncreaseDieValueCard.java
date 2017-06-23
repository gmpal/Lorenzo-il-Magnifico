package it.polimi.ingsw.GC_24.effects;

import it.polimi.ingsw.GC_24.personalboard.PersonalCards;
import it.polimi.ingsw.GC_24.values.SetOfValues;

public class IncreaseDieValueCard extends IncreaseDieValueActivity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2391647693462341995L;
	private PersonalCards personalCards;
	private SetOfValues sale;
	private SetOfValues alternativeSale;

	// constructor
	public IncreaseDieValueCard(String name, PersonalCards personalCards, int increaseDieValue, SetOfValues sale,
			SetOfValues alternativeSale) {
		super(name, increaseDieValue);
		this.personalCards = personalCards;
		this.sale = sale;
		this.alternativeSale = alternativeSale;
	}

	// getters and setters
	public PersonalCards getPersonalCards() {
		return personalCards;
	}

	public void setPersonalCards(PersonalCards personalCards) {
		this.personalCards = personalCards;
	}

	public SetOfValues getSale() {
		return sale;
	}

	public void setSale(SetOfValues sale) {
		this.sale = sale;
	}

	public SetOfValues getAlternativeSale() {
		return alternativeSale;
	}

	public void setAlternativeSale(SetOfValues alternativeSale) {
		this.alternativeSale = alternativeSale;
	}

}
