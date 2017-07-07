package it.polimi.ingsw.GC_24.devCardJsonFile;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.polimi.ingsw.GC_24.devCardJsonFile.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.GC_24.model.effects.*;
import it.polimi.ingsw.GC_24.model.effects.immediate.ChooseNewCard;
import it.polimi.ingsw.GC_24.model.effects.immediate.CouncilPrivilege;
import it.polimi.ingsw.GC_24.model.effects.immediate.Exchange;
import it.polimi.ingsw.GC_24.model.effects.immediate.ImmediateEffect;
import it.polimi.ingsw.GC_24.model.effects.immediate.MoltiplicationCards;
import it.polimi.ingsw.GC_24.model.effects.immediate.MoltiplicationPoints;
import it.polimi.ingsw.GC_24.model.effects.immediate.PerformHarvest;
import it.polimi.ingsw.GC_24.model.effects.immediate.PerformProduction;
import it.polimi.ingsw.GC_24.model.effects.immediate.ValueEffect;
import it.polimi.ingsw.GC_24.model.effects.permanent.ChangeServantsValue;
import it.polimi.ingsw.GC_24.model.effects.permanent.CustomizedPermanentEffect;
import it.polimi.ingsw.GC_24.model.effects.permanent.IncreaseDieValueActivity;
import it.polimi.ingsw.GC_24.model.effects.permanent.IncreaseDieValueCard;
import it.polimi.ingsw.GC_24.model.effects.permanent.NoMarketAvailability;
import it.polimi.ingsw.GC_24.model.effects.permanent.NoValueEffectFromTowerPlace;
import it.polimi.ingsw.GC_24.model.effects.permanent.NoVictoryPointsFromCard;
import it.polimi.ingsw.GC_24.model.effects.permanent.PermanentEffect;
import it.polimi.ingsw.GC_24.model.effects.permanent.SubSetOfValues;
import it.polimi.ingsw.GC_24.model.effects.permanent.SubVicrotyPointsFromSetOfValue;
import it.polimi.ingsw.GC_24.model.personalboard.*;
import it.polimi.ingsw.GC_24.model.values.*;

/**
 * This class set the serialization and the deserialization of abstract class.
 * The subclasses are registered with "RuntimeTypeAdapterFactory"
 */
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
				.registerSubtype(NoMarketAvailability.class, "noMarketAvailability")
				.registerSubtype(CustomizedPermanentEffect.class, "customizedPermanentEffect")
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
