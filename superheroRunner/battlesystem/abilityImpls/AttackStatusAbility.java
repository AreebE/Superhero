package battlesystem.abilityImpls;

import java.util.EnumMap;
import java.util.List;

import battlesystem.Ability;
import battlesystem.AbilityModifier;
import battlesystem.BattleLog;
import battlesystem.Effect;
import battlesystem.Element;
import battlesystem.Entity;

/**
 * This extension of attack ability is going to add an effect to the target.
 */
public class AttackStatusAbility extends AttackAbility 
{
	
    private Effect sideEffect;
    
    /**
     * One key difference, compared to the other one:
     *  
     * @param name name of the ability
     * @param desc the description of how the ability works
     * @param cooldown the cooldown
     * @param strength the strength
     * @param em the element
     * @param ignoresBaseDefense if it ignores defense
     * @param isPiercing if it ignores shield
     * @param sideEffect *NEW* The effect this will be the one applied to the target
     * @param modifiers any modifiers to give
     */
    public AttackStatusAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing, 
        Effect sideEffect,
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            em, 
            ignoresBaseDefense, 
            isPiercing, 
            modifiers
        );
        this.sideEffect = sideEffect;
    }


    /**
     * The constructor for the copy method.
     * 
     * @param name the name of the ability
     * @param desc the description of the ability
     * @param cooldown the cooldown
     * @param strength the base strength
     * @param em the element
     * @param ignoresBaseDefense if it ignores defense
     * @param isPiercing if it ignores shield
     * @param sideEffect the side effect
     * @param modifiers the modifiers for the ability.
     */
    public AttackStatusAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing,
        Effect sideEffect,
        EnumMap<Ability.Modifier, AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            em, 
            ignoresBaseDefense, 
            isPiercing, 
            modifiers
        );
        this.sideEffect = sideEffect;
    }


    /**
     * It calls the super.castAbility, then applies the status effect if the ability either pierces or the target 
     * doesn't have a shield.
     * 
     * @param target the target for the ability
     * @param caster the caster
     * @param otherTargets other targets that may be important
     * @param allPlayers all other players.
     */
    @Override
    public void castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log) 
    {
        super.castAbility(target, caster, otherTargets, allPlayers, log);
        if (isPiercing() 
            || !target.hasShield()) 
        {
            target.addEffect(sideEffect.copy());
            Object[] contents = new Object[]{target.getName(), sideEffect.getName()};
            log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.ATTACK_STATUS, contents));
        }
        return;
    }

    /**
     * The copy method to create a copy of an attackStatusAbility
     * 
     * @return the copied ability.
     */
    @Override
    public Ability copy() 
    {
        return new AttackStatusAbility
                    (   getName(), 
                        getDescription(), 
                        getCooldown(), 
                        getStrength(), 
                        getElement(), 
                        doesIgnoreBaseDefense(), 
                        isPiercing(), 
                        sideEffect.copy(),
                        getModifiers()
                    );
    }

}