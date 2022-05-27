
import java.util.ArrayList;
import java.util.Collections;
import battlesystem.objectMapImpls.Spawnables;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import game.OuterGame;
import battlesystem.Storage;
import battlesystem.objectMapImpls.*;


//should we make like a "main menu" type of thing so that
//it would be easier to do stuff like change the file or 
//create new heros and/or abilities and choose to save and load...


class Main {
  public static void main(String[] args) 
    throws FileNotFoundException{
      	 String[] fileSources = new String[7];
	 	  File f = new File("res/entities.json");
//	  File f = new File("res/abilities.json");
//	  File f = new File("res/effects.json");
//	  File f = new File("res/shields.json");
	  // File f = new File("res/encounters.json");
        
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
	  // String fileContents = Encounters.saveEncounters().toString();
//	  System.out.println(file);
	  try {
		o.write(fileContents.getBytes());
		System.out.println("written" + f.getAbsolutePath());
	} catch (IOException e) {
//		 TODO Auto-generated catch block
		e.printStackTrace();
	}
      
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