package battlesystem;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;


public class AreebGame{

    ArrayList<Entity> superheros;
    Entity currentPlayer;
    ScannerInput system;

  public AreebGame(){
   
    //  System.out.println("Hello world!... Also trying to make custom");
    // CustomMaker c = new CustomMaker();
    // Entity testin = c.AskNMakeSuperhero();
    // System.out.println("End of custom");
    // Abilities.giveAbility(testin,   
    //     Abilities.Name.HEAL_PULSE,
    //     Abilities.Name.POISON,
    //     Abilities.Name.PROTECT,
    //     Abilities.Name.WARNING,
    //     Abilities.Name.COUNTER,
    //     Abilities.Name.PRAY,
    //     Abilities.Name.PROTECT,
    //     Abilities.Name.BURN_UP);
    // testin.addEffect(Effects.getEffect(Effects.Name.PERMAGEN));


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

    Entity A = new Entity("A", 10, 100, 50);
    Abilities.giveAbility(A,
        Abilities.Name.FIREBALL,
        Abilities.Name.SUMMON_SQUIRREL,
        Abilities.Name.RAM_ATTACK
        );

    Entity B = new Entity("B", 30, 100, 50);
    Abilities.giveAbility(B,
        Abilities.Name.SNOWBALL,
        Abilities.Name.SUMMON_GOLEM,
        Abilities.Name.GROUND_SUCTION
        );

    superheros = new ArrayList<>();
    // superheros.add(Heroes.getHero(Heroes.Name.BEEP_BOOP, null));
    // superheros.add(Heroes.getHero(Heroes.Name.JOE, null));
    // superheros.add(Heroes.getHero(Heroes.Name.EEEEEE, null));

    // superheros.add(testin);
    superheros.add(A);
    superheros.add(B);
    Collections.sort(superheros);
    Collections.reverse(superheros);
    // System.out.println(superheros);
    currentPlayer = null;
    playGame(superheros);
    }

  public void playGame(ArrayList<Entity> superheros){
    // System.out.println(superheros.get(0).getHealth() + ", " + superheros.get(1).getHealth() + ", " + superheros.get(2).getHealth());

    Scanner inputReader = new Scanner(System.in);
    system = new ScannerInput(inputReader);
    // int i = 0;
    boolean anyHealthZero = false;
    Terrain t = new Terrain();
    t.setsTerrianElement(Elements.getElement(Elements.Name.ICE));
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
            currentPlayer = superheros.get(i);
            Entity.Action a = null;
            while (a == null)
            {
                System.out.println(currentPlayer);
// =                System.out.println(target);
                a = currentPlayer.getAction(superheros, system);
            }
            actions.add(a);
            currentPlayer.endOfTurn();
        }

        for (int i = 0; i < actions.size(); i++)
        {
            // System.out.println(actions.get(i).getClass());
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

    private class ScannerInput implements InputSystem 
    {
        private Scanner inputReader;

        public ScannerInput(Scanner inputReader)
        {
            this.inputReader = inputReader;
        }

        @Override
        public String getAbilityName()
        {
            System.out.println("Which ability to use?");
            return inputReader.next();
        }        
        
        @Override
        public Entity getSingleTarget()
        {
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

        @Override
        public List<Entity> getSecondaryTargets()
        {
            return null;
        }
    }

  private Entity getEntity(String name, ArrayList<Entity> superheros){
    for (int i = 0; i < superheros.size(); i++){
      if (superheros.get(i).getName().equals(name)){
        return superheros.get(i);
      }
    }
    return null;
  }
}