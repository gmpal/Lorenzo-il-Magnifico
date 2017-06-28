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
	public GsonBuilders() {
		getGsonWithTypeAdapters();
	}

	private static GsonBuilder builder = new GsonBuilder();

	public static RuntimeTypeAdapterFactory<Value> getValueTypeAdapter() {
		return RuntimeTypeAdapterFactory.of(Value.class, "valueType").registerSubtype(Coin.class, "coin")
				.registerSubtype(Servant.class, "servant").registerSubtype(Stone.class, "stone")
				.registerSubtype(Wood.class, "wood").registerSubtype(MilitaryPoint.class, "militaryPoint")
				.registerSubtype(FaithPoint.class, "faithPoint").registerSubtype(VictoryPoint.class, "victoryPoint");
	}

	public static RuntimeTypeAdapterFactory<Effect> getEffectTypeAdapter() {
		return RuntimeTypeAdapterFactory.of(Effect.class, "EffectType")
				.registerSubtype(NoVictoryPointsFromCard.class, "noVictoryPointsFromCard")
				.registerSubtype(SubVicrotyPointsFromSetOfValue.class, "subVicrotyPointsFromSetOfValue");
	}

	public static RuntimeTypeAdapterFactory<ImmediateEffect> getImmediateEffectTypeAdapter() {
		return RuntimeTypeAdapterFactory.of(ImmediateEffect.class, "immediateEffectType")
				.registerSubtype(MoltiplicationPoints.class, "moltiplicationPoints")
				.registerSubtype(MoltiplicationCards.class, "moltiplicationCards")
				.registerSubtype(ValueEffect.class, "value").registerSubtype(CouncilPrivilege.class, "councilPrivilege")
				.registerSubtype(ChooseNewCard.class, "chooseNewCard").registerSubtype(Exchange.class, "exchange")
				.registerSubtype(PerformHarvest.class, "performHarvest")
				.registerSubtype(PerformProduction.class, "performProduction");
	}

	public static RuntimeTypeAdapterFactory<PermanentEffect> getPermanentEffectTypeAdapter() {
		return RuntimeTypeAdapterFactory.of(PermanentEffect.class, "permanentEffectType")
				.registerSubtype(IncreaseDieValueActivity.class, "increaseDieValueActivity")
				.registerSubtype(IncreaseDieValueCard.class, "increaseDieValueCard")
				.registerSubtype(NoValueEffectFromTowerPlace.class, "noValueEffectFromTowerPlace")
				.registerSubtype(ChangeServantsValue.class, "changeServantsValue")
				.registerSubtype(FirstPlacementAtTheEnd.class, "firstPlacementAtTheEnd")
				.registerSubtype(NoMarketAvailability.class, "noMarketAvailability")
				.registerSubtype(SubSetOfValues.class, "subSetOfValues");
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
		builder.registerTypeAdapterFactory(getEffectTypeAdapter());
		builder.registerTypeAdapterFactory(getImmediateEffectTypeAdapter());
		builder.registerTypeAdapterFactory(getPersonalCardTypeAdapter());
		builder.registerTypeAdapterFactory(getPermanentEffectTypeAdapter());
		builder.serializeNulls();
		return builder.create();
	}

	public static String getLine(BufferedReader br) throws IOException {
		String line;
		line = br.readLine();
		return line;
	}

}
