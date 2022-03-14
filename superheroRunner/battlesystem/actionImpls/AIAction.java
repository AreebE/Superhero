package battlesystem.actionImpls;

import java.util.List;

import battlesystem.Action;
import battlesystem.Entity;
import battlesystem.InputSystem;
import battlesystem.entityImpls.AIEntity;

public class AIAction extends Action
{
    private AIEntity caster;

    public AIAction(
        Entity target, 
        AIEntity caster, 
        List<Entity> allHeros,
        InputSystem inputReader)
    {
        super(target, caster, caster.getCurrentAbility().getName(), allHeros, inputReader);
        this.caster = caster;
    }

    @Override
    public boolean isLegalAction()
    {
        return  (
                    !(super.getTarget() instanceof AIEntity) 
                    || ((AIEntity) super.getTarget()).isTargettable() 
                );
    }

}
