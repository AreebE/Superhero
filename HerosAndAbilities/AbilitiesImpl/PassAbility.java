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
    protected String castAbility
    (
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers
    )
    {
        return "  ";
    }

    @Override
    public String useAbility
    (
        Entity target, 
        Entity caster,
        List<Entity> otherTargets,
        List<Entity> allPlayers
    )
    {
        return new StringBuilder(caster.getName())
                .append(" passed their turn.")
                .toString();
    }  
    
    @Override
    public Ability copy()
    {
        return new PassAbility(getName(), getDescription());
    }
}