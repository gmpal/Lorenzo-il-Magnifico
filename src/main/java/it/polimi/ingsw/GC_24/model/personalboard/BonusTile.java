package it.polimi.ingsw.GC_24.model.personalboard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;

import it.polimi.ingsw.GC_24.devCardJsonFile.GsonBuilders;
import it.polimi.ingsw.GC_24.model.values.SetOfValues;

/**
 * This class represent the bonus tile that have the resources given to players
 * when they do harvest or production before to give the effect of territory
 * cards or building cards, it is taken from the configuration file.
 */
public class BonusTile implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7330731129325631314L;
	private SetOfValues harvestValues;
	private SetOfValues productionValues;

	// constructor
	public BonusTile(boolean advancedRules, int index) {
		try {
			createBonusTile(advancedRules, index);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method create a bonus tile from a file named "bonusTiles". The attribute
	 * advanceRules is false if you want play without advanced rules, it is true
	 * otherwise. The attribute index is the player's number, it is useful to give a
	 * different Bonus Tile to players. If are not selected the advanced rules all
	 * player receive the same Bonus Tile. The file contains 5 pair of lines, one
	 * for the setOfValues given when you do harvest, one for the setOfValues given
	 * when you do production, the first pair is used when the players play with the
	 * simple rules, the other triplets when the players play with the advanced
	 * rules.
	 * 
	 * @throws IOException
	 */
	private void createBonusTile(boolean advancedRules, int index) throws IOException {
		BufferedReader br;
		Gson gson = GsonBuilders.getGsonWithTypeAdapters();
		String line;
		br = new BufferedReader(new FileReader("src/main/java/it/polimi/ingsw/GC_24/devCardJsonFile/bonusTiles.json"));
		if (advancedRules) {
			for (int i = 0; i < 2 + (index - 1) * 2; i++) {
				line = GsonBuilders.getLine(br);
			}
		}
		line = GsonBuilders.getLine(br);
		harvestValues = gson.fromJson(line, SetOfValues.class);
		line = GsonBuilders.getLine(br);
		productionValues = gson.fromJson(line, SetOfValues.class);

	}

	/** adds the harvestValues to the parameter */
	public void giveHarvestValues(SetOfValues v) {
		harvestValues.addTwoSetsOfValues(v);
	}

	/** adds the productionValues to the parameter */
	public void giveProductionValues(SetOfValues v) {
		productionValues.addTwoSetsOfValues(v);
	}

	@Override
	public String toString() {
		return "HarvestValues = " + harvestValues + ", ProductionValues = " + productionValues;
	}

	// getters e setters
	public SetOfValues getHarvestValues() {
		return harvestValues;
	}

	public void setHarvestValues(SetOfValues harvestValues) {
		this.harvestValues = harvestValues;
	}

	public SetOfValues getProductionValues() {
		return productionValues;
	}

	public void setProductionValues(SetOfValues productionValues) {
		this.productionValues = productionValues;
	}

}
