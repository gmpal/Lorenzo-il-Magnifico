package it.polimi.ingsw.GC_24.values;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
	private List<SetOfValues> correspondingValue = new ArrayList<>();

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
	public SetOfValues convertToValue() {
		return correspondingValue.get(this.quantity - 1);
	}

	/**
	 * This method converts Faith Points in Values specified in a configuration
	 * file named "convertFaithPoints.json". Every line of file corresponds to a
	 * score. All Values are entered in a List of SetOfValues
	 */
	public void getCorrespondingValue() {
		BufferedReader br;
		Gson gson = GsonBuilders.getGsonWithTypeAdapters();
		String line = "ready";
		try {
			br = new BufferedReader(
					new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/convertFaithPoints.json"));

			while (line != null) {
				line = GsonBuilders.getLine(br);
				correspondingValue.add(gson.fromJson(line, SetOfValues.class));
			}
		} catch (IOException e) {
			System.out.println("There is a problem with the configuration file");
		}
	}
}
