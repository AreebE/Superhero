import java.util.EnumMap;

public class ShieldList 
{
    
    private ShieldList()
    {

    }

    public static enum Name
    {
        COUNTER,
        SELF_DESTRUCT,
        SELF_CARE,
        WITCH_CURSE
    }

    public static enum Trigger {
        ATTACK,
        SHIELD_BREAK,
        ALL
    }

    private static EnumMap<Name, Shield> SHIELDS = new EnumMap<>(Name.class){{
        put
        (
            Name.COUNTER, 
            new TrapShield
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

        put
        (
            Name.SELF_CARE, 
            new SelfShield
            (
                "Self care",
                "Heal from some damage when struck.",
                3,
                EffectList.getEffect(EffectList.Name.INSTANT_HEAL),
                false,
                -1,
                new Trigger[] {Trigger.ATTACK},
                new ElementList.Name[] {ElementList.Name.ALL}
            ) 
        );

        put 
        (
            Name.SELF_DESTRUCT,
            new DualShield 
            (
                "Self destruct",
                "Deals damage to both the user and the attacker once the shield breaks",
                5,
                EffectList.getEffect(EffectList.Name.EXPLODE),
                EffectList.getEffect(EffectList.Name.EXPLODE),
                false,
                1,
                new Trigger[] {Trigger.SHIELD_BREAK},
                new ElementList.Name[] {ElementList.Name.ALL}
            )
        );

        put 
        (
            Name.WITCH_CURSE,
            new DualShield 
            (
                "Witch's Curse",
                "The enemy will be cursed for attacking the user, who will start healing some health",
                -1,
                EffectList.getEffect(EffectList.Name.REGEN),
                EffectList.getEffect(EffectList.Name.CURSE),
                false,
                1,
                new Trigger[] {Trigger.ATTACK},
                new ElementList.Name[] {ElementList.Name.ALL}
            )
        );
    }};

    public static Shield getShield(Name name){
        return SHIELDS.get(name).copy();
    }

}