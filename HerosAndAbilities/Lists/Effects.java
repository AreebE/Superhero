package battlesystem;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

public final class Effects 
{

    private Effects(){}

    public static enum Type
    {
        ATTACK, 
        DEFENSE, 
        HEALTH,
        MAX_HEALTH,
        SHIELD, 
        DAMAGE, 
        SPEED,
        GROUP
    } 


    public static enum Name
    {
        // Base stat Effects
        ATTACK_BOOST,
        ATTACK_BOOST_X,
        DEFENSE_BOOST,
        DEFENSE_BOOST_X,
        SPEED_BOOST_X,
        MAX_HEALTH_BOOST_X,
        CHARGE,
        BUILD_UP,
        GUARD,
        BURNOUT,
        SACRIFICIAL_GIFT,
    
        // Shield
        INSTANT_SHIELD,
        INSTANT_SHIELD_X,

        // health Effects
        INSTANT_HEAL,
        RESURRECT,
        REGEN, 
        PERMAGEN,

        // Damage
        POISON,
        CURSE,
        RETALIATE,
        ILLUSIONARY_BLAST,
        BLEED,
        EXPLODE,
        FUNGAL_INFECTION
    };


    // Field Effects

    // Hashmap of Effects
    // private final static HashMap<Integer, Effect> listOfEffects = new HashMap<>(){{
    //   put(ATTACK_BOOST, new InstantEffect(2, Type.ATTACK, "attack up", "Boosts the base attack of all abilities by 2, except Pass"));
    //   put(DEFENSE_BOOST,  new InstantEffect(2, Type.DEFENSE, "defense up", "Reduces all future damage the character takes by 2"));
    //   put(CHARGE, new Effect(1, Type.ATTACK, 5, true, "Charge", "Will increase the base attack of the user by one each turn"));
    //   // put(1, 3, POISON, new Effect("poison", "The user takes one damage per turn, piercing shield, over 3 turns"));
        
    // }};

    private final static EnumMap<Name, Effect> EFFECTS = new EnumMap<>(Name.class)
    {{
        put
        (
            Name.ATTACK_BOOST, 
            new InstantEffect
            (
                2, 
                Type.ATTACK, 
                "attack up", 
                "Boosts the base attack of all abilities by 2, except Pass", 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put 
        (
            Name.ATTACK_BOOST_X,
            new InstantEffect
            (
                7,
                Type.ATTACK,
                "Attack boost X",
                "Buffs the attack by a lot",
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.DEFENSE_BOOST_X,
            new InstantEffect 
            (
                7, 
                Type.DEFENSE,
                "Defense Boost X",
                "Buffs the defense",
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put 
        (
            Name.SPEED_BOOST_X,
            new InstantEffect 
            (  
                20,
                Type.SPEED,
                "Speed Boost X",
                "Buffs speed",
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put 
        (
            Name.MAX_HEALTH_BOOST_X,
            new InstantEffect 
            (  
                20,
                Type.MAX_HEALTH,
                "Max Health X",
                "Buffs health",
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put 
        (
            Name.RETALIATE,
            new InstantEffect
            (
                4,
                Type.DAMAGE,
                "Retaliate",
                "Does some damage in retaliation",
                Elements.getElement(Elements.Name.NULL),
                new boolean[]{true, true}
            )
        );

        put 
        (
            Name.ILLUSIONARY_BLAST,
            new InstantEffect
            (
                30,
                Type.DAMAGE,
                "Illusionary blast",
                "An illusion attacks the person",
                Elements.getElement(Elements.Name.LIGHT),
                new boolean[]{true, true}
            )
        );


         put 
        (
            Name.EXPLODE,
            new InstantEffect
            (
                10,
                Type.DAMAGE,
                "Explode",
                "Deals damage with an explosion",
                Elements.getElement(Elements.Name.NULL),
                new boolean[]{true, true}
            )
        );

        put 
        (
            Name.RESURRECT,
            new InstantEffect
            (
                100,
                Type.HEALTH,
                "Resurrect",
                "Resurrect the person from beyond the grave",
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.INSTANT_HEAL, 
            new InstantEffect
            (
                10, 
                Type.HEALTH, 
                "Instant heal", 
                "Heals 10 health immediately", 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        

        put
        (
            Name.INSTANT_SHIELD, 
            new InstantEffect
            (
                1, 
                Type.SHIELD, 
                "Instant Shield", 
                "Adds 10 shield immediately", 
                Elements.getElement(Elements.Name.NULL)
            )
        );


/*
public DecayEffect(
        int basePower,
        int decayRate, 
        int turnDecayStarts,
        Effects.Type type, 
        int duration,  
        String name, 
        String desc,
        Element element)
    {
        */
        put
        (
            Name.BURNOUT,
            new DecayEffect
            (
                10,
                1,
                1,
                Type.ATTACK,
                14,
                "Burnout",
                "Gives the user immediate attack, then it slowly burns out",
                Elements.getElement(Elements.Name.FIRE)
            )
        );

        put
        (
            Name.FUNGAL_INFECTION ,
            new DecayEffect
            (
                1,
                0,
                -1,
                Type.DAMAGE,
                10,
                "Fungal infection",
                "A type of infection that deals more damage over time",
                Elements.getElement(Elements.Name.NULL),
                new boolean[] {true, true}
            )
        );

        put
        (
            Name.INSTANT_SHIELD_X, 
            new InstantEffect
            (
                50, 
                Type.SHIELD, 
                "Mega Instant Shield", 
                "Adds 50 shield immediately", 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.DEFENSE_BOOST,  
            new InstantEffect
            (
                2, 
                Type.DEFENSE, 
                "defense up", 
                "Reduces all future damage the character takes by 2", 
                Elements.getElement(Elements.Name.NULL)
            )
        );
        
        put(
            Name.CHARGE, 
            new Effect
            (
                1, 
                Type.ATTACK, 
                5, 
                true, 
                "Charge", 
                "Will increase the base attack of the user by one each turn", 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.BLEED, 
            new Effect
            (   
                3,
                Type.ATTACK,
                5, 
                false,
                "Bleed", 
                "The opponent starts to suffer from blood loss", 
                Elements.getElement(Elements.Name.NULL),
                new boolean[]{true, true}
            )
        );

        
        
        put(
            Name.REGEN, 
            new Effect
            (
                1, 
                Type.HEALTH,
                5, 
                true, 
                "Regen", 
                "The player will recover health for ten turns, 1 health per turn. ", 
                Elements.getElement(Elements.Name.NULL)
            )
        );
        
        put
        (
            Name.POISON, 
            new Effect
            (
                1, 
                Type.DAMAGE, 
                5, 
                true, 
                "Poison", 
                "Will decrease the health of the player by one.", 
                Elements.getElement(Elements.Name.NULL), 
                new boolean[]{true, true}
            )
        );

        put
        (
            Name.GUARD, 
            new OneTimeEffect
            (
                4, 
                Type.DEFENSE, 
                5, 
                "Guard", 
                "Will let the user adopt a defensive stance", 
                Elements.getElement(Elements.Name.NULL)
            )
        );

        put
        (
            Name.CURSE, 
            new DelayedEffect
            (
                20, 
                Type.DAMAGE, 
                4, 
                "Curse", 
                "Deals a hefty amount of damage after some time has passed", 
                Elements.getElement(Elements.Name.NULL),  
                new boolean[]{false, false}
            )
        );

        put
        (
            Name.PERMAGEN, 
            new PassiveEffect
            (
                1, 
                Type.HEALTH, 
                "Permanent regeneration", 
                "Just as the name says.", 
                Elements.getElement(Elements.Name.NULL), 
                new boolean[]{true, true}
            )
        );

        put
        (
            Name.BUILD_UP, 
            new GroupEffect
            (
                "Build up", 
                "The target begins to increase all of their stats.", 
                Elements.getElement(Elements.Name.NULL), 
                this.get(Name.CHARGE), 
                this.get(Name.DEFENSE_BOOST), 
                this.get(Name.REGEN)
            )
        );

        put 
        (
            Name.SACRIFICIAL_GIFT,
            new GroupEffect
            (
                "Sacrificial Gift",
                "The target gets some extreme buffs",
                Elements.getElement(Elements.Name.NULL),
                this.get(Name.ATTACK_BOOST_X),
                this.get(Name.DEFENSE_BOOST_X),
                this.get(Name.MAX_HEALTH_BOOST_X),
                this.get(Name.SPEED_BOOST_X)
            )
        );

        // template
        // put(Name., new __Effect(1, Type., 1, "", "", Elements.getElement(Elements.Name.), true, true));
    }};


    /**
    * A method for allowing a Effect to be called with a given index
    *
    * @param   index   the index of the Effect to be returned
    *
    * @return  the Effect that corresponds to the given index
    */
    public static Effect getEffect(
        Name name)
    {
        return EFFECTS.get(name).copy();
    }

    public static void giveEffects(
        Entity e,
        List<Name> names
    )
    {
        if (names == null)
        {
            return;
        }
        for (Name n: names)
        {
            e.addEffect(getEffect(n).copy());
        }
    }
    
}