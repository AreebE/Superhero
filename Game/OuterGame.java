package battlesystem;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.google.gson.JsonIOException;

//The outer game is going to be in charge 
//of things like saving loading creating and editing  
//abilities and heros and stuff like that 
// while innergame is for the actual gameplay like fighting and stuff

public class OuterGame {

  ArrayList<Entity> superheros = new ArrayList<Entity>();
  GUI g = new GUI();
  ScannerInput system;
  AbilityManager m = getAbManager();
  Scanner sc = new Scanner(System.in);

  public OuterGame() {
    
    this.superheros = JsonIoThing.loadSuperheroArr("FileParsing/save.json");
    setAbManager();
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

        case "play":
        System.out.println("Playing Game!");
        InnerGame iG = new InnerGame(superheros, g);
        iG.playGame();
        break;

        case "save superheros":
        JsonIoThing.saveSuperheroArr(this.superheros,"FileParsing/save.json");
        break;

        case "load superheros":
        this.superheros = JsonIoThing.loadSuperheroArr("FileParsing/save.json");
        break;

        case "create superhero":
        superheros.add(CustomMaker.askNMakeSuperhero());
        break;

        case "exit":
        System.out.println("Exiting game! thanks for playing");
        System.exit(69);
        break;

        case "print all superhero names":
        for(Entity t:superheros){
          System.out.println(t.getName());
        }
        break;

        case "add abilities to hero":
        askAndAddAbilityToHero();
        break;

        case "print absolute abilities names":
        m.printAllNames();
        break;


      }
      System.out.println("\n what next?");
      input = sc.nextLine();
      input = input.toLowerCase();
    }

  }
  private void askAndAddAbilityToHero(){
    System.out.println("here are the current superheros: ");
    for(Entity t:superheros){
      System.out.println(t.getName());
    }
    Entity temp=null;
    while(temp==null){
      System.out.print("Enter the name of the superhero you want to add abilities to: \n");
      String name = sc.nextLine();
      temp = getEntity(name, superheros);
    }    
    System.out.print("Enter the name of the Abilities you want to add: ");
    ArrayList<String> names = new ArrayList<String>();
    String input = sc.nextLine();
    while (!input.equals("finished")){
      Ability temporary = m.getAbility(input);
      if(temporary !=null){
        System.out.println("Ability found!, you can input more abilities or type finished to add them to the hero");
        names.add(temporary.getName());
      }else{
        System.out.println("hey thats not a valid ability, try again: ");
      }
      input = sc.nextLine();
      input = input.toLowerCase();
    }
    m.giveAbilities(temp,names);
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
    superheros.add(A);
    superheros.add(B);
    //superheros.add(Heroes.get(Heroes.Name.BEEP_BOOP, null));
    //superheros.add(Heroes.get(Heroes.Name.JOE, null));
  }
  public static AbilityManager getAbManager(){
    AbilityManager out =new AbilityManager();
    out.load();
    return out;
  }
  private void setAbManager(){
    this.m = getAbManager();
  }
}