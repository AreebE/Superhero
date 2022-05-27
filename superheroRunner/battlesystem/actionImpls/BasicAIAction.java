package battlesystem.actionImpls;

import java.util.List;

import battlesystem.Action;
import battlesystem.Entity;
import battlesystem.InputSystem;
import battlesystem.entityImpls.BasicAIEntity;

public class BasicAIAction extends Action{

	public BasicAIAction(BasicAIEntity caster, List<Entity> allHeros, BasicAIEntity.TargettingSystem input) {
		super(input.getSingleTarget(), caster, input.getAbilityName(), allHeros, input);
        
		// TODO Auto-generated constructor stub
	}

}
