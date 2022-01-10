import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;
import battlesystem.*;

public class Game{
  
  public Game(){
    //how do we want to go about saving/loading Heros n stuff?
    // not entirely sure, maybe health and what abilities they have?
    //yea i know what data to save im just wondering how to go about it like 
    //do we make a Hero[] and add some new ones to it on creation or do a more dynamic system
    // wdym by dynamic? as in make it so that you can change your hero at the beginning of the game
    // that could work for starting a new game, yeah. sounds good
    // btw, we'll be using a github called Entity, do you have a github name?
    // yes i think its paulo-grab-nsd
    // just invited you, make sure to use that github and not this project anymore
    GUI g = new GUI();
     System.out.println("Hello world!... Also trying to make custom");
    CustomMaker c = new CustomMaker();
    Entity testin = c.AskNMakeSuperhero();
    System.out.println("End of custom");
    Abilities.giveAbility(testin,   
        Abilities.Name.HEAL_PULSE,
        Abilities.Name.POISON,
        Abilities.Name.PROTECT,
        Abilities.Name.WARNING,
        Abilities.Name.COUNTER,
        Abilities.Name.PRAY,
        Abilities.Name.PROTECT,
        Abilities.Name.BURN_UP);
    testin.addEffect(Effects.getEffect(Effects.Name.PERMAGEN));


    Entity robot = new Entity("BeepBoop", 1, 20, 8);
    // Abilities.giveAbility(robot,
        // Abilities.LIGHTNING_STRIKE,
        // Abilities.COUNTERSTRIKE, 
        // Abilities.FLARE_UP);
    Abilities.giveAbility(robot,
        Abilities.Name.FIREBALL,
        Abilities.Name.COUNTERSTRIKE,
        Abilities.Name.FLARE_UP,
        Abilities.Name.CONSTRUCT,
        Abilities.Name.WITCH_SPELL,
        Abilities.Name.FIRST_AID);
    robot.addEffect(Effects.getEffect(Effects.Name.CURSE));
    
    Entity human = new Entity("Joe", 10, 7, 0);
    // Abilities.giveAbility(human,
        // Abilities.SNOWBALL,
        // Abilities.PROTECT, 
        // Abilities.DEFENSE_UP);
    Abilities.giveAbility(human,
        Abilities.Name.SNOWBALL,
        Abilities.Name.PROTECT,
        Abilities.Name.DEFENSE_UP,
        Abilities.Name.COUNTER);

    Entity bland = new Entity("EEEEEE", 20, 7, 8);
    // Abilities.giveAbility(bland,
        // Abilities.FIREBALL,
        // Abilities.PROTECT, 
        // Abilities.ATTACK_UP);
    Abilities.giveAbility(bland, 
        Abilities.Name.FIREBALL,
        Abilities.Name.PROTECT,
        Abilities.Name.ATTACK_UP);

    ArrayList<Entity> Entitys = new ArrayList<>();
   // Entitys.add(robot);
    // Entitys.add(bland);
    Entitys.add(human);
    Entitys.add(testin);
    Collections.sort(Entitys);
    Collections.reverse(Entitys);
    // System.out.println(Entitys);
    
    playGame(Entitys);
  }

  public void playGame(ArrayList<Entity> Entitys){
    // System.out.println(Entitys.get(0).getHealth() + ", " + Entitys.get(1).getHealth() + ", " + Entitys.get(2).getHealth());

    Scanner inputReader = new Scanner(System.in);
    int i = 0;
    boolean anyHealthZero = false;
    Terrain t = new Terrain();
    t.setsTerrianElement(Elements.getElement(Elements.Name.ICE));
    Entitys.get(0).setTerrain(t);
    Entitys.get(1).setTerrain(t);

    while (!anyHealthZero){
      Entity currentPlayer = Entitys.get(i);
      // System.out.println(currentPlayer.getName() + "\'s turn. Has the health of " + currentPlayer.getHealth() + ", the sheild of " + currentPlayer.getSheildHealth() + " and abilities:\n" + currentPlayer.getAbilities());
      System.out.println(currentPlayer);

      Entity target = getTarget(Entitys, inputReader);
      System.out.println("Target - " + target);
      Ability abilityToUse = useAbility(inputReader, currentPlayer, target); 
      abilityToUse.useAbility(target, currentPlayer, Entitys, Entitys);

      i = (i + 1) % Entitys.size();
      currentPlayer.endOfTurn();
      anyHealthZero = target.isPlayerHealthZero();
      // System.out.println(Entitys.get(0).getHealth() + ", " + Entitys.get(1).getHealth() + ", " + Entitys.get(2).getHealth());
      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
    inputReader.close();
    // System.out.println(Entitys.get(0).getHealth() + ", " + Entitys.get(1).getHealth() + ", " + Entitys.get(2).getHealth());
        System.out.println(Entitys);

    
  }

  private Entity getEntity(String name, ArrayList<Entity> Entitys){
    for (int i = 0; i < Entitys.size(); i++){
      if (Entitys.get(i).getName().equals(name)){
        return Entitys.get(i);
      }
    }
    return null;
  }

  private Entity getTarget(ArrayList<Entity> Entitys, Scanner inputReader){
    System.out.println("Who to target?");
    Entity target = getEntity(inputReader.next(), Entitys);
    while (target == null){
      System.out.println("No target specified.");
      target = getEntity(inputReader.next(), Entitys);
    }
    return target;
  }

  private Ability useAbility(Scanner inputReader, Entity currentPlayer, Entity target){
    System.out.println("Which ability to use?");
    Abilities.Name nameOfAbility = Abilities.getName(inputReader.next());
    Ability abilityUsed = currentPlayer.getAbility( nameOfAbility);
    while (abilityUsed == null){
      System.out.println("Choose a different ability.");
      nameOfAbility = Abilities.getName(inputReader.next());
      abilityUsed = currentPlayer.getAbility(nameOfAbility);
    }
    return abilityUsed;
  }
}