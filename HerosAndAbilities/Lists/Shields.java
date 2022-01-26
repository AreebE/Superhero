package battlesystem;

import java.util.EnumMap;
import java.util.List;

public final class Shields 
{
    
    private Shields()
    {

    }

    public static enum Name
    {
        COUNTER,
        SELF_DESTRUCT,
        SELF_CARE,
        WITCH_CURSE,
        DEATH_DEFIANCE,
        ILLUSION,
        GIFT
    }

    public static enum Trigger {
        ATTACK,
        SHIELD_BREAK,
        DEATH,
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
                Effects.getEffect(Effects.Name.RETALIATE),
                true,
                1,
                Name.COUNTER,
                new Trigger[] {Trigger.ATTACK},
                new Elements.Name[] {Elements.Name.ALL}
            ) 
        );

        put
        (
            Name.ILLUSION, 
            new TrapShield
            (
                "Illusion",
                "Redirects the person's attack",
                2,
                Effects.getEffect(Effects.Name.ILLUSIONARY_BLAST),
                true,
                1,
                Name.ILLUSION,
                new Trigger[] {Trigger.ATTACK},
                new Elements.Name[] {Elements.Name.ALL}
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
                Effects.getEffect(Effects.Name.INSTANT_HEAL),
                false,
                -1,
                Name.SELF_CARE,
                new Trigger[] {Trigger.ATTACK},
                new Elements.Name[] {Elements.Name.ALL}
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
                Effects.getEffect(Effects.Name.EXPLODE),
                Effects.getEffect(Effects.Name.EXPLODE),
                false,
                1,
                Name.SELF_DESTRUCT,
                new Trigger[] {Trigger.SHIELD_BREAK},
                new Elements.Name[] {Elements.Name.ALL}
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
                Effects.getEffect(Effects.Name.REGEN),
                Effects.getEffect(Effects.Name.CURSE),
                false,
                1,
                Name.WITCH_CURSE,
                new Trigger[] {Trigger.ATTACK},
                new Elements.Name[] {Elements.Name.ALL}
            )
        );

        put
        (
            Name.GIFT,
            new DeathShield 
            (
                "Gift",
                "Give the creator some extra stats",
                -1,
                Effects.getEffect(Effects.Name.SACRIFICIAL_GIFT),
                false,
                1,
                Name.GIFT
            )
        );

        put
        (
            Name.DEATH_DEFIANCE,
            new SelfDeathShield 
            (
                "Deathly Defiance",
                "Defy death",
                -1,
                Effects.getEffect(Effects.Name.RESURRECT),
                false,
                2,
                Name.DEATH_DEFIANCE
            )
        );
    }};

    public static Shield getShield(Name name){
        return SHIELDS.get(name);
    }

    public static void giveShields(
        Entity e,
        List<Name> shields)
    {
        if (shields == null)
        {
            return;
        }
        for (Name n: shields)
        {
            e.addShield(getShield(n).copy());
        }
    }
}