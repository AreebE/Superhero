
import java.util.ArrayList;
import java.util.Collections;
import game.OuterGame;
import battlesystem.Storage;


//should we make like a "main menu" type of thing so that
//it would be easier to do stuff like change the file or 
//create new heros and/or abilities and choose to save and load...


class Main {
  public static void main(String[] args) {
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