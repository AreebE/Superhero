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

  ArrayList<Entity> superheros = new ArrayList<Entity>();
  GUI g = new GUI();
  ScannerInput system;
  AbilityManager m = new AbilityManager();
  

  public OuterGame() {
    
    makeFixedEntities();
    mainMenu();
    System.out.println("END OF GAME");
  }

  private void mainMenu(){
    System.out.println("welcommen to superheros! \n what would you like to do? (type help for CMDs)");
    Scanner sc = new Scanner(System.in);
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


      }
      System.out.println("\n what next?");
      input = sc.nextLine();
      input = input.toLowerCase();
    }

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
    
    Entity A = new Entity("A", 10, 100, 50, States.get(States.Name.NORMAL), null);
    Abilities.giveAbility(A, Abilities.Name.FIREBALL, Abilities.Name.SUMMON_SQUIRREL);

    Entity B = new Entity("B", 30, 100, 50, States.get(States.Name.NORMAL), null);
    Abilities.giveAbility(B, Abilities.Name.SNOWBALL, Abilities.Name.SUMMON_GOLEM, Abilities.Name.GROUND_SUCTION);

    superheros.add(A);
    superheros.add(B);
    superheros.add(Heroes.get(Heroes.Name.BEEP_BOOP, null));
    superheros.add(Heroes.get(Heroes.Name.JOE, null));
  }
}