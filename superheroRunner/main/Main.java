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
import battlesystem.EntityInfoItem;
import battlesystem.RandomModifier;
import battlesystem.Shield;
import battlesystem.State;
import battlesystem.abilityImpls.AbilityLoader;
import battlesystem.effectImpls.EffectLoader;
import battlesystem.infoItemImpls.InfoItemReader;
import battlesystem.objectMapImpls.AbilityStorage;
import battlesystem.objectMapImpls.Effects;
import battlesystem.objectMapImpls.Heroes;
import battlesystem.objectMapImpls.Shields;
import battlesystem.objectMapImpls.Spawnables;
import battlesystem.objectMapImpls.States;
import battlesystem.shieldImpls.ShieldLoader;
import battlesystem.stateImpls.StateLoader;
import game.InnerGame;


//should we make like a "main menu" type of thing so that
//it would be easier to do stuff like change the file or 
//create new heros and/or abilities and choose to save and load...

class Main {
  public static void main(String[] args) throws FileNotFoundException {
	  File f = new File("res/shields.json");
	  try {
		f.createNewFile();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
//	  new RandomModifier(20);
	  OutputStream o = new FileOutputStream(f);
//	  String file = Heroes.loadHeroes().toString();
//	  String file = AbilityStorage.loadAbilities().toString();
	  String file = Shields.loadShields().toString();
	  System.out.println(file);
	  try {
		o.write(file.getBytes());
		System.out.println("written" + f.getAbsolutePath());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  HashMap<String, Ability> abilities = AbilityLoader.parseJSONFile("res/abilities.json");
//	  System.out.println(abilities.get("summon squirrel"));
	  HashMap<String, Effect> effects = EffectLoader.parseJSONFile("res/effects.json");
	  HashMap<String, State> states = StateLoader.parseJSONFile("res/states.json");
	  HashMap<String, EntityInfoItem> spawnables = InfoItemReader.parseJSONFile("res/entities.json");
	  HashMap<String, EntityInfoItem> heroes = InfoItemReader.parseJSONFile("res/heroes.json");
	  HashMap<String, Shield> shields = ShieldLoader.parseJSONFile("res/shields.json");
	  
	  Iterator<String> heroNames = heroes.keySet().iterator();
	  ArrayList<EntityInfoItem> initialHeroes = new ArrayList<>();
	  while (heroNames.hasNext())
	  {
		  initialHeroes.add(heroes.get(heroNames.next()));
	  }
//	  System.out.println(states);
	  new InnerGame(initialHeroes, abilities, effects, spawnables, shields, states, null).startFight();
//	     new OuterGame();
  }
}