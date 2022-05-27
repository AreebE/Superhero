package battlesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import battlesystem.battlelogImpls.StringBattleLog;

public abstract class Game {
	

	int countOfSpawn = 0;
	
	public enum Type
	{
		ABILITY,
		EFFECT,
		SPAWNABLE,
		SHIELD,
		STATE
	}
	
	public static class Team
	{
		private ArrayList<Entity> members;
		private final int id;
	
		public Team(int id)
		{
			this.id = id;
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
		
		protected void removeMember(Entity member)
		{
			members.remove(member);
		}
		
		protected int getNumberOfMembers()
		{
			return members.size();
		}

        public String getAllMembers()
        {
            StringBuilder memberString = new StringBuilder();
            for (int i = 0; i < members.size(); i++)
            {
                memberString.append(members.get(i).getName());
                if (i == members.size() - 2)
                {
                    memberString.append(", and");
                }
                else if (i < members.size() - 1)
                {
                    memberString.append(", ");
                }
            }
            return memberString.toString();
        }
        
		@Override
		public String toString()
		{
			StringBuilder list = new StringBuilder();
			for (int i = 0; i < members.size(); i++)
			{
				list.append("* ")
					.append(members.get(i).getName())
					.append("\n");
			}
			return list.toString();
		}
		
		public int getID()
		{
			return id;
		}
	}
	
	
	private InputSystem input;
	
	private ArrayList<Entity> allFighters;
	private Storage s;
	private ArrayList<Team> teams;
	private BattleLog log;
	
	public Game(
			Encounter encounter,
			Storage s,
			BattleLog log,
			EntityInfoItem protagonist
	)
	{
		ArrayList<Encounter.ProposedTeam> propositions = encounter.getTeams();
		this.teams = new ArrayList<>();
		this.s = s;
		this.allFighters = new ArrayList<>();
		this.log = log;
		for (int i = 0; i < propositions.size(); i++)
		{
			ArrayList<String> names = propositions.get(i).getNames();
			Team team = new Team(i);
			teams.add(team);
			for (int j = 0; j < names.size(); j++)
			{
				String currentName = names.get(j);
				if (currentName.equals(Encounter.PROTAGONIST))
				{
                    EntityInfoItem item = protagonist.create(null, this);
                    this.addMember(e, team.getID());
				}
				else 
				{
                    EntityInfoItem item = s.getEntity(currentName.toLowerCase());
                    if (item == null)
                    {
                        item = s.getSpawnable(currentName.toLowerCase());
                    }
					Entity e = item.create(null, this);
					this.addMember(e, team.getID());
				}
			}
		}
	}
	
	public Game(
			ArrayList<EntityInfoItem> initialFighters,
			Storage s,
			BattleLog log)
	{
		this.s = s;
		this.input = input;
		this.teams = new ArrayList<>();
		this.allFighters = new ArrayList<>();
		this.log = log;
		for (int i = 0; i < initialFighters.size(); i++)
		{
//			System.out.println(initialFighters.get(i).defaultState);
			Entity e = initialFighters.get(i).create(null, this);
//			System.out.println("E");
			Team t = new Team(i);
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
		return s.getAbility(name.toLowerCase()).copy();
	}
	
	public EntityInfoItem getSpawnable(String name)
	{
		return s.getSpawnable(name.toLowerCase());
	}
	
	public Effect getEffect(String name)
	{
        System.out.println(name);
		return s.getEffect(name.toLowerCase()).copy();
	}
	
	public Shield getShield(String name)
	{
//		System.out.println(name);
		return s.getShield(name.toLowerCase()).copy();
	}
	
	public State getState(String name)
	{
		return s.getState(name.toLowerCase()).copy();
	}


	public void addMember(Entity created, int teamToJoin)
	{
		teams.get(teamToJoin).addMember(created);
		created.setTeamID(teamToJoin);
		for (int i = 0; i < allFighters.size(); i++)
		{
			if (allFighters.get(i).getName().equals(created.getName()))
			{
				created.appendNumber(countOfSpawn);
				countOfSpawn++;
			}
		}
		allFighters.add(created);
	}
	
	public void startFight()
	{
		 Terrain t = new Terrain();
	    t.setsTerrianElement(Elements.getElement(Elements.Name.ICE));
	    ArrayList<Action> actionsToPreform = new ArrayList<Action>();
//	    allFighters.get(0).setTerrain(t);
	    Collections.sort(allFighters);
	      Collections.reverse(allFighters);
//	    allFighters.get(1).setTerrain(t);
	    
	      while (teams.size() > 1) {
	      //asks players for what actions to preform
	    		System.out.println("Current Teams:\n");
		    	for (int i = 0; i < teams.size(); i++)
		    	{
		    		System.out.println(teams.get(i));
		    	}
	    	
			    for (int i = 0; i < allFighters.size(); i++) {
			       Entity currentPlayer = allFighters.get(i);
			       for(Action a: currentPlayer.onTurn(allFighters, input)){
			          actionsToPreform.add(a);
			       }
			        
			     }
			      
			    System.out.println("e" + allFighters.size());
	      //executes actionsToPreform
		      for (Action q : actionsToPreform) {
		        q.performAction(log, this);
		        System.out.println();
		      }
	      
	      //checks for dead people
	      for (int i = allFighters.size() - 1; i >= 0; i--) {
	        if (allFighters.get(i).isHealthZero(log, this)) {
	          Entity target = allFighters.remove(i);
	          Team targetTeam = teams.get(target.getTeamID());
	          targetTeam.removeMember(target);
	          if (targetTeam.getNumberOfMembers() == 0)
	          {
	        	  teams.remove(targetTeam);
	          }
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
	    System.out.println(teams.get(0).getAllMembers() + " won!");

	}

	public ArrayList<Entity> getFighters() {
		// TODO Auto-generated method stub
		return allFighters;
	}

	protected abstract void printLog(BattleLog log);

}
