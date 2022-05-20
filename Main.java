
import java.util.ArrayList;
import java.util.Collections;
import game.OuterGame;


//should we make like a "main menu" type of thing so that
//it would be easier to do stuff like change the file or 
//create new heros and/or abilities and choose to save and load...


class Main {
  public static void main(String[] args) {
      	 String[] fileSources = new String[6];
	 fileSources[OuterGame.ABILITIES_INDEX] = "res/abilities.json";
	 fileSources[OuterGame.EFFECTS_INDEX] = "res/effects.json";
	 fileSources[OuterGame.SHIELDS_INDEX] = "res/shields.json";
	 fileSources[OuterGame.SPAWNABLES_INDEX] = "res/entities.json";
	 fileSources[OuterGame.STATES_INDEX] = "res/states.json";
	 fileSources[OuterGame.HEROES_INDEX] = "res/heroes.json";
	  
	  new OuterGame(fileSources);
  }
}