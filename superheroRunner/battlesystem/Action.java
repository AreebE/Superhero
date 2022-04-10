package battlesystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Get an action
 *
 */
public class Action {
    private Entity mainTarget;
    private Entity caster;
    private List<Entity> otherTargets;
    private List<Entity> allHeros;
    private Ability ability;
    
    /**
     * A constructor for a basic action
     *  
     * @param target the target
     * @param caster the caster/ action this belongs to.
     * @param abilityName the name of the ability to use
     * @param allHeros All entities available
     * @param input the input system to request more information
     */
    public Action(
        Entity target, 
        Entity caster, 
        String abilityName,
        List<Entity> allHeros,
        InputSystem input)
    {
        this.mainTarget = target;
        this.caster = caster;
        this.allHeros = allHeros;
        this.otherTargets = new ArrayList<>();
        this.ability = caster.getAbility(abilityName);
        // System.out.println("Action created for " + caster.getName());
        if (ability != null && caster.hasGroupAbility(abilityName))
        {
            
            int limit = ability.getTargetAmount();
            this.otherTargets = input.getSecondaryTargets(limit, caster);
            // System.out.println(otherTargets);
        }
    }

    /**
     * Return if this action can be done. Examples:
     *  * No ability found
     *  * If another target can't be targetted
     * 
     * @return if it is legal.
     */
    public boolean isLegalAction()
    {
        if (ability == null)
        {
            // System.out.println("No ability found");
            return false;
        }
        if (otherTargets != null)
        {
            for (int i = 0; i < otherTargets.size(); i++)
            {  
                Entity singleTarget = otherTargets.get(i);
                if (!singleTarget.isTargettable() )
                {
                    return false;
                }
            }
        }
        
        return mainTarget.isTargettable();
    }
    
    /**
     * Perform the action, whether it is technically legal or not. 
     * @param log the battle log to recourd actions.
     */
    public void performAction(BattleLog log)
    {
        // System.out.println("perform action");
        caster.searchForShield(Shield.Trigger.ANY_ACTION, Elements.getElement(Elements.Name.ALL), mainTarget, caster, log);
        otherTargets.add(0, mainTarget);
        ability.useAbility(otherTargets, caster, allHeros, log);
        caster.endOfTurn(log);
        // caster.endOfTurn();
    }

    /**
     * Get the target
     * @return the target
     */
    public Entity getTarget()
    {
        return mainTarget;
    }

    /**
     * get caster
     * @return the caster
     */
    public Entity getCaster()
    {
        return caster;
    }

    /**
     * other targets
     * @return the other targets
     */
    public List<Entity> getOtherTargets()
    {
        return otherTargets;
    }

    /**
     * get all other characters
     * @return all entities.
     */
    public List<Entity> getAllHeros()    
    {
        return allHeros;
    }
}