package battlesystem;

import java.util.EnumMap;
import java.util.List;

public class AttackStatusAbility extends AttackAbility 
{
    private Effect sideEffect;

    public AttackStatusAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Abilities.Name enumName,
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
            enumName, 
            em, 
            ignoresBaseDefense, 
            isPiercing, 
            modifiers
        );
        this.sideEffect = sideEffect;
    }


    public AttackStatusAbility(
        String name, 
        String desc, 
        int cooldown, 
        int strength, 
        Abilities.Name enumName,
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing,
        Effect sideEffect,
        EnumMap<Abilities.Modifier, AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            strength, 
            enumName, 
            em, 
            ignoresBaseDefense, 
            isPiercing, 
            modifiers
        );
        this.sideEffect = sideEffect;
    }


    @Override
    protected void castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log) 
    {
        super.castAbility(target, caster, otherTargets, allPlayers, log);
        if (isPiercing() 
            || !caster.hasShield()) 
        {
            target.addEffect(sideEffect.copy());
            Object[] contents = new Object[]{target.getName(), sideEffect.getName()};
            log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.ATTACK_STATUS, contents));
        }
        return;
    }


    @Override
    public Ability copy() 
    {
        return new AttackStatusAbility
                    (   getName(), 
                        getDescription(), 
                        getCooldown(), 
                        getStrength(), 
                        getEnumName(),
                        getElement(), 
                        doesIgnoreBaseDefense(), 
                        isPiercing(), 
                        sideEffect.copy(),
                        getModifiers()
                    );
    }
}