/*
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


    // Entity robot = new Entity("BeepBoop", 1, 20, 8);
    // Abilities.giveAbility(robot,
        // Abilities.LIGHTNING_STRIKE,
        // Abilities.COUNTERSTRIKE, 
        // Abilities.FLARE_UP);
    // Abilities.giveAbility(robot,
    //     Abilities.Name.FIREBALL,
    //     Abilities.Name.COUNTERSTRIKE,
    //     Abilities.Name.FLARE_UP,
    //     Abilities.Name.CONSTRUCT,
    //     Abilities.Name.WITCH_SPELL,
    //     Abilities.Name.FIRST_AID);
    // robot.addEffect(Effects.getEffect(Effects.Name.CURSE));

    // Entity human = new Entity("Joe", 10, 7, 0, null);
    // Abilities.giveAbility(human,
        // Abilities.SNOWBALL,
        // Abilities.PROTECT, 
        // Abilities.DEFENSE_UP);
    // Abilities.giveAbility(human,
    //     Abilities.Name.SNOWBALL,
    //     Abilities.Name.PROTECT,
    //     Abilities.Name.DEFENSE_UP,
    //     Abilities.Name.COUNTER);

    // // Entity bland = new Entity("EEEEEE", 20, 7, 8, null);
    // // Abilities.giveAbility(bland,
    //     // Abilities.FIREBALL,
    //     // Abilities.PROTECT, 
    //     // Abilities.ATTACK_UP);
    // Abilities.giveAbility(bland, 
    //     Abilities.Name.FIREBALL,
    //     Abilities.Name.PROTECT,
    //     Abilities.Name.ATTACK_UP);

    // Entity A = new Entity("A", 10, 100, 50, null);
    // Abilities.giveAbility(A,
    //     Abilities.Name.FIREBALL,
    //     Abilities.Name.SUMMON_SQUIRREL,
    //     Abilities.Name.RAM_ATTACK
    //     );

    // Entity B = new Entity("B", 30, 100, 50, null);
    // Abilities.giveAbility(B,
    //     Abilities.Name.SNOWBALL,
    //     Abilities.Name.SUMMON_GOLEM,
    //     Abilities.Name.GROUND_SUCTION
    //     );

    superheros = new ArrayList<>();
    superheros.add(Heroes.get(Heroes.Name.BEEP_BOOP, null));
    superheros.add(Heroes.get(Heroes.Name.JOE, null));
    superheros.add(Heroes.get(Heroes.Name.EEEEEE, null));
    superheros.add(Heroes.get(Heroes.Name.TEST_SUBJECT, null));
    superheros.add(Heroes.get(Heroes.Name.SECOND_TEST, null));


    // superheros.add(testin);
    // superheros.add(A);
    // superheros.add(B);
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
    ArrayList<Action> actions = new ArrayList<>();
    superheros.get(0).setTerrain(t);
    superheros.get(1).setTerrain(t);
    int removeCount = 0;
    // ArrayList<Action> actions = new
    while (superheros.size() > 1){
    //   Entity currentPlayer = superheros.get(i);
      // System.out.println(currentPlayer.getName() + "\'s turn. Has the health of " + currentPlayer.getHealth() + ", the sheild of " + currentPlayer.getSheildHealth() + " and abilities:\n" + currentPlayer.getAbilities());
    //   System.out.println(currentPlayer);
        for (int i = 0; i < superheros.size(); i++)
        {
            currentPlayer = superheros.get(i);
            System.out.println(currentPlayer);
            List<Action> playerActions = currentPlayer.getActions(superheros, system);
            
            for (Action a: playerActions)
            {
                actions.add(0, a);
            }
        }
        
        // String[] battleLog = new String[actions.size()];
        BattleLog log = new StringBattleLog();
        for (int i = actions.size() - 1; i >= 0; i--)
        {
            // System.out.println(actions.get(i).getClass());
            actions.get(i).performAction(log);
            System.out.println();
        }
        // System.out.println(log.getFullLog().toString());
        for (int i = superheros.size() - 1; i >= 0; i--)
        {
            if (superheros.get(i).isHealthZero(log))
            {
                Entity target = superheros.remove(i);
                System.out.println(target.getName() + " was eliminated. " + target.toString());
                removeCount++;
            }
            // superheros.get(i);
        }

        ArrayList<String> fullLog = (ArrayList<String>) log.getFullLog();
        for (String s: fullLog)
        {
            System.out.println(s);
        }
        
        actions.clear();
        Collections.sort(superheros);
        Collections.reverse(superheros);

      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    
    }
    inputReader.close();
    // System.out.println(superheros.get(0).getHealth() + ", " + superheros.get(1).getHealth() + ", " + superheros.get(2).getHealth());
    System.out.println(superheros.get(0).getName() + " won!");
  }

    private class ScannerInput implements InputSystem 
    {
        private Scanner inputReader;
        private Entity target;

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
            Entity target = getEntity(name, superheros);
            while (target == null){
                System.out.println("No target specified.");
                name = inputReader.next();
                target = getEntity(name, superheros);
            }
            this.target = target;
            return target;
        }

        @Override
        public List<Entity> getSecondaryTargets(Integer limit)
        {
            ArrayList<Entity> otherTargets = new ArrayList<>();
            if (limit == -1)
            {
                for (int i = 0; i < superheros.size(); i++)
                {
                    if (!superheros.get(i).equals(currentPlayer) && !superheros.get(i).equals(target))
                    {
                        otherTargets.add(superheros.get(i));
                    }
                }
            }
            for (int i = 0; i < limit && otherTargets.size() < superheros.size() - 1; i++ )
            {
                System.out.println("Who else to target?");
                String name = inputReader.next();
                Entity target = getEntity(name, superheros);
                while (target == null && otherTargets.contains(target))
                {
                    System.out.println("No target specified.");
                    name = inputReader.next();
                    if (name.toLowerCase().equals("pass")){
                        return null;
                    }
                    target = getEntity(name, superheros);
                }
                otherTargets.add(target);
            }
            return otherTargets;
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
*/