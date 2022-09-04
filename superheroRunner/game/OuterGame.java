package game;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import gameSystem.Campaign;
import gameSystem.Encounter;
import gameSystem.Entity;
import gameSystem.EntityInfoItem;
import gameSystem.Storage;
import gameSystem.battlelogImpls.StringBattleLog;
import gameSystem.objectMapImpls.AbilityManager;
import gameSystem.objectMapImpls.States;

public class OuterGame {

  private ArrayList<EntityInfoItem> superheros = new ArrayList<>();
  private GUI g = new GUI();
//  ScannerInput system;
  private AbilityManager abilityMan = new AbilityManager();
  private Scanner sc = new Scanner(System.in);
    private Storage storage;


  
  public OuterGame(String[] files) {
   this.superheros = new ArrayList<>();
    String[] items = new String[]{"EEEEEE", "Joe", "BeepBoop", "TestSubject", "SecondestSubject"};
      try 
    {
        storage = new Storage(files);
        storage.verifyAllItems();
//        storage.saveAllToFiles();
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

        case "pe":
        case "play encounter":
        // System.out.println("Playing Game!");
             System.out.println("What encounter will you choose?");
            String encounterName = sc.nextLine();
            Encounter e = storage.getEncounter(encounterName);
            System.out.println("Just to be safe, can you choose a protagonist?");
            String protagName = sc.nextLine();
            EntityInfoItem protag = storage.getEntity(protagName);
            if (e == null || protag == null)
            {
                break;
            }
            InnerGame.ScannerInput system = new InnerGame.ScannerInput(new Scanner(System.in));
            InnerGame game = new InnerGame(e, storage, new GUI(), protag);
            game.startFight(protag.getName());
       
        //going to add exploration here soon
//        iG.Fight(superheros);
        break;
        case "pc":
        case "play campaign":
            System.out.println("What campaign will you choose?");
            String campaignName = sc.nextLine();
            Campaign c = storage.getCampaign(campaignName);
            if (c == null)
            {
                break;
            }
            system = new InnerGame.ScannerInput(new Scanner(System.in));
            c.setInput(system);
            c.setOutput(system);
            c.beginCampaign(storage, new StringBattleLog());
              break;
        case "ss":
        case "save superheros":
        storage.saveAllToFiles();
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

        case "rc":
          case "run campaign":

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