package it.polimi.ingsw.GC_24.model.effects.immediate;

import java.util.HashMap;
import java.util.List;

import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.model.personalboard.PersonalCards;
import it.polimi.ingsw.GC_24.model.values.*;

public class MoltiplicationCards extends Moltiplication {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4807445464341088218L;

	private PersonalCards personalCards;

	// constructor
	public MoltiplicationCards(String name, Value value, PersonalCards personalCards) {
		super(name, value);
		this.personalCards = personalCards;
	}

	@Override
	public void moltiplicationEffect(Player player) {
		System.out.println("Values before:" +player.getMyValues());
		int valueQuantity = this.getValue().getQuantity();
		PersonalCards correspondingArrayList = this.personalCards.findCardsInPersonalBoard(player.getMyBoard());
		int sizeOfArray = correspondingArrayList.getCards().size();
		int newQuantity = (valueQuantity) * (sizeOfArray);
		getValue().setQuantity(newQuantity);
		SetOfValues setOfValues = player.getMyValues();
		getValue().addValueToSet(setOfValues);
		System.out.println("Values after:" +player.getMyValues());
	}

	@Override
	public void giveImmediateEffect(Player player) {
		System.out.println("Giving moltiplication effect");
		moltiplicationEffect(player);
		System.out.println("Moltiplication effect given");
	}

	@Override
	public String toString() {
		return "Moltiplication Cards: for your every " + personalCards.getType() + " card you will receive "
				+ getValue().toString();
	}

	/* Not necessary methods, but better than choosing with if... */
	@Override
	public String generateParametersRequest() {
		return null;
	}

	@Override
	public HashMap<String, Object> generateHashMapToSend(String response) {
		return null;
	}

	@Override
	public void assignParameters(String responseFromClient) {

	}

	@Override
	public List<ImmediateEffect> addAllNewEffectsToThisSet(List<ImmediateEffect> secondaryInteractiveEffects) {
		return secondaryInteractiveEffects;
	}

	// getters and setters
	public PersonalCards getPersonalCards() {
		return personalCards;
	}

	public void setPersonalCards(PersonalCards personalCards) {
		this.personalCards = personalCards;
	}
}
