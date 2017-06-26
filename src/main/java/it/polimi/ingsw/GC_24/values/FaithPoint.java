package it.polimi.ingsw.GC_24.values;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.model.Player;

public class FaithPoint extends Value {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3781295737830022673L;

	// constructor
	public FaithPoint(int value) {
		super(value);
	}

	@Override
	public SetOfValues addValueToSet(SetOfValues values) {
		values.getFaithPoints().setQuantity(values.getFaithPoints().getQuantity() + this.getQuantity());
		return values;
	}

	@Override
	public SetOfValues subValuefromSet(SetOfValues values) {
		values.getFaithPoints().setQuantity(values.getFaithPoints().getQuantity() - this.getQuantity());
		return values;
	}

	@Override
	public Value findValueInPlayer(Player player) {
		return player.getMyValues().getFaithPoints();
	}

	@Override
	public String whatValueAmI() {
		return "FaithPoint";
	}

	@Override
	public Boolean amIpresentInThisSet(SetOfValues setOfValues) {
		return (setOfValues.getFaithPoints().getQuantity() >= this.quantity);
	}

	/**
	 * This method return the corresponding Values of Faith Points
	 * 
	 * @return SetOfValues
	 */
	public SetOfValues convertToValue(List<SetOfValues> correspondingValue) {
		return correspondingValue.get(this.quantity - 1);
	}
	
	@Override
	public String toString() {
		return "faithPoints = " + getQuantity();
	}
}
