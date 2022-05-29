package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import game.InnerGame;
import game.OuterGame;
import gameSystem.Ability;
import gameSystem.Effect;
import gameSystem.Encounter;
import gameSystem.EntityInfoItem;
import gameSystem.RandomModifier;
import gameSystem.Shield;
import gameSystem.State;
import gameSystem.Storage;
import gameSystem.abilityImpls.AbilityLoader;
import gameSystem.effectImpls.EffectLoader;
import gameSystem.encounterImpls.EncounterLoader;
import gameSystem.infoItemImpls.InfoItemReader;
import gameSystem.objectMapImpls.AbilityStorage;
import gameSystem.objectMapImpls.Campaigns;
import gameSystem.objectMapImpls.Effects;
import gameSystem.objectMapImpls.Encounters;
import gameSystem.objectMapImpls.Events;
import gameSystem.objectMapImpls.Heroes;
import gameSystem.objectMapImpls.Shields;
import gameSystem.objectMapImpls.Spawnables;
import gameSystem.objectMapImpls.States;
import gameSystem.shieldImpls.ShieldLoader;
import gameSystem.stateImpls.StateLoader;


//should we make like a "main menu" type of thing so that
//it would be easier to do stuff like change the file or 
//create new heros and/or abilities and choose to save and load...

class Main {
  public static void main(String[] args) throws FileNotFoundException {
//	  File f = new File("res/entities.json");
//	  File f = new File("res/abilities.json");
//	  File f = new File("res/effects.json");
//	  File f = new File("res/shields.json");
//	  File f = new File("res/encounters.json");
	  File f = new File("res/events.json");
//	  File f = new File("res/campaigns.json");
//	  try {
//		f.createNewFile();
//	} catch (IOException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	  new RandomModifier(20);
	  OutputStream o = new FileOutputStream(f);
//	  String fileContents = Heroes.loadHeroes().toString();
//	  String fileContents = AbilityStorage.loadAbilities().toString();
//	  String fileContents = Shields.loadShields().toString();
//	  String fileContents = Spawnables.loadSpawnables().toString();
//	  String fileContents = Effects.loadEffects().toString();
//	  String fileContents = Encounters.saveEncounters().toString();
	  String fileContents = Events.saveEvents().toString();
//	  String fileContents = Campaigns.saveCampaigns().toString();

//	  System.out.println(file);
	  try {
		o.write(fileContents.getBytes());
//		System.out.println("written" + f.getAbsolutePath());
	} catch (IOException e) {
//		 TODO Auto-generated catch block
		e.printStackTrace();
	}
//	  HashMap<String, Ability> abilities = AbilityLoader.parseJSONFile("res/abilities.json");
////	  System.out.println(abilities.get("summon squirrel"));
//	  HashMap<String, Effect> effects = EffectLoader.parseJSONFile("res/effects.json");
//	  HashMap<String, State> states = StateLoader.parseJSONFile("res/states.json");
//	  HashMap<String, EntityInfoItem> spawnables = InfoItemReader.parseJSONFile("res/entities.json");
//	  HashMap<String, EntityInfoItem> heroes = InfoItemReader.parseJSONFile("res/heroes.json");
//	  HashMap<String, Shield> shields = ShieldLoader.parseJSONFile("res/shields.json");
//	HashMap<String, Encounter> encounters = EncounterLoader.parseJSONFile("res/encounters.json");  
//	  Iterator<String> heroNames = heroes.keySet().iterator();
//	  ArrayList<EntityInfoItem> initialHeroes = new ArrayList<>();
//	  while (heroNames.hasNext())
//	  {
//		  initialHeroes.add(heroes.get(heroNames.next()));
//	  }
//	  System.out.println(states);
//	  new InnerGame(initialHeroes, abilities, effects, spawnables, shields, states, null).startFight();
	 String[] fileSources = new String[9];
	 fileSources[Storage.ABILITIES] = "res/abilities.json";
	 fileSources[Storage.EFFECTS] = "res/effects.json";
	 fileSources[Storage.SHIELDS] = "res/shields.json";
	 fileSources[Storage.SPAWNABLES] = "res/entities.json";
	 fileSources[Storage.STATES] = "res/states.json";
	 fileSources[Storage.ENTITIES] = "res/heroes.json";
	 fileSources[Storage.ENCOUNTERS] = "res/encounters.json";
	 fileSources[Storage.EVENTS] = "res/events.json";
	 fileSources[Storage.CAMPAIGNS] = "res/campaigns.json";
	  new OuterGame(fileSources);
  }
}