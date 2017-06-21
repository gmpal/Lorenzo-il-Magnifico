package it.polimi.ingsw.GC_24.devCardJsonFile;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.GC_24.devCardJsonFile.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_24.effects.*;
import it.polimi.ingsw.GC_24.personalboard.*;
import it.polimi.ingsw.GC_24.values.*;

public class GsonBuilders {
	public GsonBuilders(){
		getGsonWithTypeAdapters();
	}
	
	private static GsonBuilder builder = new GsonBuilder();

	public static RuntimeTypeAdapterFactory<Value> getValueTypeAdapter() {
		return RuntimeTypeAdapterFactory.of(Value.class, "valueType").registerSubtype(Coin.class, "coin")
				.registerSubtype(Servant.class, "servant").registerSubtype(Stone.class, "stone")
				.registerSubtype(Wood.class, "wood").registerSubtype(MilitaryPoint.class, "militaryPoint")
				.registerSubtype(FaithPoint.class, "faithPoint").registerSubtype(VictoryPoint.class, "victoryPoint");
	}

	public static RuntimeTypeAdapterFactory<ImmediateEffect> getImmediateEffectTypeAdapter() {
		return RuntimeTypeAdapterFactory.of(ImmediateEffect.class, "immediateEffectType")
				.registerSubtype(MoltiplicationPoints.class, "moltiplicationPoints")
				.registerSubtype(MoltiplicationCards.class, "moltiplicationCards")
				.registerSubtype(ValueEffect.class, "value").registerSubtype(CouncilPrivilege.class, "coucilPrivilege")
				.registerSubtype(ChooseNewCard.class, "chooseNewCard").registerSubtype(Exchange.class, "exchange")
				.registerSubtype(PerformHarvest.class, "performHarvest")
				.registerSubtype(PerformProduction.class, "performProduction");
	}

	public static RuntimeTypeAdapterFactory<PersonalCards> getPersonalCardTypeAdapter() {
		return RuntimeTypeAdapterFactory.of(PersonalCards.class, "personalCardsType")
				.registerSubtype(PersonalTerritories.class, "personalTerritories")
				.registerSubtype(PersonalBuildings.class, "personalBuildings")
				.registerSubtype(PersonalCharacters.class, "personalCharacters")
				.registerSubtype(PersonalVentures.class, "personalVentures");
	}

	public static Gson getGsonWithTypeAdapters() {
		builder.registerTypeAdapterFactory(getValueTypeAdapter());
		builder.registerTypeAdapterFactory(getImmediateEffectTypeAdapter());
		builder.registerTypeAdapterFactory(getPersonalCardTypeAdapter());
		builder.serializeNulls();
		return builder.create();
	}
	
	public static String getLine(BufferedReader br) throws IOException {
		String line;
		line = br.readLine();
		return line;
	}

}
