package it.polimi.ingsw.GC_24.controller;

/** This is a factory thats only job is creating actions
 *	By encapsulating actions creation, we only have one
 * 	place to make modifications.
 *
 */ 
public class ActionFactory {

		
		// This could be used as a static method if we
		// are willing to give up subclassing it
		
		public Action makeAction(String newActionType){
			
			Action newAction = null;
			
			if (newActionType.equals("Tower")){
				
				newAction = new ActionTower();
			
			} 
			
			if (newActionType.equals("Harvest") || newActionType.equals("Production")){
				
				newAction = new HarvestProductionAction();
				
			} 
			
			if (newActionType.equals("Council")){
				
				newAction = new CouncilPalaceAction();
				
			} 
			if (newActionType.equals("Market")){
				
				newAction = new MarketAction();
				
			} 
			
			return newAction;
			
		}
		
	}
	
}
