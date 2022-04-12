package battlesystem.objectMapImpls;

import java.util.EnumMap;
import java.util.Iterator;

import org.json.JSONArray;

import battlesystem.infoItemImpls.AIInfoItem;
import battlesystem.objectMapImpls.Shields.Name;

import java.util.ArrayList;

public final class Spawnables
{
    private Spawnables(){
    }

    public static enum Name
    {
        CRYSTAL,
        SQUIRREL
    }

    private final static EnumMap<Name, AIInfoItem> ENTITIES = new EnumMap<Name, AIInfoItem>(Name.class)
    {{
        System.out.println("Created spawnables");
        put
        (
            Name.CRYSTAL,
            new AIInfoItem
            (
                "Crystal",
                1,
                new ArrayList<String>()
                {{
                    add("laser");
                }},
                new ArrayList<String>()
                {{
                	
                }},
                new ArrayList<String>()
                {{
                    add("curse");
                }},
                "normal",
                10,
                10,
                new ArrayList<String>()
                {{
                    add("laser");
                }},
                true
            )
        );

        put
        (
            Name.SQUIRREL,
            new AIInfoItem
            (
                "Squirrel",
                1,
                new ArrayList<String>()
                {{
                    add("scratch");
                }},
                new ArrayList<String>()
                {{
                	
                }},
                new ArrayList<String>()
                {{
                    add("gift");
                }},
                "normal",
                1,
                0,
                new ArrayList<String>()
                {{
                    add("scratch");
                }},
                true
            )
        );
//        /*
//            String name,
//            int speed,
//            ArrayList<String> abilityNames,
//            ArrayList<Effects.Name> startingEffects,
//            ArrayList<Shields.Name> startingShields,
//            int maxHealth,
//            int shieldHealth,
//            ArrayList<String> attackPattern,
//            boolean isTargettable*/
    }};
//
    public static AIInfoItem get(Name name)
    {
        System.out.println(ENTITIES);
        return ENTITIES.get(name);
    }
    
    public static JSONArray loadSpawnables()
    {
    	JSONArray json = new JSONArray();
    	Iterator<Name> spawnables = ENTITIES.keySet().iterator();
    	while (spawnables.hasNext())
    	{
    		json.put(ENTITIES.get(spawnables.next()).toJson());
    	}
		return json;
    }
}