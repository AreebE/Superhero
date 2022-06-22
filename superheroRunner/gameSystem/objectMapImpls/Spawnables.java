package gameSystem.objectMapImpls;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;

import org.json.JSONArray;

import gameSystem.EntityInfoItem;
import gameSystem.infoItemImpls.BaseAIInfoItem;
import gameSystem.infoItemImpls.ControllableAutoEntityInfoItem;
import gameSystem.infoItemImpls.MoveItem;

public final class Spawnables
{
    private Spawnables(){
    }

    public static enum Name
    {
        CRYSTAL,
        SQUIRREL,
        BABA,
        THE_GOBLIN
    }

    private final static EnumMap<Name, EntityInfoItem> ENTITIES = new EnumMap<Name, EntityInfoItem>(Name.class)
    {{
        System.out.println("Created spawnables");
        put
        (
            Name.CRYSTAL,
            new ControllableAutoEntityInfoItem
            (
                "Crystal",
                1,
                new ArrayList<String>()
                {{
                    add("laser");
                }},
                new ArrayList<String>()
                {{
                    add("curse");

                }},
                new ArrayList<String>()
                {{

                }},
                "relaxed",
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
            new ControllableAutoEntityInfoItem
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
                "relaxed",
                1,
                0,
                new ArrayList<String>()
                {{
                    add("scratch");
                }},
                true
            )
        );

        put
        (
            Name.BABA,
            new BaseAIInfoItem
            (
                "Baba",
                101,
                new ArrayList<String>()
                {{
                    
                }},
                new ArrayList<String>()
                {{

                }},
                new ArrayList<String>()
                {{
                    
                }},
                "relaxed",
                400,
                400,
                new ArrayList<MoveItem>()
                {{
                    add(new MoveItem(0, MoveItem.POSITIVE, "test"));
                }}
            )
        );

        put
        (
            Name.THE_GOBLIN,
            new BaseAIInfoItem
            (
                "The Goblin",
                100,
                new ArrayList<String>()
                {{
                    add("bash");
                    add("pebble shot");
                    add("rally");
                }},
                new ArrayList<String>()
                {{
                }},
                new ArrayList<String>()
                {{
                }},
                "relaxed",
                100,
                100,
                new ArrayList<MoveItem>()
                {{
                    add(new MoveItem(0, MoveItem.NEGATIVE, "bash"));
                    add(new MoveItem(0, MoveItem.POSITIVE, "rally"));
                    add(new MoveItem(0, MoveItem.NEGATIVE, "bash"));
                    add(new MoveItem(0, MoveItem.NEGATIVE, "pebble shot"));
                    add(new MoveItem(0, MoveItem.NEGATIVE, "bash"));
                    add(new MoveItem(0, MoveItem.POSITIVE, "rally"));
                }}
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
    public static EntityInfoItem get(Name name)
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