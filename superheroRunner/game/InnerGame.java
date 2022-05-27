package game;

import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

import battlesystem.Ability;
import battlesystem.Action;
import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Elements;
import battlesystem.Encounter;
import battlesystem.Entity;
import battlesystem.EntityInfoItem;
import battlesystem.Game;
import battlesystem.InputSystem;
import battlesystem.Shield;
import battlesystem.State;
import battlesystem.Storage;
import battlesystem.Terrain;
import battlesystem.battlelogImpls.StringBattleLog;

public class InnerGame extends Game{
	
	
	private GUI g;
  public InnerGame(
		  ArrayList<EntityInfoItem> fighters, 
		  Storage s,
		  GUI g) {
	  super(fighters, s, new StringBattleLog());
	  
//    System.out.println(fighters.size());
	  ScannerInput input = new ScannerInput();
	  input.assignFighters(super.getFighters());
	  this.setInputSystem(input);
	  this.g = g;
  }

  
  public InnerGame(
		  Encounter e, 
		  Storage s,
		  GUI g,
		  EntityInfoItem protag)
  {
	  super(e, s, new StringBattleLog(), protag);
	  ScannerInput input = new ScannerInput();
	  input.assignFighters(super.getFighters());
	  this.setInputSystem(input);
	  this.g = g;
  }



  private static Entity getEntity(String name, ArrayList<Entity> fighters) {
    for (int i = 0; i < fighters.size(); i++) {
      if (fighters.get(i).getName().equals(name)) {
        return fighters.get(i);
      }
    }
    return null;
  }

  public static class ScannerInput implements InputSystem {
    private Scanner inputReader;
    private Entity target;
    private ArrayList<Entity> fighters;

    public ScannerInput(Scanner inputReader) {
      this.inputReader = inputReader;
    }

    public ScannerInput() {
      this.inputReader = new Scanner(System.in);
    }

    public void assignFighters(ArrayList<Entity> fighters)
    {
    	this.fighters = fighters;
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

  @Override 
  public void printLog(BattleLog log)
  {
	StringBattleLog stringLog = (StringBattleLog) log;
	ArrayList<String> entries = stringLog.getFullLog();
	for (int i = 0; i < entries.size(); i++)
	{
		System.out.println(entries.get(i));
	}
  }
}
