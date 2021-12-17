
import java.util.ArrayList;
import java.util.HashMap;
import java.util.EnumMap;

public class AbilityList 
{
    // Prevents any instances of this class from being made

    //private ArrayList<Element> elements;

    private AbilityList(){}

    public static enum ModifierName
    {
        RANDOM,
        RECOIL,
        MULTICAST,
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
        FIREBALL, 
        SNOWBALL, 
        LIGHTNING_STRIKE, 
        PASS_TURN,
        POISON_SLASH,

        // Defense 
        COUNTER,
        WITCH_SPELL,
        WARNING,
        FIRST_AID,

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
            Name.FIREBALL, 
            new AttackAbility
            (
                "Fireball", 
                "Launches a ball of fire", 
                1, 
                3, 
                Name.FIREBALL, 
                ElementList.getElement(ElementList.Name.FIRE), 
                false, 
                false
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
                ElementList.getElement(ElementList.Name.ICE), 
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
                1, 
                3, 
                Name.LIGHTNING_STRIKE, 
                ElementList.getElement(ElementList.Name.ELECTRICITY), 
                true, 
                false
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
                ElementList.getElement(ElementList.Name.NULL),
                false,
                false,
                EffectList.getEffect(EffectList.Name.POISON)
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
                ElementList.getElement(ElementList.Name.NULL), 
                false, 
                false
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
                ShieldList.getShield(ShieldList.Name.COUNTER), 
                Name.COUNTER, 
                ElementList.getElement(ElementList.Name.NULL)
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
                ShieldList.getShield(ShieldList.Name.SELF_DESTRUCT), 
                Name.WARNING, 
                ElementList.getElement(ElementList.Name.NULL)
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
                ShieldList.getShield(ShieldList.Name.WITCH_CURSE), 
                Name.WITCH_SPELL, 
                ElementList.getElement(ElementList.Name.NULL)
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
                ShieldList.getShield(ShieldList.Name.SELF_CARE), 
                Name.FIRST_AID, 
                ElementList.getElement(ElementList.Name.NULL)
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
                EffectList.getEffect(EffectList.Name.INSTANT_HEAL), 
                Name.HEAL_PULSE, 
                ElementList.getElement(ElementList.Name.NULL)
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
                EffectList.getEffect(EffectList.Name.INSTANT_SHIELD), 
                Name.PROTECT, 
                ElementList.getElement(ElementList.Name.NULL)
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
                EffectList.getEffect(EffectList.Name.BURNOUT), 
                Name.BURN_UP, 
                ElementList.getElement(ElementList.Name.FIRE)
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
                EffectList.getEffect(EffectList.Name.INSTANT_SHIELD_X), 
                Name.COUNTERSTRIKE, 
                ElementList.getElement(ElementList.Name.NULL)
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
                EffectList.getEffect(EffectList.Name.ATTACK_BOOST), 
                Name.ATTACK_UP, 
                ElementList.getElement(ElementList.Name.NULL)
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
                EffectList.getEffect(EffectList.Name.DEFENSE_BOOST), 
                Name.DEFENSE_UP, 
                ElementList.getElement(ElementList.Name.NULL)
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
                EffectList.getEffect(EffectList.Name.CHARGE), 
                Name.FLARE_UP, 
                ElementList.getElement(ElementList.Name.NULL)
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
                EffectList.getEffect(EffectList.Name.POISON), 
                Name.POISON, 
                ElementList.getElement(ElementList.Name.NULL), 
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
                EffectList.getEffect(EffectList.Name.CURSE), 
                Name.SACRIFICE, 
                ElementList.getElement(ElementList.Name.NULL), 
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
                EffectList.getEffect(EffectList.Name.GUARD), 
                Name.DEFENSIVE_STANCE, 
                ElementList.getElement(ElementList.Name.NULL)
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
                EffectList.getEffect(EffectList.Name.BUILD_UP), 
                Name.CONSTRUCT, 
                ElementList.getElement(ElementList.Name.NULL)
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
                ElementList.getElement(ElementList.Name.ALL)
            )
        );
    }};



    // Getting an ability's id, based on a name given
    private final static HashMap<String, Name> namesToAbility = new HashMap<>()
    {{
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
    }};


    // returns an ability's id
    public static Name getName(
        String name)
    {
        // System.out.println(name)
        // System.out.println(name + NamesToAbility);
        Name enumName = namesToAbility.get(name.toLowerCase());
        return enumName;
    }

    /**
    */
    // public static void giveAbility(Superhero target, int ... ids){
    //   for (int i: ids){
    //     target.addAbility(abilities_2.get(i).copy());
    //   }
    // }

    public static void giveAbility(
        Superhero target, 
        Name ... names)
    {
        for (Name name: names){
            target.addAbility(ABILITIES.get(name).copy());
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