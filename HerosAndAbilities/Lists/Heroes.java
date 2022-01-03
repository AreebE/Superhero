package battlesystem;

import java.util.EnumMap;
import java.util.ArrayList;

public final class Heroes {
    private Heroes(){}

    public static enum Name{
        BEEP_BOOP,
        JOE,
        EEEEEE
    }

    private final static EnumMap<Name, EntityInfoItem> HEROS = new EnumMap<>(Name.class)
    {{
        put
        (
            Name.BEEP_BOOP,
            new EntityInfoItem
            (
                "BeepBoop",
                30,
                new ArrayList<Abilities.Name>()
                {{
                    add(Abilities.Name.LIGHTNING_STRIKE);
                    add(Abilities.Name.COUNTERSTRIKE);
                    add(Abilities.Name.FLARE_UP);
                    add(Abilities.Name.CONSTRUCT);
                    add(Abilities.Name.WITCH_SPELL);
                    add(Abilities.Name.FIRST_AID);
                }},
                new ArrayList<Effects.Name>()
                {{
                    // add(Effects.Name.CHARGE);
                }},
                new ArrayList<Shields.Name>()
                {{
                    // add(Shields.Name.COUNTER);
                }},
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
                new ArrayList<Abilities.Name>()
                {{
                    add(Abilities.Name.SNOWBALL);
                    add(Abilities.Name.PROTECT);
                    add(Abilities.Name.DEFENSE_UP);
                    add(Abilities.Name.COUNTER);
                    add(Abilities.Name.WIDE_SLASH);
                }},
                new ArrayList<Effects.Name>()
                {{
                    add(Effects.Name.CURSE);
                }},
                new ArrayList<Shields.Name>()
                {{
                    // add(Shields.Name.);
                }},
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
                new ArrayList<Abilities.Name>()
                {{
                    add(Abilities.Name.SUMMON_SQUIRREL);
                    add(Abilities.Name.SUMMON_GOLEM);
                    add(Abilities.Name.FIREBALL);
                    add(Abilities.Name.PROTECT);
                    add(Abilities.Name.ATTACK_UP);
                }},
                new ArrayList<Effects.Name>()
                {{
                    // add(Effects.Name.);
                }},
                new ArrayList<Shields.Name>()
                {{
                    // add(Shields.Name.);
                }},
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
        /*
        String name,
        int speed,
        ArrayList<Abilities.Name> abilityNames,
        ArrayList<Effects.Name> startingEffects,
        ArrayList<Shields.Name> startingShields,
        int maxHealth,
        int shieldHealth
        */
    }};

    public static Entity getHero(Name name, Entity creator)
    {
        return HEROS.get(name).create(creator);
    }
}