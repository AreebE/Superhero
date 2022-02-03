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
        super(name, desc, 0, 0, Ability.Type.ATTACK,null);
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

    
    public void useAbility
    (
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers,
        BattleLog log
    )
    {
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