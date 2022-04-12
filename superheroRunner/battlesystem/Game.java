package battlesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import battlesystem.battlelogImpls.StringBattleLog;

public class Game {
	

	public enum Type
	{
		ABILITY,
		EFFECT,
		SPAWNABLE,
		SHIELD,
		STATE
	}
	public class Team
	{
		private ArrayList<Entity> members;
	
		public Team()
		{
			members = new ArrayList<Entity>();
		}
		
		protected ArrayList<Entity> getMembers()
		{
			return members;
		}
		
		protected void addMember(Entity member)
		{
			members.add(members.size(), member);
		}
	}
	
	private HashMap<String, Ability> abilities;
	private HashMap<String, Effect> effects;
	private HashMap<String, EntityInfoItem> spawnables;
	private HashMap<String, Shield> shields;
	private HashMap<String, State> states;
	private InputSystem input;
	
	private ArrayList<Entity> allFighters;
	private ArrayList<Team> teams;
	private BattleLog log;
	
	public Game(
			ArrayList<EntityInfoItem> initialFighters,
			HashMap<String, Ability> abilities,
			HashMap<String, Effect> effects,
			HashMap<String, EntityInfoItem> spawnables,
			HashMap<String, Shield> shields,
			HashMap<String, State> states,
			BattleLog log)
	{
		this.abilities = abilities;
		this.effects = effects;
		this.spawnables = spawnables;
		this.shields = shields;
		this.states = states;
		this.input = input;
		this.teams = new ArrayList<>();
		this.allFighters = new ArrayList<>();
		this.log = log;
		for (int i = 0; i < initialFighters.size(); i++)
		{
//			System.out.println(initialFighters.get(i).defaultState);
			Entity e = initialFighters.get(i).create(null, this);
			Team t = new Team();
			t.addMember(e);
			e.setTeamID(i);
			teams.add(t);
			allFighters.add(e);
		}
	}
	
	public void setInputSystem(InputSystem input)
	{
		this.input = input;
	}
	
	public Ability getAbility(String name)
	{
		return abilities.get(name).copy();
	}
	
	public EntityInfoItem getSpawnable(String name)
	{
		return spawnables.get(name);
	}
	
	public Effect getEffect(String name)
	{
		return effects.get(name).copy();
	}
	
	public Shield getShield(String name)
	{
		return shields.get(name).copy();
	}
	
	public State getState(String name)
	{
		return states.get(name).copy();
	}

	public void addMember(Entity created, int teamToJoin)
	{
		teams.get(teamToJoin).addMember(created);
		allFighters.add(created);
	}
	
	public void startFight()
	{
		 Terrain t = new Terrain();
	    t.setsTerrianElement(Elements.getElement(Elements.Name.ICE));
	    ArrayList<Action> actionsToPreform = new ArrayList<Action>();
//	    allFighters.get(0).setTerrain(t);
//	    allFighters.get(1).setTerrain(t);
	    while (allFighters.size() > 1) {
	      //asks players for what actions to preform
	      for (int i = 0; i < allFighters.size(); i++) {
	        Entity currentPlayer = allFighters.get(i);
	        for(Action a: currentPlayer.onTurn(allFighters, input)){
	          actionsToPreform.add(a);
	        }
	        
	      }
	      
	      //executes actionsToPreform
	      for (Action q : actionsToPreform) {
	        q.performAction(log, this);
	        System.out.println();
	      }
	      
	      //checks for dead people
	      for (int i = allFighters.size() - 1; i >= 0; i--) {
	        if (allFighters.get(i).isHealthZero(log, this)) {
	          Entity target = allFighters.remove(i);
	          System.out.println(target.getName() + " was eliminated. " + target.toString());
	        }
	      }
	      //prints whats happened
	      printLog(log);
	      log.clear();
	      actionsToPreform.clear();
	      Collections.sort(allFighters);
	      Collections.reverse(allFighters);
	      System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
	    }
	    // scanInput.close();
	    // System.out.println(allFighters.get(0).getHealth() + ", " +
	    // allFighters.get(1).getHealth() + ", " + allFighters.get(2).getHealth());
	    System.out.println(allFighters.get(0).getName() + " won!");

	}

	public ArrayList<Entity> getFighters() {
		// TODO Auto-generated method stub
		return allFighters;
	}

	protected void printLog(BattleLog log)
	{
		
	}

}
