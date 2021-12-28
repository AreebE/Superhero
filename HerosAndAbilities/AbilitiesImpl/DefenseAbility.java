package battlesystem;

import java.util.EnumMap;
import java.util.List;

public class DefenseAbility extends Ability 
{
    private Shield shield;

    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown, 
        Shield shield, 
        Abilities.Name enumName,
        Element em, 
        AbilityModifier... modifiers) 
    {
        super
        (
            name, 
            desc,
            cooldown, 
            0, 
            Abilities.Type.DEFENSE, 
            enumName, 
            em,
            modifiers
        );
        this.shield = shield;
    }


    public DefenseAbility(
        String name, 
        String desc, 
        int cooldown,
        Shield shield, 
        Abilities.Name enumName,
        Element em, 
        EnumMap<Abilities.ModifierName, AbilityModifier> modifiers) 
    {
        super
        (
            name, 
            desc, 
            cooldown, 
            0, 
            Abilities.Type.DEFENSE, 
            enumName, 
            em, 
            modifiers
        );
        this.shield = shield;
    }


    @Override
    protected boolean castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers) 
    {
        target.addShield(shield.copy());
        return true;
    }

    @Override
    public Ability copy() 
    {
        return new DefenseAbility
                (   
                    getName(), 
                    getDescription(), 
                    getCooldown(),  
                    shield.copy(),
                    getEnumName(),
                    getElement(), 
                    getModifiers()
                );
    }
}

// regeneration, heal, and shields/ resistance effects