package revitalizedLegends.game;

import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

import revitalizedLegends.gameSystem.Ability;
import revitalizedLegends.gameSystem.Action;
import revitalizedLegends.gameSystem.BattleLog;
import revitalizedLegends.gameSystem.Campaign;
import revitalizedLegends.gameSystem.Effect;
import revitalizedLegends.gameSystem.Elements;
import revitalizedLegends.gameSystem.Encounter;
import revitalizedLegends.gameSystem.Entity;
import revitalizedLegends.gameSystem.EntityInfoItem;
import revitalizedLegends.gameSystem.Event;
import revitalizedLegends.gameSystem.Game;
import revitalizedLegends.gameSystem.InputSystem;
import revitalizedLegends.gameSystem.OutputSystem;
import revitalizedLegends.gameSystem.Shield;
import revitalizedLegends.gameSystem.State;
import revitalizedLegends.gameSystem.Storage;
import revitalizedLegends.gameSystem.Terrain;
import revitalizedLegends.gameSystem.battlelogImpls.StringBattleLog;

public class InnerGame extends Game
{
	
	
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
	  this.setOutputSystem(input);
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
	  this.setOutputSystem(input);
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

  public static class ScannerInput 
  	implements InputSystem, OutputSystem {
    private Scanner inputReader;
    private Entity target;
    private ArrayList<Entity> fighters;

    private HashMap<String, Campaign.Direction> directionalInput = new HashMap<String, Campaign.Direction>()
        {{
            put("up", Campaign.Direction.UP);
            put("w", Campaign.Direction.UP);
            put("down", Campaign.Direction.DOWN);
            put("s", Campaign.Direction.DOWN);

            put("left", Campaign.Direction.LEFT);
            put("a", Campaign.Direction.LEFT);

            put("right", Campaign.Direction.RIGHT);
            put("d", Campaign.Direction.RIGHT);
    }};

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
      System.out.println(limit);
      if (limit == -1) {
        for (int i = 0; i < fighters.size(); i++) {
          if (!fighters.get(i).equals(currentPlayer) && !fighters.get(i).equals(target)) {
            otherTargets.add(fighters.get(i));
          }
        }
      }
      for (int i = 0; i < limit; i++) {
        System.out.println("Who else to target?");
        String name = inputReader.nextLine();
        Entity target = getEntity(name, fighters);
        while (target == null) {
          System.out.println("No target specified.");
          name = inputReader.nextLine();
          target = getEntity(name, fighters);
        }
        otherTargets.add(target);
      }
      System.out.println(otherTargets.size());
      return otherTargets;
    }

	@Override
	public int getChoice(String prompt, ArrayList<String[]> choices) {
		Integer result = null;
		while (result == null)
		{
			
			for (int i = 0; i < choices.size(); i++)
			{
				System.out.println(choices.get(i)[Event.DESCRIPTION]);
			}
			System.out.println(prompt);
			if (choices.size() == 1)
			{
				inputReader.nextLine();
				return 1;
			}
			String answer = inputReader.nextLine();
			try
			{
				result = Integer.parseInt(answer);				
				if (result <= 0 || result > choices.size())
				{
					result = null;
				}
			}
			catch (InputMismatchException|NumberFormatException tme)
			{
				result = null;
			}
		}
		return result;
	}
	
	@Override
	public void displayString(String event, int type) {
		// TODO Auto-generated method stub
		System.out.println(event);
	}  

    public Campaign.Direction getDirection(ArrayList<Campaign.Direction> possibleDirections)
    {
       Campaign.Direction direction = null;
        while (direction == null)
        {
            System.out.println("Possible movements are: ");
            for (Campaign.Direction dir: possibleDirections)
            {
                System.out.println("* " + dir);
            }
            String input = inputReader.nextLine();
            direction = directionalInput.get(input);
            if (!possibleDirections.contains(direction))
            {
                direction = null;
            }
        }
        return direction;
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
  
  public class StringOutput implements OutputSystem
  {

	@Override
	public void displayString(String event, int type) {
		// TODO Auto-generated method stub
		System.out.println(event);
	}  
  }
  }

