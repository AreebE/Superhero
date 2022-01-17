package battlesystem;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

class InnerGame {
  ArrayList<Entity> fighters;
  GUI g;
  
  Terrain t = new Terrain();
  Entity currentPlayer;
  ScannerInput system;

  public InnerGame(ArrayList<Entity> fighters, GUI g) {
    this.fighters = fighters;
    this.g = g;
    System.out.println("Starting game!");
  }

  public void playGame() {
    ArrayList<Entity> fighters = this.fighters;

    // System.out.println(fighters.get(0).getHealth() + ", " +
    // fighters.get(1).getHealth() + ", " + fighters.get(2).getHealth());

    Scanner inputReader = new Scanner(System.in);
    system = new ScannerInput(inputReader);
    // int i = 0;
    boolean anyHealthZero = false;
    Terrain t = new Terrain();
    t.setsTerrianElement(Elements.getElement(Elements.Name.ICE));
    ArrayList<Action> actions = new ArrayList<>();
    fighters.get(0).setTerrain(t);
    fighters.get(1).setTerrain(t);
    int removeCount = 0;
    // ArrayList<Action> actions = new
    while (fighters.size() > 1) {
      // Entity currentPlayer = fighters.get(i);
      // System.out.println(currentPlayer.getName() + "\'s turn. Has the health of " +
      // currentPlayer.getHealth() + ", the sheild of " +
      // currentPlayer.getSheildHealth() + " and abilities:\n" +
      // currentPlayer.getAbilities());
      // System.out.println(currentPlayer);
      for (int i = 0; i < fighters.size(); i++) {
        currentPlayer = fighters.get(i);
        System.out.println(currentPlayer);
        List<Action> playerActions = currentPlayer.getActions(fighters, system);

        for (Action a : playerActions) {
          actions.add(0, a);
        }
      }

      // String[] battleLog = new String[actions.size()];
      BattleLog log = new StringBattleLog();
      for (int i = actions.size() - 1; i >= 0; i--) {
        // System.out.println(actions.get(i).getClass());
        actions.get(i).performAction(log);
        System.out.println();
      }
      // System.out.println(log.getFullLog().toString());
      for (int i = fighters.size() - 1; i >= 0; i--) {
        if (fighters.get(i).isHealthZero(log)) {
          Entity target = fighters.remove(i);
          System.out.println(target.getName() + " was eliminated. " + target.toString());
          removeCount++;
        }
        // fighters.get(i);
      }

      ArrayList<String> fullLog = (ArrayList<String>) log.getFullLog();
      for (String s : fullLog) {
        System.out.println(s);
      }

      actions.clear();
      Collections.sort(fighters);
      Collections.reverse(fighters);

      System.out.println(
          "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

    }
    inputReader.close();
    // System.out.println(fighters.get(0).getHealth() + ", " +
    // fighters.get(1).getHealth() + ", " + fighters.get(2).getHealth());
    System.out.println(fighters.get(0).getName() + " won!");
  }

  private class ScannerInput implements InputSystem {
    private Scanner inputReader;
    private Entity target;

    public ScannerInput(Scanner inputReader) {
      this.inputReader = inputReader;
    }

    @Override
    public String getAbilityName() {
      System.out.println("Which ability to use?");
      return inputReader.next();
    }

    @Override
    public Entity getSingleTarget() {
      System.out.println("Who to target?");
      String name = inputReader.next();
      Entity target = getEntity(name, fighters);
      while (target == null) {
        System.out.println("No target specified.");
        name = inputReader.next();
        target = getEntity(name, fighters);
      }
      this.target = target;
      return target;
    }

    @Override
    public List<Entity> getSecondaryTargets(Integer limit) {
      ArrayList<Entity> otherTargets = new ArrayList<>();
      if (limit == -1) {
        for (int i = 0; i < fighters.size(); i++) {
          if (!fighters.get(i).equals(currentPlayer) && !fighters.get(i).equals(target)) {
            otherTargets.add(fighters.get(i));
          }
        }
      }
      for (int i = 0; i < limit && otherTargets.size() < fighters.size() - 1; i++) {
        System.out.println("Who else to target?");
        String name = inputReader.next();
        Entity target = getEntity(name, fighters);
        while (target == null && otherTargets.contains(target)) {
          System.out.println("No target specified.");
          name = inputReader.next();
          if (name.toLowerCase().equals("pass")) {
            return null;
          }
          target = getEntity(name, fighters);
        }
        otherTargets.add(target);
      }
      return otherTargets;
    }
  }

  private Entity getEntity(String name, ArrayList<Entity> fighters) {
    for (int i = 0; i < fighters.size(); i++) {
      if (fighters.get(i).getName().equals(name)) {
        return fighters.get(i);
      }
    }
    return null;
  }
}
