package game;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

import battlesystem.Action;
import battlesystem.BattleLog;
import battlesystem.Entity;
import battlesystem.InputSystem;
import battlesystem.Terrain;
import battlesystem.battlelogImpls.StringBattleLog;
import battlesystem.databaseImpls.Elements;

class InnerGame {
  ArrayList<Entity> fighters;
  GUI g;
  Terrain t = new Terrain();
  Entity currentPlayer;
  ScannerInput scanInput;

public InnerGame(GUI g) {
    this.g = g;
}
  public InnerGame(ArrayList<Entity> fighters, GUI g) {
    this.fighters = fighters;
    this.g = g;
  }

  public void Fight(ArrayList<Entity> entities) {
    //we will have to figure out teams at some point 
    this.fighters = entities;
    ScannerInput scanInput = new ScannerInput();
    
    boolean anyHealthZero = false;
    Terrain t = new Terrain();
    t.setsTerrianElement(Elements.getElement(Elements.Name.ICE));
    ArrayList<Action> actionsToPreform = new ArrayList<Action>();
    fighters.get(0).setTerrain(t);
    fighters.get(1).setTerrain(t);
    while (fighters.size() > 1) {
      BattleLog log = new StringBattleLog();
      //asks players for what actions to preform
      for (int i = 0; i < fighters.size(); i++) {
        currentPlayer = fighters.get(i);
        for(Action a:currentPlayer.onTurn(fighters,scanInput)){
          actionsToPreform.add(a);
        }
        
      }
      
      //executes actionsToPreform
      for (Action q : actionsToPreform) {
        q.performAction(log);
        System.out.println();
      }
      
      //checks for dead people
      for (int i = fighters.size() - 1; i >= 0; i--) {
        if (fighters.get(i).isHealthZero(log)) {
          Entity target = fighters.remove(i);
          System.out.println(target.getName() + " was eliminated. " + target.toString());
        }
      }
      //prints whats happened
      ArrayList<String> fullLog = (ArrayList<String>) log.getFullLog();
      System.out.println(fullLog);
      actionsToPreform.clear();
      Collections.sort(fighters);
      Collections.reverse(fighters);
      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
    // scanInput.close();
    // System.out.println(fighters.get(0).getHealth() + ", " +
    // fighters.get(1).getHealth() + ", " + fighters.get(2).getHealth());
    System.out.println(fighters.get(0).getName() + " won!");
  }


  private Entity getEntity(String name, ArrayList<Entity> fighters) {
    for (int i = 0; i < fighters.size(); i++) {
      if (fighters.get(i).getName().equals(name)) {
        return fighters.get(i);
      }
    }
    return null;
  }

  public class ScannerInput implements InputSystem {
    private Scanner inputReader;
    private Entity target;

    public ScannerInput(Scanner inputReader) {
      this.inputReader = inputReader;
    }

    public ScannerInput() {
      this.inputReader = new Scanner(System.in);
    }

    @Override
    public String getAbilityName() {
      System.out.println("Which ability to use?");
      return inputReader.nextLine();
    }

    @Override
    public Entity getSingleTarget() {
      System.out.println("Who to target?");
      String name = inputReader.nextLine();
      Entity target = getEntity(name, fighters);
      while (target == null) {
        System.out.println("No target specified.");
        name = inputReader.nextLine();
        target = getEntity(name, fighters);
      }
      this.target = target;
      return target;
    }

    @Override
    public List<Entity> getSecondaryTargets(Integer limit, Entity currentPlayer) {
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
        String name = inputReader.nextLine();
        Entity target = getEntity(name, fighters);
        while (target == null && otherTargets.contains(target)) {
          System.out.println("No target specified.");
          name = inputReader.nextLine();
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

}
