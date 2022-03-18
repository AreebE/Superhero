package battlesystem.actionImpls;

import java.util.List;

import battlesystem.Action;
import battlesystem.Entity;
import battlesystem.InputSystem;
import battlesystem.entityImpls.AIEntity;

/**
 * An action used for AI entities only.
 *
 */
public class AIAction extends Action
{
    private AIEntity caster;

    /**
     * A basic constructor for the AI Action
     * 
     * @param target the target to attack
     * @param caster the AI entity
     * @param allHeros the other heros here
     * @param inputReader the inputReader to get certain attributes.
     */
    public AIAction(
        Entity target, 
        AIEntity caster, 
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
