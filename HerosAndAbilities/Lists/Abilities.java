package battlesystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.List;
import java.util.HashSet;

public final class Abilities 
{
    // Prevents any instances of this class from being made

    //private ArrayList<Element> elements;

    private Abilities(){}

    public static enum Modifier
    {
        RANDOM,
        RECOIL,
        MULTICAST,
        PERCENTAGE,
        GROUP
    }

    public static enum Type
    {
        ATTACK, 
        DEFENSE, 
        SUPPORT
    }

    public static enum Name 
    {
        // Attack 
        WIDE_SLASH,
        RAM_ATTACK,
        FIREBALL, 
        SNOWBALL, 
        LIGHTNING_STRIKE, 
        PASS_TURN,
        POISON_SLASH,
        LASER,
        SCRATCH,
        GROUND_SUCTION,

        // Defense 
        COUNTER,
        WITCH_SPELL,
        WARNING,
        FIRST_AID,
        SUMMON_SQUIRREL,
        SUMMON_GOLEM,

        // Support 
        HEAL_PULSE,
        PROTECT,
        COUNTERSTRIKE,
        ATTACK_UP,
        DEFENSE_UP,
        FLARE_UP,
        BURN_UP,
        POISON,
        SACRIFICE,
        DEFENSIVE_STANCE,
        CONSTRUCT,
        PRAY,
        SPORE,

        // Etc.
        CUSTOM
    }
    public static final int MAX_CHANCE = 256;
    
    private static int abilityID = 0;

    private static HashMap<String, Ability> customAbilities = new HashMap<>();

    private final static EnumMap<Name, Ability> ABILITIES = new EnumMap<>(Name.class)
    {{
    // Attack ability

        put 
        (
            Name.RAM_ATTACK,
            new AttackAbility
            (
                "Ram",
                "Ram yourself into your opponent",
                1,
                5,
                Name.RAM_ATTACK,
                Elements.getElement(Elements.Name.NULL),
                false,
                false,
                new PercentageModifier(10, Entity.Statistic.SPEED, true)
            )
        );

        put
        (
            Name.FIREBALL, 
            new AttackAbility
            (
                "Fireball", 
                "Launches a ball of fire", 
                1, 
                3, 
                Name.FIREBALL, 
                Elements.getElement(Elements.Name.FIRE), 
                false, 
                false
            )
        );

        put
        (
            Name.LASER,
            new AttackAbility
            (
                "Laser",
                "Repeatedly attack the opponent",
                1,
                1,
                Name.LASER,
                Elements.getElement(Elements.Name.FIRE),
                false,
                true,
                new MultiCastModifier(10)
            )
        );

        put
        (
            Name.SCRATCH,
            new AttackAbility
            (
                "Scratch",
                "Do very minor damage",
                1,
                1,
                Name.SCRATCH,
                Elements.getElement(Elements.Name.NULL),
                true,
                true,
                new PercentageModifier(10, Entity.Statistic.SPEED, true)
            )
        );
        
        put
        (
            Name.SNOWBALL, 
            new AttackAbility
            (
                "Snowball", 
                "Launches a few snowballs. May cause frost or blindness.", 
                1, 
                3, 
                Name.SNOWBALL, 
                Elements.getElement(Elements.Name.ICE), 
                false, 
                true,
                new MultiCastModifier(4)
            )
        );

        put
        (
            Name.LIGHTNING_STRIKE, 
            new AttackAbility
            (
                "Lightning Strike", 
                "Has a chance to shock the enemy.", 
                9, 
                50, 
                Name.LIGHTNING_STRIKE, 
                Elements.getElement(Elements.Name.ELECTRICITY), 
                true, 
                true
            )
        );

        put
        (
            Name.WIDE_SLASH,
            new AttackAbility
            (
                "Wide Slash",
                "Performs a sweeping attack, targetting two other characters",
                4,
                10,
                Name.WIDE_SLASH,
                Elements.getElement(Elements.Name.NULL),
                false,
                false,
                new GroupModifier(50, 2)
            )
        );

        put
        (
            Name.POISON_SLASH, 
            new AttackStatusAbility
            (
                "Poison Slash",
                "Poisons the enemy upon hit",
                4, 
                2,
                Name.POISON_SLASH,
                Elements.getElement(Elements.Name.NULL),
                false,
                false,
                Effects.getEffect(Effects.Name.POISON)
            )
        );

        put
        (
            Name.PASS_TURN, 
            new AttackAbility
            (
                "Pass turn", 
                "Allows the user to skip their turn", 
                0, 
                0, 
                Name.PASS_TURN, 
                Elements.getElement(Elements.Name.NULL), 
                false, 
                false
            )
        );

        put 
        (
            Name.GROUND_SUCTION,
            new AttackStatusAbility 
            (
                "Ground Suction",
                "Pulls the user into the ground, cutting them on the rocks and they bleed out over time",
                5,
                10,
                Name.GROUND_SUCTION,
                Elements.getElement(Elements.Name.EARTH),
                true, 
                true,
                Effects.getEffect(Effects.Name.BLEED)
            )
        );

        // Defense Abilities
        put
        (
            Name.COUNTER,
            new DefenseAbility
            (
                "Counter", 
                "counter the next attack that comes", 
                3, 
                Shields.getShield(Shields.Name.COUNTER), 
                Name.COUNTER, 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.WARNING,
            new DefenseAbility
            (
                "Warning", 
                "The user will be warned not to break your shield", 
                3, 
                Shields.getShield(Shields.Name.SELF_DESTRUCT), 
                Name.WARNING, 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.WITCH_SPELL,
            new DefenseAbility
            (
                "Witch's spell", 
                "When attacked, cast a curse on whoever attacked you", 
                3, 
                Shields.getShield(Shields.Name.WITCH_CURSE), 
                Name.WITCH_SPELL, 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.FIRST_AID,
            new DefenseAbility
            (
                "First id", 
                "Heal oneself upon hit", 
                3, 
                Shields.getShield(Shields.Name.SELF_CARE), 
                Name.FIRST_AID, 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.SUMMON_SQUIRREL,
            new SpawnableAbility
            (
                "Summon Squirrel",
                "Summon a small squirrel to help you",
                5,
                Spawnables.getEntityInfo(Spawnables.Name.SQUIRREL),
                Name.SUMMON_SQUIRREL,
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.SUMMON_GOLEM,
            new SpawnableAbility
            (
                "Summon Golem",
                "Summon a crystal to help you",
                5,
                Spawnables.getEntityInfo(Spawnables.Name.CRYSTAL),
                Name.SUMMON_GOLEM,
                Elements.getElement(Elements.Name.NULL)
            )
        );

        // Support abilities
        put
        (
            Name.HEAL_PULSE, 
            new SupportAbility
            (
                "Heal pulse", 
                "boosts defense for x amount of time",
                1, 
                Effects.getEffect(Effects.Name.INSTANT_HEAL), 
                Name.HEAL_PULSE, 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.SPORE, 
            new SupportAbility
            (
                "Spore", 
                "Gives the opponent an infection",
                9, 
                Effects.getEffect(Effects.Name.FUNGAL_INFECTION), 
                Name.SPORE, 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.PROTECT, 
            new SupportAbility
            (
                "Protect", 
                "protects the user from any hit",
                3, 
                Effects.getEffect(Effects.Name.INSTANT_SHIELD), 
                Name.PROTECT, 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.BURN_UP, 
            new SupportAbility
            (
                "burn up", 
                "The target will burn up for a short amount of time",
                3, 
                Effects.getEffect(Effects.Name.BURNOUT), 
                Name.BURN_UP, 
                Elements.getElement(Elements.Name.FIRE)
            )
        );

        put
        (
            Name.COUNTERSTRIKE, 
            new SupportAbility
            (
                "Counterstrike", 
                "Adds a lot of shield",
                3, 
                Effects.getEffect(Effects.Name.INSTANT_SHIELD_X), 
                Name.COUNTERSTRIKE, 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.ATTACK_UP, 
            new SupportAbility
            (
                "attack up", 
                "boosts damage for x amount of time", 
                5, 
                Effects.getEffect(Effects.Name.ATTACK_BOOST), 
                Name.ATTACK_UP, 
                Elements.getElement(Elements.Name.NULL)
            )
        );
        
        put
        (
            Name.DEFENSE_UP, 
            new SupportAbility
            (
                "defense up", 
                "boosts defense for x amount of time",
                5, 
                Effects.getEffect(Effects.Name.DEFENSE_BOOST), 
                Name.DEFENSE_UP, 
                Elements.getElement(Elements.Name.NULL)
            )
        );
        
        put
        (
            Name.FLARE_UP, 
            new SupportAbility
            (
                "flare up", 
                "Makes the user charge up their attacks", 
                10, 
                Effects.getEffect(Effects.Name.CHARGE), 
                Name.FLARE_UP, 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.POISON, 
            new SupportAbility
            (
                "Poison", 
                "Will poison the person targeted", 
                0, 
                Effects.getEffect(Effects.Name.POISON), 
                Name.POISON, 
                Elements.getElement(Elements.Name.NULL), 
                new RandomModifier(256 / 4 * 3)
            )
        );

        put
        (
            Name.SACRIFICE, 
            new SupportAbility
            (
                "Sacrifice", 
                "Injures the user to cause more damage later", 
                4, 
                Effects.getEffect(Effects.Name.CURSE), 
                Name.SACRIFICE, 
                Elements.getElement(Elements.Name.NULL), 
                new RecoilModifier
                (
                    2, 
                    true, 
                    true
                )
            )
        );

        put
        (
            Name.DEFENSIVE_STANCE, 
            new SupportAbility
            (
                "Defensive Stance", 
                "The user decides to be cautious for some time", 
                3, 
                Effects.getEffect(Effects.Name.GUARD), 
                Name.DEFENSIVE_STANCE, 
                Elements.getElement(Elements.Name.NULL)
            )
        );
        
        put
        (
            Name.CONSTRUCT, 
            new SupportAbility
            (
                "Construct", 
                "The user starts to build up their stats", 
                2, 
                Effects.getEffect(Effects.Name.BUILD_UP), 
                Name.CONSTRUCT, 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.PRAY, 
            new CleanseAbility
            (
                "Pray", 
                "The user makes a prayer onto the target, cleansing them of all effects", 
                3, 
                Name.PRAY, 
                Elements.getElement(Elements.Name.ALL)
            )
        );
    }};



    // Getting an ability's id, based on a name given
    private final static HashMap<String, Name> namesToAbility = new HashMap<>()
    {{
        put("wide_slash", Name.WIDE_SLASH);

        put("fireball", Name.FIREBALL);

        put("snowball", Name.SNOWBALL);

        put("lightning", Name.LIGHTNING_STRIKE);

        put("protect", Name.PROTECT);

        put("counterstrike", Name.COUNTERSTRIKE);

        put("attack_up", Name.ATTACK_UP);

        put("defense_up", Name.DEFENSE_UP);

        put("pass", Name.PASS_TURN);

        put("flare_up", Name.FLARE_UP);

        put("heal", Name.HEAL_PULSE);

        put("poison", Name.POISON);

        put("sacrifice", Name.SACRIFICE);

        put("defensive_stance", Name.DEFENSIVE_STANCE);

        put("construct", Name.CONSTRUCT);
        
        put("pray", Name.PRAY);

        put("counter", Name.COUNTER);

        put("witch_spell", Name.WITCH_SPELL);

        put("warning", Name.WARNING);

        put("first_aid", Name.FIRST_AID);

        put("burn_up", Name.BURN_UP);

        put("squirrel_summon", Name.SUMMON_SQUIRREL);

        put("golem_summon", Name.SUMMON_GOLEM);

        put("ram", Name.RAM_ATTACK);
    }};


    // returns an ability's id
    public static Name getName(
        String name)
    {
        // System.out.println(name)
        // System.out.println(name + NamesToAbility);
        if (name == null)
        {
            return null;
        }
        Name enumName = namesToAbility.get(name.toLowerCase());
        return enumName;
    }

    /**
    */
    // public static void giveAbility(Entity target, int ... ids){
    //   for (int i: ids){
    //     target.addAbility(abilities_2.get(i).copy());
    //   }
    // }

    public static void giveAbility(
        Entity target, 
        Name... names)
    {
        for (Name name: names){
            target.addAbility(ABILITIES.get(name).copy());
        }
    }


    public static void giveAbilities(
        Entity target, 
        List<Name> names)
    {
        HashSet<Name> abilitiesAdded = new HashSet<>();
        for (Name name: names){
            if (!abilitiesAdded.contains(name))
            {
                target.addAbility(ABILITIES.get(name).copy());
                abilitiesAdded.add(name);
            }
        }
    }



    public static void addAbility(Ability a, String... identifiers)
    {
        for (int i = 0; i < identifiers.length; i++)
        {
            String identifier = identifiers[i];
            if (namesToAbility.containsKey(identifier))
            {
                continue;
            }
            namesToAbility.put(identifier, Name.CUSTOM);
            customAbilities.put(identifier, a);
        }
    }

    

}