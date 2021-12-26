import java.util.EnumMap;
import java.util.ArrayList;

public class SpawnableList
{
    private SpawnableList(){}

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
                10,
                0, 
                false,
                new ArrayList<>()
                {{
                    add(AbilityList.Name.LASER);
                }}
            )
        );

        put
        (
            Name.SQUIRREL,
            new AIInfoItem
            (
                "Squirrel",
                1,
                1,
                0, 
                true,
                new ArrayList<>()
                {{
                    add(AbilityList.Name.SCRATCH);
                }}
            )
        );
    }};

    public static AIInfoItem getEntityInfo(Name name)
    {
        return ENTITIES.get(name);
    }
}