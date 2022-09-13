package revitalizedLegends.gameSystem.objectMapImpls;

import java.util.EnumMap;
import java.util.Iterator;

import org.json.JSONArray;

import revitalizedLegends.gameSystem.State;
import revitalizedLegends.gameSystem.objectMapImpls.Shields.Name;
import revitalizedLegends.gameSystem.stateImpls.NormalState;
import revitalizedLegends.gameSystem.stateImpls.StunState;
import revitalizedLegends.gameSystem.stateImpls.VigorState;

public final class States
{
    private States(){}

    public static enum Name
    {
        NORMAL,
        PARALYZED,
        ENERGIZED
    }

    private static final EnumMap<Name, State> STATES = new EnumMap<Name, State>(Name.class)
    {{
        put
        (
            Name.NORMAL,
            new NormalState
            (
                "Relaxed",
                "The person isn't suffering from anything",
                -1
            )
        );

        put
        (
            Name.PARALYZED,
            new StunState 
            (
                "Paralyzed",
                "The person is in shock and unable to move",
                3
            )
        );

        put
        (
            Name.ENERGIZED,
            new VigorState 
            (
                "Energized",
                "The person becomes more energized and can perform more actions per turn",
                3,
                2
            )
        );
    }};

    public static State get(Name name)
    {
        return STATES.get(name);
    }
    
    public static JSONArray loadStates()
    {
    	JSONArray json = new JSONArray();
    	Iterator<Name> states = STATES.keySet().iterator();
    	while (states.hasNext())
    	{
    		json.put(STATES.get(states.next()).toJson());
    	}
		return json;
    }
}
