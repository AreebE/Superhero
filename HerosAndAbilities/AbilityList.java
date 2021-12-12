import java.util.ArrayList;
import java.util.HashMap;
import java.util.EnumMap;

public class AbilityList 
{
    // Prevents any instances of this class from being made

    //private ArrayList<Element> elements;

    private AbilityList(){}

    public static enum AbilityModifierNames
    {
        RANDOM,
        RECOIL,
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

        // Defense 
        HEAL_PULSE,
        PROTECT,
        COUNTERSTRIKE,

        // Support
        ATTACK_UP,
        DEFENSE_UP,
        FLARE_UP,
        POISON,
        SACRIFICE,
        DEFENSIVE_STANCE,
        CONSTRUCT,
        PRAY
    }
    public static int MAX_CHANCE = 256;
  

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
                true
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


        // Defense abilities
        put
        (
            Name.HEAL_PULSE, 
            new HealAbility
            (
                "Heal pulse", 
                "heals oneself", 
                1, 
                10, 
                Name.HEAL_PULSE, 
                ElementList.getElement(ElementList.Name.NULL)
            )
        );

        put
        (
            Name.PROTECT, 
            new DefenseAbility
            (
                "Protect", 
                "protects the user from any hit.", 
                3, 
                5, 
                Name.PROTECT, 
                ElementList.getElement(ElementList.Name.NULL)
            )
        );
        
        put
        (
            Name.COUNTERSTRIKE, 
            new DefenseAbility
            (
                "Counterstrike", 
                "Will counter any attack that comes forth", 
                5, 
                50, 
                Name.COUNTERSTRIKE, 
                ElementList.getElement(ElementList.Name.NULL)
            )
        );

        // Support abilities
        // System.out.println(listOfEffects);
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
    //     target.addAbility(abilities_2.get(i).copyAbility());
    //   }
    // }

    public static void giveAbility(
        Superhero target, 
        Name ... names)
    {
        for (Name name: names){
            target.addAbility(ABILITIES.get(name).copyAbility());
        }
    }

    

}