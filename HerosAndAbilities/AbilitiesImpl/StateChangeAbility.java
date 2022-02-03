package battlesystem;

import java.util.EnumMap;
import java.util.List;

public class StateChangeAbility extends SupportAbility
{

    private State template;

    public StateChangeAbility(
        String name, 
        String desc, 
        int cooldown, 
        State template, 
        Element em, 
        AbilityModifier... modifiers) 
    {
        super(name, desc, cooldown, null, em, modifiers);
        this.template = template;
    }

    public StateChangeAbility(
        String name, 
        String desc, 
        int cooldown, 
        State template, 
        Element em,
        EnumMap<Ability.Modifier, AbilityModifier> modifiers
        )
    {
        super(name, desc, cooldown, null, em, modifiers);
        this.template = template;
    }
    
    @Override
    protected void castAbility(
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log) 
    {
        String oldStateName = target.getState().getName();
        Object[] contents = new Object[]{target.getName(), oldStateName, template.getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.STATE_CHANGE, contents));
        target.replaceState(template.copy());
        return; 
    }


    @Override
    public Ability copy() 
    {
        return new StateChangeAbility
                (
                    getName(), 
                    getDescription(), 
                    getCooldown(), 
                    template, 
                    getElement()

                );
    }
}