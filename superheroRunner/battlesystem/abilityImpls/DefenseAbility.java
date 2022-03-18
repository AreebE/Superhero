package battlesystem.abilityImpls;

import java.util.EnumMap;
import java.util.List;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Element;
import battlesystem.Entity;
import battlesystem.Shield;

/**
 * The defense ability used to produce a shield and give it to a player.
 *
 */
public class DefenseAbility extends Ability 
{
    private Shield shield;

    /**
     * The basic constructor for the defense ability.
     * 
     * @param name the name of the ability
     * @param desc the description of how it works
     * @param cooldown how many turns it will be on cooldown for
     * @param shield *NEW* the shield that this will apply to the target
     * @param em the elemental attributes of the spell
     * @param modifiers the extra modifiers of this ability
     */
    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown, 
        Shield shield, 
        Element em, 
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc,
            cooldown, 
            0, 
            Ability.Category.DEFENSE,  
            em,
            modifiers
        );
        this.shield = shield;
    }


    /**
     * The constructor for the copy method
     * 
     * @param name the name of the ability
     * @param desc the description of how it works
     * @param cooldown the cooldown of the ability
     * @param shield the shield that will be given to the target
     * @param em the elemental attributes of the skill
     * @param modifiers the modifiers to add to this ability
     */
    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown,
        Shield shield, 
        Element em, 
        EnumMap<Ability.Modifier, AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            0, 
            Ability.Category.DEFENSE, 
            em,
            modifiers
        );
        this.shield = shield;
    }


    /**
     * The method to cast the ability. In this case, it will give the shield to the target.
     * 
     * @param target the target to give the shield to
     * @param caster the person who is using the ability.
     * @param otherTargets the other targets to attack
     * @param allPlayers the other players in the list
     * @param log the battlelog to record the shield given
     */
    @Override
    public void castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log) 
    {
        target.addShield(shield.copy());
        Object[] contents = new Object[]{target.getName(), shield.getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.DEFENSE, contents));
        return;
    }

    /**
     * Create a copy of the defense ability.
     * @return a copy of the defense ability.
     */
    @Override
    public Ability copy() 
    {
        return new DefenseAbility
                (   
                    getName(), 
                    getDescription(), 
                    getCooldown(),  
                    shield.copy(),
                    getElement(),
                    getModifiers()
                );
    }


}

// regeneration, heal, and shields/ resistance effects