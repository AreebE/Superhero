package battlesystem;

import java.util.List;
public class PassAbility extends Ability 
{

    public PassAbility
    (
        String name, 
        String desc
    ) 
    {
        super(name, desc, 0, 0, Abilities.Type.ATTACK, Abilities.Name.PASS_TURN, null);
    }

    @Override
    protected void castAbility
    (
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog battleLog
    )
    {
        
    }

    @Override
    public void useAbility
    (
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log
    )
    {
        caster.searchForShield(Shields.Trigger.PASS, Elements.getElement(Elements.Name.ALL), caster, target, log);
        Object[] contents = new Object[]{caster.getName()};
        log.addEntry(new BattleLog.Entry(BattleLog.Entry.Type.PASS, contents));
        return;
    }  
    
    @Override
    public Ability copy()
    {
        return new PassAbility(getName(), getDescription());
    }
}