import java.util.EnumMap;

public class SheildList 
{
    
    private SheildList()
    {

    }

    public static enum Name
    {
        COUNTER
    }

    public static enum Trigger {
        ATTACK,
        SHEILD_BREAK,
        ALL
    }

    private static EnumMap<Name, Sheild> SHEILDS = new EnumMap<>(Name.class){{
        put
        (
            Name.COUNTER, 
            new Sheild
            (
                "Counter",
                "Deal some damage when struck",
                3,
                EffectList.getEffect(EffectList.Name.RETALIATE),
                true,
                1,
                new Trigger[] {Trigger.ATTACK},
                new ElementList.Name[] {ElementList.Name.ALL}
            ) 
        );
    }};

    public static Sheild getSheild(Name name){
        return SHEILDS.get(name);
    }

}