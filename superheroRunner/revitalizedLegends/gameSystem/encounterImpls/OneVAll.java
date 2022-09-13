package revitalizedLegends.gameSystem.encounterImpls;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import revitalizedLegends.gameSystem.Encounter;
import revitalizedLegends.gameSystem.Saveable;

public class OneVAll extends Encounter
{
	
	public OneVAll(JSONObject json)
	{
		super(json);
	}
	
	public OneVAll(ArrayList<String> enemies, String name)
	{
		super(name);
		ArrayList<Encounter.ProposedTeam> teams = new ArrayList<>();
		Encounter.ProposedTeam opposers = new Encounter.ProposedTeam();
		for (int i = 0; i < enemies.size(); i++)
		{
			opposers.addMember(enemies.get(i));
		}
		teams.add(opposers);
		
		Encounter.ProposedTeam protag = new Encounter.ProposedTeam(); 
		protag.addMember(Encounter.PROTAGONIST);
		teams.add(protag);
		
		super.setTeams(teams);
	}

	@Override
	public JSONObject toJson() {
		JSONObject json = super.toJson();
		json.put(TYPE_KEY, EncounterLoader.ONE_V_ALL);
		return json;
	}

}
