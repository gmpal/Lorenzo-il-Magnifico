package it.polimi.ingsw.GC_24.values;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

	public SetOfValues convertToValue() {
		BufferedReader br;
		Gson gson = GsonBuilders.getGsonWithTypeAdapters();
		String line = null;
		try {
			br = new BufferedReader(
					new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/convertFaithPoints.json"));

			for (int i = 0; i < this.quantity; i++) {
				line = GsonBuilders.getLine(br);
			}
		} catch (IOException e) {
			System.out.println("There is a problem with the configuration file");
		}
		return gson.fromJson(line, SetOfValues.class);
	}
}
