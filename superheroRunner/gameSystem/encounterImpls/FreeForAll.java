package gameSystem.encounterImpls;

import java.util.ArrayList;

import org.json.JSONObject;

import gameSystem.Encounter;

public class FreeForAll extends Encounter {
	
	public FreeForAll(JSONObject json)
	{
		super(json);
	}
	
	public FreeForAll(ArrayList<String> members, String name)
	{
		super(name);
		ArrayList<Encounter.ProposedTeam> teams = new ArrayList<>();
		for (int i = 0; i < members.size(); i++)
		{
			Encounter.ProposedTeam singleCompetor = new Encounter.ProposedTeam();
			singleCompetor.addMember(members.get(i));
			teams.add(singleCompetor);
		}
		this.setTeams(teams);
	}

	@Override
	public JSONObject toJson() {
		JSONObject json = super.toJson();
		json.put(TYPE_KEY, EncounterLoader.FREE_FOR_ALL);
		return json;
	}
}
