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
    protected String castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers) 
    {
        StringBuilder action = new StringBuilder(super.castAbility(target, caster, otherTargets, allPlayers)).append("\n");
        if (isPiercing() 
            || !caster.hasShield()) 
        {
            target.addEffect(sideEffect.copy());
            action.append(target.getName())
                .append(" also received the \'")
                .append(sideEffect.getName())
                .append("\' effect.");
        }
        else 
        {
            action.append("No effect was applied.");
        }
        return action.toString();
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