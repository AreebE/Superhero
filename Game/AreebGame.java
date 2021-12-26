import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class AreebGame{
  
  public AreebGame(){
   
     System.out.println("Hello world!... Also trying to make custom");
    CustomMaker c = new CustomMaker();
    Entity testin = c.AskNMakeSuperhero();
    System.out.println("End of custom");
    AbilityList.giveAbility(testin,   
        AbilityList.Name.HEAL_PULSE,
        AbilityList.Name.POISON,
        AbilityList.Name.PROTECT,
        AbilityList.Name.WARNING,
        AbilityList.Name.COUNTER,
        AbilityList.Name.PRAY,
        AbilityList.Name.PROTECT,
        AbilityList.Name.BURN_UP);
    testin.addEffect(EffectList.getEffect(EffectList.Name.PERMAGEN));


    Entity robot = new Entity("BeepBoop", 1, 20, 8);
    // AbilityList.giveAbility(robot,
        // AbilityList.LIGHTNING_STRIKE,
        // AbilityList.COUNTERSTRIKE, 
        // AbilityList.FLARE_UP);
    AbilityList.giveAbility(robot,
        AbilityList.Name.FIREBALL,
        AbilityList.Name.COUNTERSTRIKE,
        AbilityList.Name.FLARE_UP,
        AbilityList.Name.CONSTRUCT,
        AbilityList.Name.WITCH_SPELL,
        AbilityList.Name.FIRST_AID);
    robot.addEffect(EffectList.getEffect(EffectList.Name.CURSE));


    Entity human = new Entity("Joe", 10, 7, 0);
    // AbilityList.giveAbility(human,
        // AbilityList.SNOWBALL,
        // AbilityList.PROTECT, 
        // AbilityList.DEFENSE_UP);
    AbilityList.giveAbility(human,
        AbilityList.Name.SNOWBALL,
        AbilityList.Name.PROTECT,
        AbilityList.Name.DEFENSE_UP,
        AbilityList.Name.COUNTER);

    Entity bland = new Entity("EEEEEE", 20, 7, 8);
    // AbilityList.giveAbility(bland,
        // AbilityList.FIREBALL,
        // AbilityList.PROTECT, 
        // AbilityList.ATTACK_UP);
    AbilityList.giveAbility(bland, 
        AbilityList.Name.FIREBALL,
        AbilityList.Name.PROTECT,
        AbilityList.Name.ATTACK_UP);

    Entity A = new Entity("A", 10, 100, 50);
    AbilityList.giveAbility(A,
        AbilityList.Name.FIREBALL,
        AbilityList.Name.SUMMON_SQUIRREL
        );

    Entity B = new Entity("B", 30, 100, 50);
    AbilityList.giveAbility(B,
        AbilityList.Name.SNOWBALL,
        AbilityList.Name.SUMMON_GOLEM
        );

    ArrayList<Entity> superheros = new ArrayList<>();
   // superheros.add(robot);
    // superheros.add(bland);
    // superheros.add(human);
    // superheros.add(testin);
    superheros.add(A);
    superheros.add(B);
    Collections.sort(superheros);
    Collections.reverse(superheros);
    // System.out.println(superheros);
    playGame(superheros);
    }

  public void playGame(ArrayList<Entity> superheros){
    // System.out.println(superheros.get(0).getHealth() + ", " + superheros.get(1).getHealth() + ", " + superheros.get(2).getHealth());

    Scanner inputReader = new Scanner(System.in);
    // int i = 0;
    boolean anyHealthZero = false;
    Terrain t = new Terrain();
    t.setsTerrianElement(ElementList.getElement(ElementList.Name.ICE));
    ArrayList<Entity.Action> actions = new ArrayList<>();
    superheros.get(0).setTerrain(t);
    superheros.get(1).setTerrain(t);

    // ArrayList<Action> actions = new
    while (!anyHealthZero){
    //   Entity currentPlayer = superheros.get(i);
      // System.out.println(currentPlayer.getName() + "\'s turn. Has the health of " + currentPlayer.getHealth() + ", the sheild of " + currentPlayer.getSheildHealth() + " and abilities:\n" + currentPlayer.getAbilities());
    //   System.out.println(currentPlayer);
        for (int i = 0; i < superheros.size(); i++)
        {
            Entity e = superheros.get(i);
            Entity.Action a = null;
            while (a == null)
            {
                System.out.println(e);
                Entity target = getTarget(superheros, inputReader);
                System.out.println(target);
                String abilityName = null;
                if (!(e instanceof AIEntity))
                {
                    System.out.println("Choose an ability.");
                    abilityName = inputReader.next();
                }
                a = e.getAction(target, abilityName, superheros, superheros);
            }
            actions.add(a);
            e.endOfTurn();
        }

        for (int i = 0; i < actions.size(); i++)
        {
            System.out.println(actions.get(i).getClass());
            actions.get(i).performAction();
        }
        actions.clear();
        Collections.sort(superheros);
        Collections.reverse(superheros);

      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    
    }
    inputReader.close();
    // System.out.println(superheros.get(0).getHealth() + ", " + superheros.get(1).getHealth() + ", " + superheros.get(2).getHealth());
        System.out.println(superheros);

    
  }

  private Entity getEntity(String name, ArrayList<Entity> superheros){
    for (int i = 0; i < superheros.size(); i++){
      if (superheros.get(i).getName().equals(name)){
        return superheros.get(i);
      }
    }
    return null;
  }

  private Entity getTarget(ArrayList<Entity> superheros, Scanner inputReader){
    System.out.println("Who to target?");
    String name = inputReader.next();
      if (name.toLowerCase().equals("pass")){
          return null;
      }
    Entity target = getEntity(name, superheros);
    while (target == null){
      System.out.println("No target specified.");
      name = inputReader.next();
      if (name.toLowerCase().equals("pass")){
          return null;
      }
      target = getEntity(name, superheros);
    }
    return target;
  }

  private Ability useAbility(Scanner inputReader, Entity currentPlayer, Entity target){
    System.out.println("Which ability to use?");
    AbilityList.Name nameOfAbility = AbilityList.getName(inputReader.next());
    Ability abilityUsed = currentPlayer.getAbility( nameOfAbility);
    while (abilityUsed == null){
      System.out.println("Choose a different ability.");
      nameOfAbility = AbilityList.getName(inputReader.next());
      abilityUsed = currentPlayer.getAbility(nameOfAbility);
    }
    return abilityUsed;
  }
}