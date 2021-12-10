import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class Game{
  
  public Game(){
    //how do we want to go about saving/loading Heros n stuff?
    // not entirely sure, maybe health and what abilities they have?
    //yea i know what data to save im just wondering how to go about it like 
    //do we make a Hero[] and add some new ones to it on creation or do a more dynamic system
    // wdym by dynamic? as in make it so that you can change your hero at the beginning of the game
    // that could work for starting a new game, yeah. sounds good
    // btw, we'll be using a github called Superhero, do you have a github name?
    // yes i think its paulo-grab-nsd
    // just invited you, make sure to use that github and not this project anymore
     System.out.println("Hello world!... Also trying to make custom");
    CustomMaker c = new CustomMaker();
    Superhero testin = c.AskNMakeSuperhero();
    System.out.println("End of custom");
    AbilityList.giveAbility(testin,   
        AbilityList.AbilityNames.HEAL_PULSE,
        AbilityList.AbilityNames.POISON);
    


    Superhero robot = new Superhero("BeepBoop", 1, 7, 8);
    // AbilityList.giveAbility(robot,
        // AbilityList.LIGHTNING_STRIKE,
        // AbilityList.COUNTERSTRIKE, 
        // AbilityList.FLARE_UP);
    AbilityList.giveAbility(robot,
        AbilityList.AbilityNames.LIGHTNING_STRIKE,
        AbilityList.AbilityNames.COUNTERSTRIKE,
        AbilityList.AbilityNames.FLARE_UP);
    
    Superhero human = new Superhero("Joe", 10, 7, 8);
    // AbilityList.giveAbility(human,
        // AbilityList.SNOWBALL,
        // AbilityList.PROTECT, 
        // AbilityList.DEFENSE_UP);
    AbilityList.giveAbility(human,
        AbilityList.AbilityNames.SNOWBALL,
        AbilityList.AbilityNames.PROTECT,
        AbilityList.AbilityNames.DEFENSE_UP);

    Superhero bland = new Superhero("EEEEEE", 20, 7, 8);
    // AbilityList.giveAbility(bland,
        // AbilityList.FIREBALL,
        // AbilityList.PROTECT, 
        // AbilityList.ATTACK_UP);
    AbilityList.giveAbility(bland, 
        AbilityList.AbilityNames.FIREBALL,
        AbilityList.AbilityNames.PROTECT,
        AbilityList.AbilityNames.ATTACK_UP);

    ArrayList<Superhero> superheros = new ArrayList<>();
    superheros.add(robot);
    superheros.add(bland);
    superheros.add(human);
    superheros.add(testin);
    Collections.sort(superheros);
    Collections.reverse(superheros);
    System.out.println(superheros);
    playGame(superheros);
  }

  public void playGame(ArrayList<Superhero> superheros){
    // System.out.println(superheros.get(0).getHealth() + ", " + superheros.get(1).getHealth() + ", " + superheros.get(2).getHealth());

    Scanner inputReader = new Scanner(System.in);
    int i = 0;
    boolean anyHealthZero = false;
    while (!anyHealthZero){
      Superhero currentPlayer = superheros.get(i);
      // System.out.println(currentPlayer.getName() + "\'s turn. Has the health of " + currentPlayer.getHealth() + ", the sheild of " + currentPlayer.getSheildHealth() + " and abilities:\n" + currentPlayer.getAbilities());
      System.out.println(currentPlayer);

      Superhero target = getTarget(superheros, inputReader);
      System.out.println("Target - " + target);
      Ability abilityToUse = useAbility(inputReader, currentPlayer, target); 
      abilityToUse.useAbility(target, currentPlayer);

      i = (i + 1) % superheros.size();
      currentPlayer.endOfTurn();
      anyHealthZero = target.isPlayerHealthZero();
      // System.out.println(superheros.get(0).getHealth() + ", " + superheros.get(1).getHealth() + ", " + superheros.get(2).getHealth());
      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
    inputReader.close();
    System.out.println(superheros.get(0).getHealth() + ", " + superheros.get(1).getHealth() + ", " + superheros.get(2).getHealth());
    
    
  }

  private Superhero getSuperhero(String name, ArrayList<Superhero> superheros){
    for (int i = 0; i < superheros.size(); i++){
      if (superheros.get(i).getName().equals(name)){
        return superheros.get(i);
      }
    }
    return null;
  }

  private Superhero getTarget(ArrayList<Superhero> superheros, Scanner inputReader){
    System.out.println("Who to target?");
    Superhero target = getSuperhero(inputReader.next(), superheros);
    while (target == null){
      System.out.println("No target specified.");
      target = getSuperhero(inputReader.next(), superheros);
    }
    return target;
  }

  private Ability useAbility(Scanner inputReader, Superhero currentPlayer, Superhero target){
    System.out.println("Which ability to use?");
    AbilityList.AbilityNames nameOfAbility = AbilityList.getName(inputReader.next());
    Ability abilityUsed = currentPlayer.getAbility( nameOfAbility);
    while (abilityUsed == null){
      System.out.println("Choose a different ability.");
      nameOfAbility = AbilityList.getName(inputReader.next());
      abilityUsed = currentPlayer.getAbility(nameOfAbility);
    }
    return abilityUsed;
  }
}