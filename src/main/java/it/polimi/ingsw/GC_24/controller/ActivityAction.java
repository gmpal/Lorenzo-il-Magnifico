package it.polimi.ingsw.GC_24.controller;

import it.polimi.ingsw.GC_24.model.Model;
/**This class represents the particular action referred to the Activity --> Harvest&Production
 * It avoids duplicating code insite the two subclasses, because the verify method is the same*/
public abstract class ActivityAction extends Action{

	public ActivityAction(Model game, String familiar, String zone, String floor, String servants) {
		super(game, familiar, zone, floor, servants);
	}

	@Override
	public String verify() {
		String answerToPlayer = "Answer: \n";

		answerToPlayer = verifyIfEnoughServants(answerToPlayer);
		answerToPlayer = verifyIfEnoughServantsForThisPlace(answerToPlayer);
		answerToPlayer = verifyFamilyMemberAvailability(answerToPlayer);
		answerToPlayer = verifyPlaceAvailability(answerToPlayer);
		answerToPlayer = verifyZoneOccupiedByMe(answerToPlayer);

		if (answerToPlayer.equals("Answer: \n"))
			return "ok";
		else
			return answerToPlayer;
	}
}
