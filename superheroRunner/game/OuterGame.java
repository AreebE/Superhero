package game;

import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



import battlesystem.Ability;
import battlesystem.Effect;
import battlesystem.Entity;
import battlesystem.EntityInfoItem;
import battlesystem.Shield;
import battlesystem.State;
import battlesystem.abilityImpls.AbilityLoader;
import battlesystem.effectImpls.EffectLoader;
import battlesystem.infoItemImpls.InfoItemReader;
import battlesystem.objectMapImpls.*;
import battlesystem.shieldImpls.ShieldLoader;
import battlesystem.stateImpls.StateLoader;
import loaders.CustomMaker;
import loaders.JsonIoThing;
//The outer game is going to be in charge 
//of things like saving loading creating and editing  
//abilities and heros and stuff like that 
// while innergame is for the actual gameplay like fighting and stuff

public class OuterGame {

  private ArrayList<EntityInfoItem> superheros = new ArrayList<>();
  private GUI g = new GUI();
//  ScannerInput system;
  private AbilityManager abilityMan = new AbilityManager();
  private Scanner sc = new Scanner(System.in);
  private HashMap<String, Ability> abilities;
  private HashMap<String, Effect> effects;
  private HashMap<String, Shield> shields;
  private HashMap<String, EntityInfoItem> spawnables;
  private HashMap<String, State> states;
  
  public static final int ABILITIES_INDEX = 0;
  public static final int EFFECTS_INDEX = 1;
  public static final int SHIELDS_INDEX = 2;
  public static final int SPAWNABLES_INDEX = 3;
  public static final int STATES_INDEX = 4;
  public static final int HEROES_INDEX = 5;
  
  public OuterGame(String[] files) {
//    this.superheros = JsonIoThing.loadSuperheroArr("FileParsing/save.json");
    try 
    {
        this.abilities = AbilityLoader.parseJSONFile(files[ABILITIES_INDEX]);
        this.effects = EffectLoader.parseJSONFile(files[EFFECTS_INDEX]);
        this.shields = ShieldLoader.parseJSONFile(files[SHIELDS_INDEX]);
        this.spawnables = InfoItemReader.parseJSONFile(files[SPAWNABLES_INDEX]);
        this.states = StateLoader.parseJSONFile(files[STATES_INDEX]);
        HashMap<String, EntityInfoItem> heroes = InfoItemReader.parseJSONFile(files[HEROES_INDEX]);  	  
  	  	Iterator<String> heroNames = heroes.keySet().iterator();
  	  	this.superheros = new ArrayList<>();
	  	  while (heroNames.hasNext())
	  	  {
	  		  superheros.add(heroes.get(heroNames.next()));
	  	  }
    }
    catch(FileNotFoundException fnfe)
    {
    	System.out.println(fnfe.toString());
    }
    
    mainMenu();
    System.out.println("END OF GAME");
  }

  private void mainMenu(){
    System.out.println("welcommen to superheros! \n what would you like to do? (type help for CMDs)");
    
    String input = sc.nextLine().toLowerCase();
    
    while(!input.equals("Exit")){
      if(!Command.isItInHere(input)){
        System.out.println("HEY THATS NOT A VALID COMMAND");
      }

      switch(input){
        
        case "help":
         Command.onHelp();
        break;

        case "p":
        case "play":
        System.out.println("Playing Game!");
        InnerGame iG = new InnerGame(superheros, abilities, effects, spawnables, shields, states, g);
        iG.startFight();
        //going to add exploration here soon
//        iG.Fight(superheros);
        break;

        case "ss":
        case "save superheros":
//        JsonIoThing.saveSuperheroArr(this.superheros,"FileParsing/save.json");
        break;

        case "ls":
        case "load superheros":
//        this.superheros = JsonIoThing.loadSuperheroArr("FileParsing/save.json");
        break;

        case "cs":
        case "create superhero":
//        superheros.add(CustomMaker.askNMakeSuperhero());
        break;

        case "e":
        case "exit":
        System.out.println("Exiting game! thanks for playing");
        System.exit(69);
        break;

        case "ps":
        case "print all superhero names":
//        for(Entity t:superheros){
//          System.out.println(t.getName());
//        }
        break;

        case "ah":
        case "add abilities to hero":
        askAndAddAbilityToHero();
        break;

        case "pa":
        case "print absolute abilities names":
        abilityMan.printAllNames();
        break;
        
      }
      System.out.println("\n what next?");
      input = sc.nextLine();
      input = input.toLowerCase();
    }

  }
  private void askAndAddAbilityToHero(){
//    System.out.println("here are the current superheros: ");
//    for(Entity t:superheros){
//      System.out.println(t.getName());
//    }
//    Entity temp=null;
//    while(temp==null){
//      System.out.print("Enter the name of the superhero you want to add abilities to: \n");
//      String name = sc.nextLine();
//      temp = getEntity(name, superheros);
//    }    
//    System.out.print("Enter the name of the Abilities you want to add: ");
//    ArrayList<String> names = new ArrayList<String>();
//    String input = sc.nextLine();
//    while (!input.equals("finished")){
//      Ability temporary = abilityMan.getAbility(input);
//      if(temporary !=null){
//        System.out.println("Ability found!, you can input more abilities or type finished to add them to the hero");
//        names.add(temporary.getName());
//      }else{
//        System.out.println("hey thats not a valid ability, try again: ");
//      }
//      input = sc.nextLine();
//      input = input.toLowerCase();
//    }
//    abilityMan.giveAbilities(temp,names);
  }


  private Entity getEntity(String name, ArrayList<Entity> superheros) {
    for (int i = 0; i < superheros.size(); i++) {
      if (superheros.get(i).getName().equals(name)) {
        return superheros.get(i);
      }
    }
    return null;
  }
  
  private void makeFixedEntities(){
    String[] tempabs =new String[5];
    //tempabs = {"snowball","summon golem","ground suction"};
    Entity A = new Entity("A", 10, 100, 50, States.get(States.Name.NORMAL), null);
    //m.giveAbilities(A,tempabs);

    Entity B = new Entity("B", 30, 100, 50, States.get(States.Name.NORMAL), null);
    
    //tempabs = {"fireball","summon squirrel"};

    //m.giveAbilities(B,tempabs);
//    superheros.add(A);
//    superheros.add(B);
    //superheros.add(Heroes.get(Heroes.Name.BEEP_BOOP, null));
    //superheros.add(Heroes.get(Heroes.Name.JOE, null));
  }
  public AbilityManager getAbManager(){
    return this.abilityMan;
  }
}