import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class Game{
  public Game(){
     System.out.println("Hello world!");
    Superhero robot = new Superhero("BeepBoop", 1, 7, 8);
    AbilityList.giveAbility(robot,
        AbilityList.LIGHTNING_STRIKE,
        AbilityList.COUNTERSTRIKE, 
        AbilityList.FLARE_UP);
    
    Superhero human = new Superhero("Joe", 10, 7, 8);
    AbilityList.giveAbility(human,
        AbilityList.SNOWBALL,
        AbilityList.PROTECT, 
        AbilityList.DEFENSE_UP);
    

    Superhero bland = new Superhero("EEEEEE", 20, 7, 8);

    AbilityList.giveAbility(bland,
        AbilityList.FIREBALL,
        AbilityList.PROTECT, 
        AbilityList.ATTACK_UP);

    ArrayList<Superhero> superheros = new ArrayList<>();
    superheros.add(robot);
    superheros.add(bland);
    superheros.add(human);
    Collections.sort(superheros);
    Collections.reverse(superheros);
    System.out.println(superheros);
    playGame(superheros);
  }

  public void playGame(ArrayList<Superhero> superheros){
    System.out.println(superheros.get(0).getHealth() + ", " + superheros.get(1).getHealth() + ", " + superheros.get(2).getHealth());

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
    Integer idOfAbility = AbilityList.getID(inputReader.next());
    Ability abilityUsed = currentPlayer.getAbility( idOfAbility);
    while (abilityUsed == null){
      System.out.println("Choose a different ability.");
      idOfAbility = AbilityList.getID(inputReader.next());
      abilityUsed = currentPlayer.getAbility(idOfAbility);
    }
    return abilityUsed;
  }
}