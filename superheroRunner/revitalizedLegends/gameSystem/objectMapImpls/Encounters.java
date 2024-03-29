package revitalizedLegends.gameSystem.objectMapImpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;

import revitalizedLegends.gameSystem.Encounter;
import revitalizedLegends.gameSystem.encounterImpls.FreeForAll;
import revitalizedLegends.gameSystem.encounterImpls.OneVAll;
import revitalizedLegends.gameSystem.objectMapImpls.Effects.Name;

public class Encounters {
	
	private static final HashMap<String, Encounter> ENCOUNTERS = new HashMap<String, Encounter>()
			{{
                String title = "beta";
				ArrayList<String> firstEnemyEncounter = new ArrayList<>();
				firstEnemyEncounter.add("BeepBoop");
				firstEnemyEncounter.add("EEEEEE");
				firstEnemyEncounter.add("TestSubject");
				this.put(title, new OneVAll(firstEnemyEncounter, title));

                title = "beta free";
				ArrayList<String> firstFreeForAll = new ArrayList<>();
				firstFreeForAll.add("BeepBoop");
				firstFreeForAll.add("EEEEEE");
				firstFreeForAll.add("TestSubject");
				firstFreeForAll.add("SecondestSubject");
				this.put(title, new FreeForAll(firstFreeForAll, title));
				
				ArrayList<String> duel = new ArrayList<>();
                duel.add("The Goblin");
                duel.add("Joe");
                title = "duel";
                this.put(title, new FreeForAll(duel, title));

			}};
			
	public static JSONArray saveEncounters()
	{
		JSONArray json = new JSONArray();
    	Iterator<String> encounters = ENCOUNTERS.keySet().iterator();
    	while (encounters.hasNext())
    	{
    		json.put(ENCOUNTERS.get(encounters.next()).toJson());
    	}
		return json;
	}
}
