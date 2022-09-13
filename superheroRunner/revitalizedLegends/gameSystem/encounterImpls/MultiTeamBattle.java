package revitalizedLegends.gameSystem.encounterImpls;

import java.util.ArrayList;
import org.json.JSONObject;

import revitalizedLegends.gameSystem.Encounter;

public class MultiTeamBattle extends Encounter
{
    
	public MultiTeamBattle(JSONObject json)
	{
		super(json);
	}
	
	public MultiTeamBattle(ArrayList<ArrayList<String>> members, String name)
	{
		super(name);
		ArrayList<Encounter.ProposedTeam> teams = new ArrayList<>();
		for (int i = 0; i < members.size(); i++)
		{
            ArrayList<String> currentTeam = members.get(i);
            Encounter.ProposedTeam singleTeam = new Encounter.ProposedTeam();
            for (int j = 0; j < currentTeam.size(); j++)
            {
    			singleTeam.addMember(currentTeam.get(i));                
            }
			teams.add(singleTeam);
		}
		this.setTeams(teams);
	}

	@Override
	public JSONObject toJson() {
		JSONObject json = super.toJson();
		json.put(TYPE_KEY, EncounterLoader.MULTI_TEAM);
		return json;
	}
}