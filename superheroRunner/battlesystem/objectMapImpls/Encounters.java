package battlesystem.objectMapImpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;

import battlesystem.Encounter;
import battlesystem.encounterImpls.FreeForAll;
import battlesystem.encounterImpls.OneVAll;
import battlesystem.objectMapImpls.Effects.Name;

public class Encounters {
	
	private static final HashMap<String, Encounter> ENCOUNTERS = new HashMap<String, Encounter>()
			{{
				ArrayList<String> firstEnemyEncounter = new ArrayList<>();
				firstEnemyEncounter.add("BeepBoop");
				firstEnemyEncounter.add("EEEEEE");
				firstEnemyEncounter.add("TestSubject");
				this.put("beta", new OneVAll(firstEnemyEncounter, "beta"));
				
				ArrayList<String> firstFreeForAll = new ArrayList<>();
				firstFreeForAll.add("BeepBoop");
				firstFreeForAll.add("EEEEEE");
				firstFreeForAll.add("TestSubject");
				firstFreeForAll.add("SecondestSubject");
				this.put("beta free", new FreeForAll(firstFreeForAll, "beta free"));
				
				
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
