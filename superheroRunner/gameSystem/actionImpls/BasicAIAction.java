package gameSystem.actionImpls;

import java.util.List;

import gameSystem.Action;
import gameSystem.Entity;
import gameSystem.InputSystem;
import gameSystem.entityImpls.BasicAIEntity;

public class BasicAIAction extends Action{

	public BasicAIAction(BasicAIEntity caster, List<Entity> allHeros, BasicAIEntity.TargettingSystem input) {
		super(input.getSingleTarget(), caster, input.getAbilityName(), allHeros, input);
        
		// TODO Auto-generated constructor stub
	}

}
