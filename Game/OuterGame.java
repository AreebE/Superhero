package battlesystem;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//The outer game is going to be in charge 
//of things like saving loading creating and editing  
//abilities and heros and stuff like that 
// while innergame is for the actual gameplay like fighting and stuff

public class OuterGame {

  ArrayList<Entity> superheros;

  ScannerInput system;

  public OuterGame() {
    GUI g = new GUI();
    JsonIoThing j = new JsonIoThing("FileParsing/save.json");
    Entity A = new Entity("A", 10, 100, 50, null);
    Abilities.giveAbility(A, Abilities.Name.FIREBALL, Abilities.Name.SUMMON_SQUIRREL, Abilities.Name.RAM_ATTACK);

    Entity B = new Entity("B", 30, 100, 50, null);
    Abilities.giveAbility(B, Abilities.Name.SNOWBALL, Abilities.Name.SUMMON_GOLEM, Abilities.Name.GROUND_SUCTION);

    superheros = new ArrayList<>();
    /*
    superheros.add(Heroes.getHero(Heroes.Name.BEEP_BOOP, null));
    superheros.add(Heroes.getHero(Heroes.Name.JOE, null));
    superheros.add(Heroes.getHero(Heroes.Name.EEEEEE, null));
    superheros.add(Heroes.getHero(Heroes.Name.TEST_SUBJECT, null));
    */
    superheros.add(A);
    superheros.add(B);
    
    j.saveSuperheroArr(superheros);

    //load still needs to parse its abilities
    ArrayList<Entity> e = j.loadSuperheroArr();
    InnerGame iG = new InnerGame(superheros, g);
    iG.playGame();
    System.out.println("note turns arent implemented yet \n Nor is loading an entitys abilities from file");
  }

  private Entity getEntity(String name, ArrayList<Entity> superheros) {
    for (int i = 0; i < superheros.size(); i++) {
      if (superheros.get(i).getName().equals(name)) {
        return superheros.get(i);
      }
    }
    return null;
  }
}