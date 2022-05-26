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
import battlesystem.Storage;
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
    private Storage storage;
    private HashMap<String, Ability> abilities;
  private HashMap<String, Effect> effects;
  private HashMap<String, Shield> shields;
  private HashMap<String, EntityInfoItem> spawnables;
  private HashMap<String, State> states;

  
  public OuterGame(String[] files) {
   this.superheros = new ArrayList<>();
    String[] items = new String[]{"EEEEEE", "Joe", "BeepBoop", "TestSubject", "SecondestSubject"};
      try 
    {
        storage = new Storage(files);
        storage.verifyAllItems();
        for (int i = 0; i < items.length; i++)
        {
        	superheros.add(storage.getEntity(items[i]));
//        	System.out.println(storage.getEntity(items[i]));
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
        String testName = "Joe";
        InnerGame iG = new InnerGame(storage.getEncounter("beta"), storage, g, testName);
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