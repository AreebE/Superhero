package battlesystem;

import java.util.EnumMap;

public final class States
{
    private States(){}

    public static enum Name
    {
        NORMAL,
        PARALYZED
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
    }};

    public static State get(Name name)
    {
        return STATES.get(name);
    }
}
