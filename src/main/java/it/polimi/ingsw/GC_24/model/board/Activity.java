package it.polimi.ingsw.GC_24.model.board;

import it.polimi.ingsw.GC_24.model.places.Place;

public abstract class Activity extends Area{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5456041403053748002L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		for (Place p : placesArray) {
			if (!p.isAvailable()) {
				builder.append("[Place occupied by the " + p.getFamMemberOnPlace().getPlayerColour() + " player]");
			} else {
				if (p.getValue() != null)
					builder.append(
							"[Place Available] --> You will have to pay an extra die's cost of: " + p.getValue());
				else
					builder.append("[Place Available]");
				return builder.toString();
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
