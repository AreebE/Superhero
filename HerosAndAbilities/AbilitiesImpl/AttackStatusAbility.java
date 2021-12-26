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
        AbilityList.Name enumName,
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
        AbilityList.Name enumName,
        Element em, 
        boolean ignoresBaseDefense, 
        boolean isPiercing,
        Effect sideEffect,
        EnumMap<AbilityList.ModifierName, AbilityModifier> modifiers) 
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
    protected boolean castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers) 
    {
        super.castAbility(target, caster, otherTargets, allPlayers);
        if (isPiercing() 
            || !caster.hasShield()) 
        {
            target.addEffect(sideEffect.copy());
        }
        return true;
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