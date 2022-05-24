package battlesystem;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import battlesystem.encounterImpls.EncounterLoader;

public abstract class Encounter 
	implements Saveable {
	
	public Encounter(JSONObject json)
	{
		JSONArray teamLayout = json.getJSONArray(TEAM_LAYOUT);
		ArrayList<ProposedTeam> teams = new ArrayList<>();
		for (int i = 0; i < teamLayout.length(); i++)
		{
			ProposedTeam currentTeam = new ProposedTeam();
			JSONArray currentTeamJSON = teamLayout.getJSONArray(i);
			for (int j = 0; j < currentTeamJSON.length(); j++)
			{
				currentTeam.addMember(currentTeamJSON.getString(j));				
			}
			teams.add(currentTeam);
		}
		
		this.name = json.getString(NAME_KEY);
		this.setTeams(teams);
	}
	
	public Encounter(String name)
	{
		this.name = name;
	}
	
	public static final String NAME_KEY = "name";
	public static final String TYPE_KEY = "type";
	public static final String TEAM_LAYOUT = "team layout";
	public static final String PROTAGONIST = "protagonist";
	
	public static class ProposedTeam 
	{
		private ArrayList<String> memberNames;
		
		public ProposedTeam()
		{
			memberNames = new ArrayList<>();
		}
		
		public void addMember(String name)
		{
			memberNames.add(name);
		}
		
		public ArrayList<String> getNames()
		{
			return memberNames;
		}
	}
	
	private String name;
	private ArrayList<ProposedTeam> teams;
	
	public void setTeams(ArrayList<ProposedTeam> teams)
	{
		this.teams = teams;
	}
	
	public ArrayList<ProposedTeam> getTeams()
	{
		return teams;
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
	public JSONObject toJson()
	{
		JSONObject json = new JSONObject();
		json.put(NAME_KEY, name);
		JSONArray teamLayout = new JSONArray();
		for (int i = 0; i < teams.size(); i++)
		{
			ArrayList<String> currentTeam = teams.get(i).getNames();
			JSONArray currentTeamJSON = new JSONArray();
			for (int j = 0; j < currentTeam.size(); j++)
			{
				currentTeamJSON.put(currentTeam.get(j));
			}
			teamLayout.put(currentTeamJSON);
		}
		json.put(TEAM_LAYOUT, teamLayout);
		return json;
	}
}
