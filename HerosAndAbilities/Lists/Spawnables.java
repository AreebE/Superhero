package battlesystem;

import java.util.EnumMap;
import java.util.ArrayList;

public final class Spawnables
{
    private Spawnables(){}

    public static enum Name
    {
        CRYSTAL,
        SQUIRREL
    }

    private final static EnumMap<Name, AIInfoItem> ENTITIES = new EnumMap<>(Name.class)
    {{
        put
        (
            Name.CRYSTAL,
            new AIInfoItem
            (
                "Crystal Golem",
                1,
                new ArrayList<Abilities.Name>()
                {{
                    add(Abilities.Name.LASER);
                }},
                new ArrayList<Effects.Name>()
                {{
                    add(Effects.Name.CURSE);
                }},
                null,
                10,
                10,
                new ArrayList<Abilities.Name>()
                {{
                    add(Abilities.Name.LASER);
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
                new ArrayList<Abilities.Name>()
                {{
                    add(Abilities.Name.SCRATCH);
                }},
                null,
                new ArrayList<Shields.Name>()
                {{
                    add(Shields.Name.GIFT);
                }},
                1,
                0,
                new ArrayList<Abilities.Name>()
                {{
                    add(Abilities.Name.SCRATCH);
                }},
                true
            )
        );

        /*
            String name,
            int speed,
            ArrayList<Abilities.Name> abilityNames,
            ArrayList<Effects.Name> startingEffects,
            ArrayList<Shields.Name> startingShields,
            int maxHealth,
            int shieldHealth,
            ArrayList<Abilities.Name> attackPattern,
            boolean isTargettable*/
    }};

    public static AIInfoItem getEntityInfo(Name name)
    {
        return ENTITIES.get(name);
    }
}