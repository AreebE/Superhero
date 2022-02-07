package battlesystem;

import java.util.List;


public class Action {
    private Entity target;
    private Entity caster;
    private List<Entity> otherTargets;
    private List<Entity> allHeros;
    private Ability ability;

    public Action(
        Entity target, 
        Entity caster, 
        String abilityName,
        List<Entity> allHeros,
        InputSystem input)
    {
        this.target = target;
        this.caster = caster;
        this.allHeros = allHeros;
        this.otherTargets = null;
        this.ability = caster.getAbility(abilityName);
        
        if (ability != null && caster.hasGroupAbility(abilityName))
        {
            
            int limit = ((GroupModifier) ability.getModifier(Ability.Modifier.GROUP)).getLimit();
            this.otherTargets = input.getSecondaryTargets(limit, caster);
            // System.out.println(otherTargets);
        }
    }

    public boolean isLegalAction()
    {
        if (ability == null)
        {
            return false;
        }
        if (otherTargets != null)
        {
            for (int i = 0; i < otherTargets.size(); i++)
            {  
                Entity singleTarget = otherTargets.get(i);
                if ((singleTarget instanceof AIEntity) 
                    && !((AIEntity) singleTarget).isTargettable() )
                {
                    return false;
                }
            }
        }
        
        return  (
                    ! (target instanceof AIEntity) 
                    || ((AIEntity) target).isTargettable() 
                );
    }

    public void performAction(BattleLog log)
    {
        // System.out.println("perform action");
        ability.useAbility(target, caster, otherTargets, allHeros, log);
        caster.endOfTurn(log);
        // caster.endOfTurn();
    }

    public Entity getTarget()
    {
        return target;
    }

    public Entity getCaster()
    {
        return caster;
    }

    public List<Entity> getOtherTargets()
    {
        return otherTargets;
    }

    public List<Entity> getAllHeros()    
    {
        return allHeros;
    }
}