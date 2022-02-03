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
                "Crystal",
                1,
                new ArrayList<String>()
                {{
                    add("laser");
                }},
                new ArrayList<Effects.Name>()
                {{
                    add(Effects.Name.CURSE);
                }},
                null,
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
                null,
                new ArrayList<Shields.Name>()
                {{
                    add(Shields.Name.GIFT);
                }},
                1,
                0,
                new ArrayList<String>()
                {{
                    add("scratch");
                }},
                true
            )
        );

        /*
            String name,
            int speed,
            ArrayList<String> abilityNames,
            ArrayList<Effects.Name> startingEffects,
            ArrayList<Shields.Name> startingShields,
            int maxHealth,
            int shieldHealth,
            ArrayList<String> attackPattern,
            boolean isTargettable*/
    }};

    public static AIInfoItem getEntityInfo(Name name)
    {
        return ENTITIES.get(name);
    }
}