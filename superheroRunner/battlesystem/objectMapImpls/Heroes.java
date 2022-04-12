package battlesystem.objectMapImpls;

import java.util.EnumMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import battlesystem.Entity;
import battlesystem.EntityInfoItem;
import battlesystem.objectMapImpls.Effects.Name;

import java.util.ArrayList;

public final class Heroes {
    private Heroes(){}
    

    public static enum Name{
        BEEP_BOOP,
        JOE,
        EEEEEE,
        TEST_SUBJECT,
        SOLARIS,
        SECOND_TEST
    }

    private final static EnumMap<Name, EntityInfoItem> HEROS = new EnumMap<Name, EntityInfoItem>(Name.class)
    {{
        put
        (
            Name.BEEP_BOOP,
            new EntityInfoItem
            (
                "BeepBoop",
                30,
                new ArrayList<String>()
                {{
                    add("lightning strike");
                    add("counterstrike");
                    add("flare up");
                    add("construct");
                    add("witch spell");
                    add("first aid");
                }},
                new ArrayList<String>()
                {{
                    add("exponential shield");
                }},
                new ArrayList<String>()
                {{
                    // add(Shields.Name.COUNTER);
                }},
                "relaxed",
                100,
                40
            )  
        );

        put
        (
            Name.JOE,
            new EntityInfoItem
            (
                "Joe",
                60,
                new ArrayList<String>()
                {{
                    add("snowball");
                    add("protect");
                    add("defense up");
                    add("counter");
                    add("wide slash");
                }},
                new ArrayList<String>()
                {{
                    add("curse");
                }},
                new ArrayList<String>()
                {{
                    add("deathly defiance");
                }},
                "relaxed",
                100,
                40
            )  
        );

        put
        (
            Name.EEEEEE,
            new EntityInfoItem
            (
                "EEEEEE",
                100,
                new ArrayList<String>()
                {{
                    add("summon squirrel");
                    add("summon golem");
                    add("fireball");
                    add("protect");
                    add("attack up");
                    add("stun spore");
                }},
                new ArrayList<String>()
                {{
                    // add(Effects.Name.);
                }},
                new ArrayList<String>()
                {{
                    // add(Shields.Name.);
                }},
                "relaxed",
                100,
                40
            )  
        );

        put
        (
            Name.TEST_SUBJECT,
            new EntityInfoItem
            (
                "TestSubject",
                200,
                new ArrayList<String>()
                {{
                    add("earthquake");
                    add("illusion spell");
                    add("telekinesis");
                    // add(Abilities.Name.PROTECT);
                    // add(Abilities.Name.ATTACK_UP);
                    // add(Abilities.Name.STUN_SPORE);
                }},
                new ArrayList<String>()
                {{
                    // add(Effects.Name.);
                }},
                new ArrayList<String>()
                {{
                    add("illusion");
                }},
                "relaxed",
                100,
                40
            )  
        );

         put
        (
            Name.SECOND_TEST,
            new EntityInfoItem
            (
                "SecondestSubject",
                300,
                new ArrayList<String>()
                {{
                    add("poison slash");
                    add("illusion spell");
                    add("telekinesis");
                    add("earthquake");
                    add("defensive stance");
                    add("pray");
                    // add(Abilities.Name.STUN_SPORE);
                }},
                new ArrayList<String>()
                {{
                    // add(Effects.Name.);
                }},
                new ArrayList<String>()
                {{
                    add("illusion");
                }},
                "relaxed",
                100,
                40
            )  
        );

        /*
        Abilities.Name.FIREBALL,
        Abilities.Name.PROTECT,
        Abilities.Name.ATTACK_UP
        */

        /* Template
        put
        (
            Name.,
            new EntityInfoItem
            (
                "",
                30,
                new ArrayList<Abilities.Name>
                {{
                    add(Abilities.);
                }},
                new ArrayList<Effects.Name>
                {{
                    add(Effects.);
                }},
                new ArrayList<Shields.Name>
                {{
                    add(Shields.);
                }},
                100,
                40
            )  
        );
        */
       
        
    }};

    public static EntityInfoItem get(Name name, Entity creator)
    {
        return HEROS.get(name);
    }
    
    public static JSONArray loadHeroes()
    {
    	JSONArray json = new JSONArray();
    	Iterator<Name> heros = HEROS.keySet().iterator();
    	while (heros.hasNext())
    	{
    		JSONObject hero = HEROS.get(heros.next()).toJson();
    		json.put(hero);
//    		System.out.println(hero.toString());
    		
    	}
		return json;
    }

    
}