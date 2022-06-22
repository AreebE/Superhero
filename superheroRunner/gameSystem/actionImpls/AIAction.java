package gameSystem.actionImpls;

import java.util.List;

import gameSystem.Action;
import gameSystem.Entity;
import gameSystem.InputSystem;
import gameSystem.entityImpls.ControllableAutoEntity;

/**
 * An action used for Controllable entities only.
 *
 */
public class AIAction extends Action
{
    private ControllableAutoEntity caster;

    /**
     * A basic constructor for the AI Action
     * 
     * @param target the target to attack
     * @param caster the Auto entity
     * @param allHeros the other heros here
     * @param inputReader the inputReader to get certain attributes.
     */
    public AIAction(
        Entity target, 
        ControllableAutoEntity caster, 
        List<Entity> allHeros,
        InputSystem inputReader)
    {
        super(target, caster, caster.getCurrentAbility().getName(), allHeros, inputReader);
        this.caster = caster;
    }

    /**
     * Do a test to see if this is a legal action. It's specifically if the target is targetable.
     */
    @Override
    public boolean isLegalAction()
    {
        return  (
                    super.getTarget().isTargettable() 
                );
    }

}
