package battlesystem.objectMapImpls;

import java.util.EnumMap;

import battlesystem.State;
import battlesystem.stateImpls.NormalState;
import battlesystem.stateImpls.StunState;
import battlesystem.stateImpls.VigorState;

public final class States
{
    private States(){}

    public static enum Name
    {
        NORMAL,
        PARALYZED,
        ENERGIZED
    }

    private static final EnumMap<Name, State> STATES = new EnumMap(Name.class)
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
}
