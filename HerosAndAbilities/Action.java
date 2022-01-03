package battlesystem;

import java.util.List;


public class Action {
    private Entity target;
    private Entity caster;
    private List<Entity> otherTargets;
    private List<Entity> allHeros;
    private Abilities.Name name;

    public Action(
        Entity target, 
        Entity caster, 
        String abilityName,
        List<Entity> allHeros,
        InputSystem input)
    {
        this(target, caster, Abilities.getName(abilityName), allHeros, input);
    }

    public Action(
        Entity target, 
        Entity caster, 
        Abilities.Name abilityName,
        List<Entity> allHeros,
        InputSystem input)
    {
        this.target = target;
        this.caster = caster;
        this.allHeros = allHeros;
        this.name = abilityName;
        this.otherTargets = null;
        if (caster.getAbility(name) != null && caster.hasGroupAbility(name))
        {
            int limit = ((GroupModifier) caster.getAbility(name).getModifier(Abilities.Modifier.GROUP)).getLimit();
            this.otherTargets = input.getSecondaryTargets(limit);
            // System.out.println(otherTargets);
        }
    }

    public boolean isLegalAction()
    {
        if (name == null || caster.getAbility(name) == null)
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

    public void performAction()
    {
        // System.out.println("perform action");
        caster.getAbility(name).useAbility(target, caster, otherTargets, allHeros);
    }

    public Entity getTarget()
    {
        return target;
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