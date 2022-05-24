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

import battlesystem.Ability;
import battlesystem.Effect;
import battlesystem.Encounter;
import battlesystem.EntityInfoItem;
import battlesystem.RandomModifier;
import battlesystem.Shield;
import battlesystem.State;
import battlesystem.Storage;
import battlesystem.abilityImpls.AbilityLoader;
import battlesystem.effectImpls.EffectLoader;
import battlesystem.encounterImpls.EncounterLoader;
import battlesystem.infoItemImpls.InfoItemReader;
import battlesystem.objectMapImpls.AbilityStorage;
import battlesystem.objectMapImpls.Effects;
import battlesystem.objectMapImpls.Encounters;
import battlesystem.objectMapImpls.Heroes;
import battlesystem.objectMapImpls.Shields;
import battlesystem.objectMapImpls.Spawnables;
import battlesystem.objectMapImpls.States;
import battlesystem.shieldImpls.ShieldLoader;
import battlesystem.stateImpls.StateLoader;
import game.InnerGame;
import game.OuterGame;


//should we make like a "main menu" type of thing so that
//it would be easier to do stuff like change the file or 
//create new heros and/or abilities and choose to save and load...

class Main {
  public static void main(String[] args) throws FileNotFoundException {
	  File f = new File("res/entities.json");
//	  File f = new File("res/abilities.json");
//	  File f = new File("res/effects.json");
//	  File f = new File("res/shields.json");
//	  File f = new File("res/encounters.json");
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
	  String fileContents = Spawnables.loadSpawnables().toString();
//	  String fileContents = Effects.loadEffects().toString();
//	  String fileContents = Encounters.saveEncounters().toString();
//	  System.out.println(file);
	  try {
		o.write(fileContents.getBytes());
		System.out.println("written" + f.getAbsolutePath());
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
	 String[] fileSources = new String[7];
	 fileSources[Storage.ABILITIES] = "res/abilities.json";
	 fileSources[Storage.EFFECTS] = "res/effects.json";
	 fileSources[Storage.SHIELDS] = "res/shields.json";
	 fileSources[Storage.SPAWNABLES] = "res/entities.json";
	 fileSources[Storage.STATES] = "res/states.json";
	 fileSources[Storage.ENTITIES] = "res/heroes.json";
	 fileSources[Storage.ENCOUNTERS] = "res/encounters.json";
	  new OuterGame(fileSources);
  }
}