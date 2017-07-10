package it.polimi.ingsw.GC_24.model.effects.permanent;

import it.polimi.ingsw.GC_24.model.personalboard.PersonalCards;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

/**
 * with this effect the player will have a fixed starting die value when
 * placing a family member on a certain tower
 */
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if ( personalCards != null) {
		builder.append( "Increase Die Value: from now on you will have a starting die value of " +getIncreaseDieValue()+
						" when taking a " + personalCards.getType() + " card");
		} else {
			builder.append( "Increase Die Value: from now on you will have a starting die value of " +getIncreaseDieValue()+
					" when taking a card");
		}
		if (sale != null) {
			builder.append(" and you will also have an extra discount on the price of the card of " + sale);
			if (alternativeSale != null)
				builder.append(" or of " + alternativeSale);
		}
		return builder.toString();
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
