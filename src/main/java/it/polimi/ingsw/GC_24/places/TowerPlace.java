package it.polimi.ingsw.GC_24.places;

import it.polimi.ingsw.GC_24.cards.Development;
import it.polimi.ingsw.GC_24.effects.CouncilPrivilege;
import it.polimi.ingsw.GC_24.effects.ImmediateEffect;
import it.polimi.ingsw.GC_24.effects.ValueEffect;
import it.polimi.ingsw.GC_24.model.Player;
import it.polimi.ingsw.GC_24.values.Value;

public class TowerPlace extends Place {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2679672850968909758L;
	
	private Development correspondingCard;
	private ValueEffect value;
	private ImmediateEffect privilege;
	
	
	//constructor
	public TowerPlace(int costDice, ValueEffect value, ImmediateEffect privilege){
		super(costDice);
		this.value=value;
		this.privilege=privilege;
		this.correspondingCard=null;
	}
	
	//method to take a card without placing a family member
	public void takeWithoutPlacing(Player player){
		this.correspondingCard.setCardOnPersonalBoard(player.getMyBoard());
		this.correspondingCard.getImmediateEffect().giveImmediateEffect(player);
	}
	
	@Override
	public void giveEffects(Player player){
		correspondingCard.setCardOnPersonalBoard(player.getMyBoard());
		correspondingCard.getImmediateEffect();
		correspondingCard.getImmediateEffect1();
		this.correspondingCard = null;
	}

	@Override
	public void clearPlace() {
		if (this.famMemberOnPlace != null){
		this.famMemberOnPlace.setAvailable(true);
		}
		this.famMemberOnPlace = null;
		this.setAvailable(true);
		
	
		this.correspondingCard = null;
	}
	
	@Override
	public String toString() {
		return "Card = " + correspondingCard;
	}

	public Development getCorrespondingCard() {
		return correspondingCard;
	}

	public void setCorrespondingCard(Development correspondingCard) {
		this.correspondingCard = correspondingCard;
	}

	@Override
	public ValueEffect getValue() {
		return value;
	}

	public ImmediateEffect getPrivilege() {
		return privilege;
	}

	public void setPrivilege(ImmediateEffect privilege) {
		this.privilege = privilege;
	}

	public void setValue(ValueEffect value) {
		this.value = value;
	}
	
	
	
	

}
